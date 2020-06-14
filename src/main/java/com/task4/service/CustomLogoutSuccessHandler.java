package com.task4.service;

import com.task4.entity.User;
import com.task4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();
        if( userRepository.findByUsername(user.getUsername())!=null){
            user.setStatus("offline");
            userRepository.save(user);
        }
        response.sendRedirect( "/login");
    }
}
