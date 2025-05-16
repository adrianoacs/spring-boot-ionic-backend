package com.nelioalves.cursomc.cursomc.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/")
    public String home() {
        return "API is running!";
    }
}
