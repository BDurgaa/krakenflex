package com.kf.demo.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SiteApiResponse {

    private String id;
    private String name;
    private List<Device> devices;
}
