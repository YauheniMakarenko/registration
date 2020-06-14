package com.task4.controller;

import com.task4.entity.User;
import com.task4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {


    @Autowired
    private UserService userService;
    private final String REGISTRATION = "registration";

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {


        if (bindingResult.hasErrors()) {
            return REGISTRATION;
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Password mismatch");
            return REGISTRATION;
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "A user with the same name already exists");
            return REGISTRATION;
        }

        return "redirect:/";
    }
}
