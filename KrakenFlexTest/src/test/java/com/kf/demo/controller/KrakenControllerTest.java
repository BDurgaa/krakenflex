package com.kf.demo.controller;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.kf.demo"})
@SpringBootTest
@AutoConfigureMockMvc
class KrakenControllerTest {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void init() {
    }

    @Test
    void getAllOutages() throws Exception {
        final var resp = new ResponseEntity<>("success", HttpStatus.OK);
        MvcResult mvcResult = mockMvc.perform(get("/outages"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        Assert.assertEquals(mvcResult.getHandler().toString(), "com.kf.demo.controller.KrakenController#getAllOutages()");
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().length() > 10);
    }

    @Test
    void getSiteInfo() throws Exception {
        final var resp = new ResponseEntity<>("success", HttpStatus.OK);
        MvcResult mvcResult = mockMvc.perform(get("/site-info/norwich-pear-tree"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        Assert.assertTrue(mvcResult.getHandler().toString().contains("com.kf.demo.controller.KrakenController#getSiteInfo"));
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains("111183e7-fb90-436b-9951-63392b36bdd2"));
    }

    @Test
    void postSiteInfo() throws Exception {
        String input = "[{\"id\": \"002b28fc-283c-47ec-9af2-ea287336dc1b\",\"name\": \"Battery 1\",\"begin\": \"2022-05-23T12:21:27.377Z\",\"end\": \"2022-11-13T02:16:38.905Z\" }]";
        final var resp = new ResponseEntity<>("success", HttpStatus.OK);
        MvcResult mvcResult = mockMvc.perform(post("/site-outages/norwich-pear-tree")
                        .accept("application/json")
                        .contentType("application/json")
                        .param("siteId", "norwich-pear-tree").content(input))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType("application/json"))
                .andReturn();

    }
}