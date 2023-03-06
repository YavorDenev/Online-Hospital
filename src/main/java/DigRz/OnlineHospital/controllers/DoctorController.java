package DigRz.OnlineHospital.controllers;

import DigRz.OnlineHospital.entities.Doctor;
import DigRz.OnlineHospital.repositories.AppointmentRepository;
import DigRz.OnlineHospital.repositories.DoctorRepository;
import DigRz.OnlineHospital.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/show")
    private String showDoctors (Model m) {
        m.addAttribute("doctorList", doctorRepository.findAll());
        return "doctor/list";
    }

    @PostMapping("/delete/{id}")
    private String deleteDoctor (@PathVariable(name = "id") Long id) {
        doctorRepository.deleteById(id);
        return "redirect:/doctor/show";
    }

    //----------------------------------------------------

    @GetMapping("/show-apps")
    private String showAllAppointments (Model m) {
        m.addAttribute("appointmentList", appointmentRepository.findAll());
        return "doctor/list-apps";
    }

    @GetMapping("/search_choice")
    private String searchByDoctor (Model m) {
        Doctor doctor = new Doctor();
        m.addAttribute("doctorsList",doctorRepository.findAll());
        m.addAttribute("doctor", doctor);
        return "/doctor/search_choice";
    }

    @PostMapping("/submit_choice")
    private String submitDoctorInputs (Model m, Long doctorId, int sortCriteria, int sortMethod) {
        m.addAttribute("appointmentList", doctorService.getSortedAppointments(doctorId, sortCriteria, sortMethod));
        return "/doctor/list-apps";
    }

}
