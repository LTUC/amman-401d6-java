package com.example.shoppy.web;

import com.example.shoppy.infrastructure.HellosService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hello")
public class HelloController {

    // @Autowired will create things as needed
    // This is just an example of a service
    // The HellosService has an @Service annotation
    // making this work
    private final HellosService service;

    public HelloController(HellosService service) {
        this.service = service;
    }

    @GetMapping("/")  // http://localhost:8080/hello/
    public String getHello() {
        // will load the hello template!
        // the hello template has a form to posthello
        return "hello";
    }

    // This handles posts from the hello
    @PostMapping("/posthello")
    @ResponseBody
    // http://localhost/hello/posthello?myname=bob&mynumber=test
    public String postHello(
            @RequestParam String myname,
            @RequestParam int mynumber
    ) {

        // @RequestParam works for both form fields
        // and query parameters (The ?whatever=value in the url)

        // our service is available!
        // Let's get a greeting
        List<String> list = this.service.getHellos();
        String msg = list.get(0);

        // because we have an @ResponseBody
        // this just returns as a string for testing
        return msg + " " + myname + mynumber;
    }

}
