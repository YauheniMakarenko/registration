package com.task4.controller;

import com.task4.entity.Role;
import com.task4.entity.User;
import com.task4.repository.UserRepository;
import com.task4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AdminController {

    private User user;
    private final String BLOCK = "block";
    private final String DELETE = "delete";
    private final String UNBLOCK = "unblock";
    private final String UNLOCKED_USER = "ROLE_UNLOCKED";
    private final String BLOCKED_USER = "ROLE_BLOCKED";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String userList(Model model) {
        user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setLastlogin(String.valueOf(new java.sql.Timestamp(new Date().getTime()))
                .replaceAll("\\.\\d+", ""));
        user.setStatus("online");
        userRepository.save(user);
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @PostMapping("/admin")
    public String deleteUser(HttpServletRequest request,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model) {
        String[] checkboxIds = request.getParameterValues("checkboxId");
        if (checkboxIds == null) {
            return "redirect:/admin";
        }
        checkUserForContentsArray(checkboxIds);
        switch (action){
            case BLOCK:
                return blockUser(checkboxIds);
            case UNBLOCK:
                return unBlockUser(checkboxIds);
            case DELETE:
                return deleteUSer(checkboxIds);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/gt/{userId}")
    public String gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
    }

    private void checkUserForContentsArray(String[] checkbox) {
        if (Arrays.asList(checkbox).contains(Long.toString(user.getId()))) {
            int indexUser = Arrays.asList(checkbox).indexOf(String.valueOf(user.getId()));
            Collections.swap(Arrays.asList(checkbox), indexUser, checkbox.length - 1);
        }
    }

    private String deleteUSer(String[] checkbox) {
        for (String username : checkbox) {
            if (user.getId() == Long.parseLong(username)) {
                userService.deleteUser(Long.parseLong(username));
                return "redirect:/logout";
            }
            userService.deleteUser(Long.parseLong(username));
        }
        return "redirect:/admin";
    }

    private String blockUser(String[] checkbox) {
        for (String username : checkbox) {
            User userById = userService.findUserById(Long.parseLong(username));
            if (user.getId() == Long.parseLong(username)) {
                userById.getRoles().clear();
                userById.getRoles().add(new Role(2L, BLOCKED_USER));
                userRepository.save(userById);
                return "redirect:/logout";
            }
            userById.getRoles().clear();
            userById.getRoles().add(new Role(2L, BLOCKED_USER));
            userRepository.save(userById);
        }
        return "redirect:/admin";
    }

    private String unBlockUser(String[] checkbox) {
        for (String username : checkbox) {
            User userById = userService.findUserById(Long.parseLong(username));
            userById.getRoles().clear();
            userById.getRoles().add(new Role(1L, UNLOCKED_USER));
            userRepository.save(userById);
        }
        return "redirect:/admin";
    }
}