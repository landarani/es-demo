package com.resmed.sycn.service.pocsycnservice.service;

import com.resmed.sycn.service.pocsycnservice.model.Alert;
import com.resmed.sycn.service.pocsycnservice.model.Condition;
import com.resmed.sycn.service.pocsycnservice.model.Device;
import com.resmed.sycn.service.pocsycnservice.model.Patient;
import com.resmed.sycn.service.pocsycnservice.repository.PatientRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient addPatient(Patient patient) {
        Patient existingPatient = findByEcn(patient.getEcn());
        if (existingPatient != null ) {
            existingPatient.setFirstName(patient.getFirstName());
            existingPatient.setLastName(patient.getLastName());
            return save(existingPatient);
        } else {
            return save(patient);
        }
    }

    public Iterable<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Iterable<Patient> findByQuery(String q){
        return patientRepository.search(QueryBuilders.queryStringQuery(q));
    }

    public void addPatientAlert (String ecn, Alert alert) {
        Patient patient = findByEcn(ecn);
        if (patient != null ) {
            if (patient.getAlerts() == null) {
                patient.setAlerts(new ArrayList<>());
            }

            if (patient.getAlerts().contains(alert)) {
                patient.getAlerts().remove(alert);
            }

            patient.getAlerts().add(alert);
            save(patient);
        }
    }

    public void addPatientCondition (String ecn, Condition condition) {
        Patient patient = findByEcn(ecn);
        if (patient != null ) {
            if (patient.getConditions() == null) {
                patient.setConditions(new ArrayList<>());
            }

            if (patient.getConditions().contains(condition)) {
                patient.getConditions().remove(condition);
            }


            patient.getConditions().add(condition);
            save(patient);
        }
    }

    public void addPatientDevice (String ecn, Device device) {
        Patient patient = findByEcn(ecn);
        if (patient != null ) {
            if (patient.getDevices() == null) {
                patient.setDevices(new ArrayList<>());
            }

            if (patient.getDevices().contains(device)) {
                patient.getDevices().remove(device);
            }

            patient.getDevices().add(device);
            save(patient);
        }
    }

    public void removePatientAlert (String ecn, Alert alert) {
        Patient patient = findByEcn(ecn);
        if (patient != null ) {
            if (patient.getAlerts() != null) {
                patient.getAlerts().remove(alert);
            save(patient);
            }
        }
    }

    public void removePatientCondition (String ecn, Condition condition) {
        Patient patient = findByEcn(ecn);
        if (patient != null ) {
            if (patient.getConditions() != null) {
                patient.getConditions().remove(condition);
                save(patient);
            }
        }
    }

    public void removePatientDevice (String ecn, Device device) {
        Patient patient = findByEcn(ecn);
        if (patient != null ) {
            if (patient.getDevices() != null) {
                patient.getDevices().remove(device);
                save(patient);
            }
        }
    }


    public Patient findByEcn(String id) { return patientRepository.findById(id).orElse(null); }

    public List<Patient> findByName(String name) { return patientRepository.findByFirstNameOrLastName(name); }

    public List<Patient> findByQuery(String ecn, String condition, String alertName){
        return patientRepository.findByQuery(ecn, condition, alertName);
    }
}
