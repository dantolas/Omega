package com.kuta.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

/**
 * Controller for static web content
 */
@Controller
public class RouteController {

    private Gson gson;

    @Autowired
    public RouteController(Gson gson){
        this.gson = gson;
    }

	@GetMapping("/greetings")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="there :]") String name, Model model) {
        model.addAttribute("name",name);
		return "greetings";
	}
	@GetMapping("/")
	public String index() { 
		return "index";
	}
}

