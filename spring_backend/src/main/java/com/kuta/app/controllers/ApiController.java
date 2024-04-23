package com.kuta.app.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.kuta.app.models.ApiResponseModel;
import com.kuta.pathfinding_algos.BFS;
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
    public ApiController(Gson gson) {
        this.gson = gson;
    }

    /**
     * Test method to verify API access
     * @return ApiResponseModel containing the result of the test
     */
    @ResponseBody
    @GetMapping(value = "test", produces = "application/json")
    public ApiResponseModel test() {
        String input = "";
        try {
            input = IO.readFileIntoString(System.getProperty("user.dir") + "/src/main/resources/inputs/02.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[][] inputMatrix = gson.fromJson(input, String[][].class);
        ApiResponseModel response = BFS.solve(inputMatrix, "S", "S");
        logger.info("API TEST ACCESSED");
        return response;
    }

    private record ApiRequestData(String matrixJson, String start, String end, String algorithm) {
    };

    /**
     * Endpoint to solve a maze
     * @param data ApiRequestData containing matrix JSON, start and end characters, and algorithm
     * @return ResponseEntity containing ApiResponseModel with the solution
     */
    @ResponseBody
    @PostMapping(value = "solve", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> solve(@RequestBody ApiRequestData data) {
        if (data == null)
            return ResponseEntity.badRequest().body("Please send a valid request according to the documentation");
        if (data.start() == null || data.end() == null || data.algorithm() == null)
            return ResponseEntity.badRequest().body("Some data was missing, please send a correct request");

        String[][] charMatrix = gson.fromJson(data.matrixJson(), String[][].class);
        return ResponseEntity.ok().body(BFS.solve(charMatrix, data.start(), data.end()));
    }
}
