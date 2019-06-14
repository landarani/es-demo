package com.resmed.sycn.service.pocsycnservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(of = {"condition"})
@NoArgsConstructor
@AllArgsConstructor
public class Condition {
    private String condition;
}
