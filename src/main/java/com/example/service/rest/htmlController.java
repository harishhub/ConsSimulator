package com.example.service.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class htmlController {

    @RequestMapping(path = "/publishToCons", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

}
