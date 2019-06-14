package com.resmed.sycn.service.pocsycnservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class Alert {
    private String id;
    private Date raisedDateTime;
    private Date closedDateTime;
    private String closedBy;
    private String status;
    private String message;
    private String alertName;
}
