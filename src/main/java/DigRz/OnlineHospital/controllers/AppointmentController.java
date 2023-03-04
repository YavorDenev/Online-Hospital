package DigRz.OnlineHospital.controllers;

import DigRz.OnlineHospital.constants.Examination;
import DigRz.OnlineHospital.entities.Appointment;
import DigRz.OnlineHospital.entities.Patient;
import DigRz.OnlineHospital.entities.User;
import DigRz.OnlineHospital.repositories.AppointmentRepository;
import DigRz.OnlineHospital.repositories.DoctorRepository;
import DigRz.OnlineHospital.repositories.PatientRepository;
import DigRz.OnlineHospital.repositories.UserRepository;
import DigRz.OnlineHospital.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
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
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/show")
    private String showPatientAppointments (Model m) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //User user = userRepository.getUserByUsername((auth.getName()));
        Long id = userRepository.getUserByUsername((auth.getName())).getId();
        Patient patient = patientRepository.findById(id).get();

        m.addAttribute("appointmentList", appointmentRepository.findByPatient(patient));
        return "appointment/list";
    }

    @GetMapping("/create")
    private String createAppointment (Model m) {
        Appointment appointment = new Appointment();
        m.addAttribute("appointment", appointment);
        m.addAttribute("doctors", doctorRepository.findAll());
        m.addAttribute("examinationList", Examination.values());
        m.addAttribute("days",utils.generateListOfDays());
        m.addAttribute("hours",utils.generateListOfHours());

        return "/appointment/create";
    }
    @PostMapping("/submit")
    private String submitAppointment (@Valid Appointment appointment, BindingResult bindingResult, Model m) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.getUserByUsername((auth.getName()));
        Patient patient = patientRepository.findByUser(user);
        appointment.setPatient(patient);
        if (bindingResult.hasErrors()) {
            m.addAttribute("doctors", doctorRepository.findAll());
            m.addAttribute("examinationList", Examination.values());
            m.addAttribute("days",utils.generateListOfDays());
            m.addAttribute("hours",utils.generateListOfHours());
            return "/appointment/create";
        }
        appointmentRepository.save(appointment);
                    System.out.println("*****"+appointment.getPatient().getFirstName());
        return "redirect:/appointment/show";
    }


}
