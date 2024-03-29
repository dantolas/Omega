package com.kuta.app.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.kuta.pathfinding_algos.BFS;
import com.kuta.pathfinding_algos.XYNode;
import com.kuta.util.IO;

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
    @GetMapping("/visualizer")
    public String visual(Model model){

        String input = "";
        try {
            input = IO.readFileIntoString(System.getProperty("user.dir")+"/src/main/resources/inputs/01.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[][] inputMatrix = gson.fromJson(input,String[][].class);
        List<XYNode> path = BFS.findCompletePathChain(inputMatrix, "S","S","x");
        for(int i = 1; i < path.size(); i++){
            XYNode curr = path.get(i);
            if(!inputMatrix[curr.x][curr.y].equalsIgnoreCase(".")) continue;
            inputMatrix[curr.x][curr.y] = "o";
        }
        model.addAttribute("matrix",inputMatrix);
        return "visualizer";
    }

}

