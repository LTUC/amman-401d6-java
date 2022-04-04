package com.asac.demo16Security.Controllers;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {

    @GetMapping("/")
    public String getHomePage(){
        return "home";
    }
}
