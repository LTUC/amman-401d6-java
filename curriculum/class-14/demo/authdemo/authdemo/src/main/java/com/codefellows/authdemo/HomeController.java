package com.codefellows.authdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController
{
    @GetMapping("/")
    public String getHomepage()
    {
        return "index.html";
    }

    @GetMapping("/withSecret")
    public String getHomepageWithSecret(HttpServletRequest request, Model m)
    {
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();

        m.addAttribute("username", username);

        return "indexWithSecret.html";
    }
}
