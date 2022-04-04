package com.asac.demo16Security.Controllers;

import com.asac.demo16Security.Models.AppUser;
import com.asac.demo16Security.Repositries.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppUserController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signupUser(@RequestParam String username,@RequestParam String password){
        AppUser appuser = new AppUser(username,encoder.encode(password));
        appUserRepository.save(appuser);
        return "login";
    }
}
