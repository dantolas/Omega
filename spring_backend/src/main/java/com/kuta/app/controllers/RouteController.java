package com.kuta.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for static web content
 */
@Controller
public class RouteController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/home")
    public String home(){
        return "index";
    }
}

