package com.example.demo.web;

import com.example.demo.domain.User;
import com.example.demo.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GreetingsController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/greeting")
    String greeting(@RequestParam(name = "name", required = false, defaultValue = "Class 401d6") String name,
                    Model model) {
        System.out.println("The name to greet is => " + name);

        model.addAttribute("name", name);
        model.addAttribute("age", 100);

        return "greeting";
    }

    @ResponseBody
    @GetMapping("/auth")
    User authentication() {
        return new User("Baraa", "password");
    }

    @ResponseBody
    @PostMapping("/users")
    User createUser(@RequestBody User user) {
        System.out.println(user);

        return userRepository.save(user);
    }

    @ResponseBody
    @GetMapping("/users/{id}")
    User getUser(@PathVariable Long id) {
        return userRepository.getById(id);
    }

    @ResponseBody
    @GetMapping("/users")
    List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        users.add(new User("John", "wick"));
        users.add(new User("Sally", "may"));
        users.add(new User("Jason", "jones"));
        users.add(new User("Morgan", "freeman"));

        return users;
    }
}
