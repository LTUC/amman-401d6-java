package com.example.shoppy.infrastructure;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Because this is tagged with a @Service
 * it can be "Autowired"
 */
@Service
public class HellosService {

    public List<String> getHellos() {
        // just a silly demo list of hellos
        List<String> list = new ArrayList<>();
        list.add("Hi");
        list.add("Good Day sir!");
        list.add("g'day mate!");
        return list;
    }
}
