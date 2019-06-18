package com.resmed.sycn.service.pocsycnservice.service;

import com.resmed.sycn.service.pocsycnservice.Application;
import com.resmed.sycn.service.pocsycnservice.model.Alert;
import com.resmed.sycn.service.pocsycnservice.model.Condition;
import com.resmed.sycn.service.pocsycnservice.model.Device;
import com.resmed.sycn.service.pocsycnservice.model.Patient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Before
    public void before() {
        esTemplate.deleteIndex(Patient.class);
        esTemplate.createIndex(Patient.class);
        esTemplate.putMapping(Patient.class);
        esTemplate.refresh(Patient.class);
    }

    @Test
    public void testSave() {



            List<Alert> alerts = Collections.singletonList(Alert.builder().alertName("An Alert").build());
            Condition condition = Condition.builder().condition("COPD").build();
            List<Device> devices = Collections.singletonList(Device.builder().serialNumber("1234567891").build());

            Patient patient = Patient.builder()
                    .ecn("patient-ecn-")
                    .firstName("first")
                    .lastName("last")
                    .alerts(alerts)
                    .condition(condition)
                    .devices(devices)
                    .build();

            Patient testPatient = patientService.save(patient);


        assertNotNull(testPatient.getEcn());
        assertEquals(testPatient.getFirstName(), patient.getFirstName());
        assertEquals(testPatient.getAlerts(), alerts);
        assertEquals(testPatient.getCondition(), condition);
        assertEquals(testPatient.getDevices(), devices);


    }



}