package DigRz.OnlineHospital.controllers;

import DigRz.OnlineHospital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/show")
    private String showPatients (Model m) {
        m.addAttribute("patientList", patientRepository.findAll());
        return "patient/list";
    }



}
