package com.kf.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kf.demo.model.SiteOutageApiRequest;
import com.kf.demo.model.SiteApiResponse;
import com.kf.demo.model.OutageApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class KrakenController {

    @Value("${kraken.api.endpoint}")
    private String endpoint;

    @Value("${kraken.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    @GetMapping("/outages")
    public ResponseEntity<List<OutageApiResponse>> getAllOutages() throws JsonProcessingException {
        final HttpEntity<Object> entity = prepareHeaders();
        var responseEntity = restTemplate.exchange(endpoint + "outages", HttpMethod.GET, entity, String.class);
        List<OutageApiResponse> apiResponse = objectMapper.readValue(responseEntity.getBody(), new TypeReference<>() {
        });
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 01, 01,0,0,0);
        apiResponse = apiResponse.stream().filter(resp -> resp.getBegin().before(calendar.getTime())).collect(Collectors.toList());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/site-info/{siteId}")
    public ResponseEntity<SiteApiResponse> getSiteInfo(@PathVariable String siteId) throws JsonProcessingException {
        final HttpEntity<Object> entity = prepareHeaders();
        var responseEntity = restTemplate.exchange(endpoint + "site-info/" + siteId, HttpMethod.GET, entity, String.class);
        SiteApiResponse siteApiResponse = objectMapper.readValue(responseEntity.getBody(), SiteApiResponse.class);
        return new ResponseEntity<>(siteApiResponse, HttpStatus.OK);
    }

    @PostMapping("/site-outages/{siteId}")
    public ResponseEntity<String> postSiteInfo(@PathVariable String siteId, @RequestBody final List<SiteOutageApiRequest> siteOutageApiRequest) throws IOException {
        final var requestJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(siteOutageApiRequest);

        final var entity = new HttpEntity<>(requestJson, createHeader());
        var responseEntity = restTemplate.exchange(endpoint + "site-outages/" + siteId, HttpMethod.POST, entity, String.class);
        return responseEntity;
    }

    private HttpEntity<Object> prepareHeaders() {
        final HttpHeaders headers = createHeader();
        final var entity = new HttpEntity<>(headers);
        return entity;
    }

    private HttpHeaders createHeader() {
        final var headers = new HttpHeaders();
        headers.add("accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("x-api-key", apiKey);
        return headers;
    }
}
