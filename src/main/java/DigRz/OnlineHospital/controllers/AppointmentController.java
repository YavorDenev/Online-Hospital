package DigRz.OnlineHospital.controllers;

import DigRz.OnlineHospital.entities.Appointment;
import DigRz.OnlineHospital.repositories.AppointmentRepository;
import DigRz.OnlineHospital.repositories.DoctorRepository;
import DigRz.OnlineHospital.repositories.PatientRepository;
import DigRz.OnlineHospital.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private Utils utils;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/create")
    private String createAppointment (Model m) {
        Appointment appointment = new Appointment();
        m.addAttribute("appointment", appointment);
        m.addAttribute("doctors", doctorRepository.findAll());
        ArrayList days = new ArrayList<String>();
        days.add("2022-12-12");
        m.addAttribute("days",days);
        m.addAttribute("hours",utils.generateListOfHours());

        return "/appointment/create";
    }

}
