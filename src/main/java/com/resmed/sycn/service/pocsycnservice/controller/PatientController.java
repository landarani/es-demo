package com.resmed.sycn.service.pocsycnservice.controller;

import com.resmed.sycn.service.pocsycnservice.model.Alert;
import com.resmed.sycn.service.pocsycnservice.model.Condition;
import com.resmed.sycn.service.pocsycnservice.model.Device;
import com.resmed.sycn.service.pocsycnservice.model.Patient;
import com.resmed.sycn.service.pocsycnservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= "/patient")
public class PatientController {
    @Autowired
    PatientService patientService;

    /**
     * Method to save the book in the database.
     * @param book
     * @return
     */
    @PostMapping(value= "/save")
    public String savePatient(@RequestBody Patient patient) {
        patientService.save(patient);
        return "Records saved in the db.";
    }

    @PostMapping
    public String addNewPatient(@RequestBody Patient patient) {
        patientService.addPatient(patient);
        return "New Patient records saved in the db.";
    }

    @PostMapping(value= "/{ecn}/alert")
    public String addPatientAlert(@PathVariable("ecn") String ecn, @RequestBody Alert alert) {
        patientService.addPatientAlert(ecn, alert);
        return "Records saved in the db.";
    }

    @PostMapping(value= "/{ecn}/condition")
    public String addPatientCondition(@PathVariable("ecn") String ecn, @RequestBody Condition condition) {
        patientService.addPatientCondition(ecn, condition);
        return "Records saved in the db.";
    }

    @PostMapping(value= "/{ecn}/device")
    public String addPatientDevice(@PathVariable("ecn") String ecn, @RequestBody Device device) {
        patientService.addPatientDevice(ecn, device);
        return "Records saved in the db.";
    }

    @DeleteMapping(value= "/{ecn}/alert")
    public String removePatientAlert(@PathVariable("ecn") String ecn, @RequestBody Alert alert) {
        patientService.removePatientAlert(ecn, alert);
        return "Records removed from db.";
    }

    @DeleteMapping(value= "/{ecn}/condition")
    public String removePatientCondition(@PathVariable("ecn") String ecn, @RequestBody Condition condition) {
        patientService.removePatientCondition(ecn, condition);
        return "Records removed from db.";
    }

    @DeleteMapping(value= "/{ecn}/device")
    public String removePatientDevice(@PathVariable("ecn") String ecn, @RequestBody Device device) {
        patientService.removePatientDevice(ecn, device);
        return "Records removed from db.";
    }

    /**
     * Method to fetch all books from the database.
     * @return
     */
    @GetMapping(value= "/findAll")
    public Iterable<Patient> getAllPatients() {
        return patientService.findAll();
    }

    /**
     * Method to fetch the book details on the basis of designation.
     * @param title
     * @return
     */
    @GetMapping(value= "/findByName/{name}")
    public Iterable<Patient> getByName(@PathVariable(name= "name") String name) {
        return patientService.findByName(name);
    }

    @GetMapping(value= "/query")
    public Iterable<Patient> getByQuery(@RequestParam(name= "q") String q) {
        return patientService.findByQuery(q);
    }
}