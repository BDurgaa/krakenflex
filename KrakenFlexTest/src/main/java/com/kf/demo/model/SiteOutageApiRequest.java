package com.kf.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SiteOutageApiRequest {

    private String id;
    private String name;
    private Date begin;
    private Date end;
}