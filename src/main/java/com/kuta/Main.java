package com.kuta;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.kuta.pathfinding_algos.BFS;
import com.kuta.pathfinding_algos.XYNode;
import com.kuta.util.ColorMe;
import com.kuta.util.IO;

/**
 * Main
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
        Gson gson = new Gson();
        String input = "";
        try {
            input = IO.readFileIntoString(System.getProperty("user.dir")+"/src/main/resources/inputs/01.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[][] inputMatrix = gson.fromJson(input,String[][].class);
        List<XYNode> path = BFS.findCompletePathChain(inputMatrix,new String[] {"S","S","x"});
        for(int i = 1; i < path.size(); i++){
            XYNode curr = path.get(i);
            if(!inputMatrix[curr.x][curr.y].equalsIgnoreCase(".")) continue;
            inputMatrix[curr.x][curr.y] = "o";
        }
        printCharMatrix(inputMatrix);
        System.out.println("Complete path size:"+path.size());
        System.out.println("Ending position:"+path.getLast());
    }


    public static void printCharMatrix(String[][] matrix){

        System.out.print("  ");
        for(int i = 0; i < matrix[0].length; i++){
            if(i%10==0){
                System.out.print(i/10);
                continue;
            }
            System.out.print(" ");
        }
        System.out.println();
        System.out.print("  ");
        for(int i = 0; i < matrix[0].length; i++){
            System.out.print(i%10);
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(i+"|");
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j].equalsIgnoreCase("x")){
                    System.out.print(ColorMe.red(matrix[i][j]));
                    continue;
                }
                if(matrix[i][j].equalsIgnoreCase("S")){
                    System.out.print(ColorMe.red(matrix[i][j]));
                    continue;
                }
                if(matrix[i][j].equalsIgnoreCase("o")){
                    System.out.print(ColorMe.green(matrix[i][j]));
                    continue;
                }
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
