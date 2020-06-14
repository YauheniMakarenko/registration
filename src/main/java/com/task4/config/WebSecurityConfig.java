package com.task4.config;


import com.task4.service.CustomLoginSuccessHandler;
import com.task4.service.CustomLogoutSuccessHandler;
import com.task4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/registration").not().fullyAuthenticated()
                .antMatchers("/admin/**").hasRole("UNLOCKED")
                .antMatchers("/", "/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(customLoginSuccessHandler)
                .and()
                .logout()
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .permitAll()
                .logoutSuccessUrl("/");
        ;
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}
