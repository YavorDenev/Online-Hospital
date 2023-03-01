package DigRz.OnlineHospital.controllers;

import DigRz.OnlineHospital.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping("/show")
    private String showDoctors (Model m) {
        m.addAttribute("doctorList", doctorRepository.findAll());
        return "doctor/list";
    }



}
