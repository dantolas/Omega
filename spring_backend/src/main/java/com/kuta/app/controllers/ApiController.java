package com.kuta.app.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.kuta.pathfinding_algos.BFS;
import com.kuta.pathfinding_algos.XYNode;
import com.kuta.util.IO;
import com.kuta.util.MatrixPrint;

/**
 * ApiController
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    Gson gson;

    @Autowired
    public ApiController(Gson gson){
        this.gson = gson;
    }

    @ResponseBody
    @GetMapping(value = "test",produces = "application/json")
    public Object test(){

        String input = "";
        try {
            input = IO.readFileIntoString(System.getProperty("user.dir")+"/src/main/resources/inputs/02.json");
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
        MatrixPrint.printCharMatrix(inputMatrix);
        return "";
    }

    @ResponseBody
    @PostMapping(value = "solve",produces = "application/json")
    public Object solve(){
        return null;
    }
}
