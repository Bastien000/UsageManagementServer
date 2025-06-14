package com.viennapoint.viennapointserver.controller;

import com.viennapoint.viennapointserver.service.addService.AddService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
public class TestController {

    final private AddService addService;

    public TestController(AddService addService) {
        this.addService = addService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Helo";
    }
    @GetMapping("/getData")
    public String getData() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://192.168.1.137:5000/getData";
            String response = restTemplate.getForObject(url, String.class);
            System.out.println("Data z requestu: " + response);
            addService.addData(response, new Date());

            return "jaj";
        } catch (Exception e) {
            System.err.println("Chyba při requestu: " + e.getMessage());
            return "Chyba při získávání dat: " + e.getMessage();
        }
}}