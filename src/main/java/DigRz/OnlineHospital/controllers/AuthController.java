package DigRz.OnlineHospital.controllers;

import DigRz.OnlineHospital.entities.User;
import DigRz.OnlineHospital.services.UserDetailsServiceImpl;
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


    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String registerUser(Model model, @Valid User user , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("successMessage", "User not registered!");
            return "register";
        }
        if(userDetailsService.isUserExist(user)){
            model.addAttribute("successMessage", "This username already present!");
            return "register";
        }
        userDetailsService.saveUser(user);
        return "login";
    }

}
