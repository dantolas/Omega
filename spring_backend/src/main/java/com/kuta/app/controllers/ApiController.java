package com.kuta.app.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.kuta.app.models.ApiResponseModel;
import com.kuta.pathfinding_algos.BFS;
import com.kuta.pathfinding_algos.CoordinateModel;
import com.kuta.util.ColorMe;
import com.kuta.util.IO;

/**
 * ApiController
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    Logger logger = LoggerFactory.getLogger(ApiController.class);

    Gson gson;

    @Autowired
    public ApiController(Gson gson){
        this.gson = gson;
    }

    @ResponseBody
    @GetMapping(value = "test",produces = "application/json")
    public ApiResponseModel test(){

        String input = "";
        try {
            input = IO.readFileIntoString(System.getProperty("user.dir")+"/src/main/resources/inputs/02.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[][] inputMatrix = gson.fromJson(input,String[][].class);
        ApiResponseModel response = BFS.solve(inputMatrix, "S", "S"); 
        logger.info("API TEST ACCESSED");
        printMatrix(response.path(), inputMatrix);
        return response;
    }


    private record ApiRequestData(String matrixJson,String start,String end,String algorhitm){};
    @ResponseBody
    @PostMapping(value = "solve",produces = "application/json",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> solve(@RequestBody ApiRequestData data){
        if(data == null) return ResponseEntity.badRequest().body("Please send a valid request according to the documentation");
        if(data.start() == null || data.end() == null || data.algorhitm() == null)
        return ResponseEntity.badRequest().body("Some data was missing, please send a correct request");

        String[][] charMatrix = gson.fromJson(data.matrixJson(),String[][].class);
        return ResponseEntity.ok().body(BFS.solve(charMatrix, data.start(), data.end()));
    }

    public void printMatrix(List<CoordinateModel> path,String[][] inputMatrix){
        for(int i = 1; i < path.size(); i++){
            CoordinateModel curr = path.get(i);
            if(!inputMatrix[curr.x()][curr.y()].equalsIgnoreCase(".")) continue;
            inputMatrix[curr.x()][curr.y()] = "o";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < inputMatrix.length; i++) {
            builder.append("\n");
            for (int j = 0; j < inputMatrix[i].length; j++) {
                String value = inputMatrix[i][j];
                switch (value) {
                    case "x":
                    builder.append(ColorMe.yellow(value));
                        break;
                    case "S":
                    builder.append(ColorMe.purple(value));
                        break;
                    case "E":
                    builder.append(ColorMe.purple(value));
                        break;
                    case ".":
                    builder.append(ColorMe.green(value));
                        break;
                    case "S/E":
                    builder.append(ColorMe.purple(value));
                        break;

                    default:
                    builder.append(value);
                        break;
                }
            }
        }
    }
}
