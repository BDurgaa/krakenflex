package com.kf.demo.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class OutageApiResponse {

    private String id;
    private Date begin;
    private Date end;
}
