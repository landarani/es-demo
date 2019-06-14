package com.resmed.sycn.service.pocsycnservice.repository;

import com.resmed.sycn.service.pocsycnservice.model.Patient;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PatientRepository extends ElasticsearchRepository<Patient, String> {
    List<Patient> findByFirstNameOrLastName(String name);
}
