package com.resmed.sycn.service.pocsycnservice.controller;

import com.resmed.sycn.service.pocsycnservice.model.Alert;
import com.resmed.sycn.service.pocsycnservice.model.Condition;
import com.resmed.sycn.service.pocsycnservice.model.Device;
import com.resmed.sycn.service.pocsycnservice.model.Patient;
import com.resmed.sycn.service.pocsycnservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/patient")
public class DataInsertionController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private ElasticsearchTemplate esTemplate;

    @PostMapping("/createBuildPatients")
    public String savePatient(@RequestParam("start") int start, @RequestParam("end") int end,
                              @RequestParam("condition") String condition, @RequestParam("alertName") String alertName) {
        Patient patient = new Patient();
        Alert alert = new Alert();
        Condition patientCondition = new Condition();
        Device device = new Device();

        patient.setAlerts(new ArrayList<>());
        patient.setDevices(new ArrayList<>());
        patientCondition.setCondition(condition);
        patient.setCondition(patientCondition);
        alert.setAlertName(alertName);

        for (int i = start; i < end; i++) {
            patient.setAlerts(new ArrayList<>());
            patient.setDevices(new ArrayList<>());
            patient.setEcn("ecn" + i);
            patient.setFirstName("firstName" + i);
            patient.setLastName("lastName" + i);
            patient.getAlerts().add(alert);
            device.setSerialNumber("serialNumber" + i + "");
            patient.getDevices().add(device);
            patientService.save(patient);
        }
        return "Records saved in the db.";
    }

    @PostMapping("/createIndex")
    public String savePatient() {
        esTemplate.createIndex(Patient.class);
        esTemplate.putMapping(Patient.class);
        esTemplate.refresh(Patient.class);

        return "Patient index created";
    }
}
