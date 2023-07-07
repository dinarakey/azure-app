package com.demo.azureapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AzureRestController {

    @GetMapping("/hi")
    public String greetings(){
        return "greetings!";
    }
}
