
package com.kuta.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for static web content
 */
@Controller
public class RouteController {

    @Autowired
    public RouteController(){
    }

	@GetMapping("/greetings")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="there :]") String name, Model model) {
        model.addAttribute("name",name);
		return "greetings";
	}
	@GetMapping("/")
	public String index(Model model) { 
		return "index";
	}

}

