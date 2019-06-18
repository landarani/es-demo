package com.resmed.sycn.service.pocsycnservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "patient", type = "patients")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    private String ecn;
    private String firstName;
    private String lastName;

    private List<Device> devices;
    private List<Alert> alerts;
    private Condition condition;
}
