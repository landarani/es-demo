package com.resmed.sycn.service.pocsycnservice.repository;

import com.resmed.sycn.service.pocsycnservice.model.Patient;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PatientRepository extends ElasticsearchRepository<Patient, String> {
    List<Patient> findByFirstNameOrLastName(String name);


    @Query("{\n" +
            "                \"bool\" : {\n" +
            "                \"should\" : [\n" +
            "                    { \"match\" : {\"ecn\" : \"?0\"} },\n" +
            "                    { \"match\" : {\"conditions.condition\" : \"?1\"} },\n" +
            "                    { \"match\" : {\"alerts.alertName\" : \"?2\"} }\n" +
            "                    \n" +
            "                    ]\n" +
            "                }\n" +
            "            }")
    List<Patient> findByQuery(String ecn, String condition, String alertName);
}
