package com.resmed.sycn.service.pocsycnservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(of = {"serialNumber"})
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    private String serialNumber;
    private String mid;
    private String vid;
}
