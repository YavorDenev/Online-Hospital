package DigRz.OnlineHospital.controllers;

import DigRz.OnlineHospital.constants.Specialty;
import DigRz.OnlineHospital.dto.DoctorReg;
import DigRz.OnlineHospital.dto.PatientReg;
import DigRz.OnlineHospital.entities.User;
import DigRz.OnlineHospital.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class AuthController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = {"/register-patient"}, method = RequestMethod.GET)
    public String registerPatient(Model model){
        model.addAttribute("patientReg", new PatientReg());
        return "register-patient";
    }

    @RequestMapping(value = {"/register-patient"}, method = RequestMethod.POST)
    public String registerUserAsPatient(Model model, @Valid PatientReg patientReg, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("successMessage", "User not registered!");
            return "register-patient";
        }
        if(userDetailsService.isUserExist(patientReg.getUsername())){
            model.addAttribute("successMessage", "This username already present!");
            return "register-patient";
        }
        userDetailsService.saveUserAsPatient(patientReg);
        patientService.savePatient(patientReg);
        return "login";
    }

    @RequestMapping(value = {"/register-doctor"}, method = RequestMethod.GET)
    public String registerDoctor(Model model){
        model.addAttribute("doctorReg", new DoctorReg());
        model.addAttribute("specialtyList", Specialty.values());
        return "register-doctor";
    }

    @RequestMapping(value = {"/register-doctor"}, method = RequestMethod.POST)
    public String registerUserAsDoctor(Model model, @Valid DoctorReg doctorReg, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("successMessage", "User not registered!");
            return "register-doctor";
        }
        if(userDetailsService.isUserExist(doctorReg.getUsername())){
            model.addAttribute("successMessage", "This username already present!");
            return "register-doctor";
        }
        userDetailsService.saveUserAsDoctor(doctorReg);
        doctorService.saveDoctor(doctorReg);
        return "/index";
    }

    @RequestMapping(value = {"/register-admin"}, method = RequestMethod.GET)
    public String registerAdmin(Model model){
        model.addAttribute("user", new User());
        return "register-admin";
    }

    @RequestMapping(value = {"/register-admin"}, method = RequestMethod.POST)
    public String registerUserAsAdmin(Model model, @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("successMessage", "User not registered!");
            return "register-admin";
        }
        if(userDetailsService.isUserExist(user.getUsername())){
            model.addAttribute("successMessage", "This username already present!");
            return "register-admin";
        }
        userDetailsService.saveUserAsAdmin(user);
        return "/index";
    }

    @RequestMapping(value = {"/register-first-user"}, method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register-first-user";
    }

    @RequestMapping(value = {"/register-first-user"}, method = RequestMethod.POST)
    public String registerUser(Model model, @Valid User user , BindingResult bindingResult){if(bindingResult.hasErrors()){
        model.addAttribute("successMessage", "User not registered!");
        return "register-first-user";
    }
        if(userDetailsService.isUserExist(user.getUsername())){
            model.addAttribute("successMessage", "This username already present!");
            return "register-first-user";
        }
        userDetailsService.saveUser(user);
        return "login";
    }

}
