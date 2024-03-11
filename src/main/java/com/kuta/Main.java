package com.kuta;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.google.gson.Gson;
import com.kuta.pathfinding_algos.BFS;
import com.kuta.pathfinding_algos.XYNode;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        String input = readFileToString(System.getProperty("user.dir")+"/inputs/01.json");
        String[][] inputMatrix = gson.fromJson(input,String[][].class);
        int[][] numberMatrix = charMatrixToNumberMatrix(inputMatrix);
        int[][] numberedGrid = createNumberedGrid(numberMatrix.length, numberMatrix[0].length);
        printCharMatrix(inputMatrix);
        //printNumberMatrix(numberMatrix);
        //printNumberMatrix(numberedGrid);
        int[] startCoords = BFS.findStart(inputMatrix);
        List<XYNode> path = BFS.findClosestX(inputMatrix,startCoords[0],startCoords[1]);
        for(int i = 1; i < path.size() - 1; i++){
            XYNode curr = path.get(i);
            inputMatrix[curr.x][curr.y] = "o";
        }
        printCharMatrix(inputMatrix);
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
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static int[][] charMatrixToNumberMatrix(String[][] inputMatrix){

        int[][] numberMatrix = new int[inputMatrix.length][inputMatrix[0].length];
        for(int i = 0; i < numberMatrix.length; i++){
            for(int j = 0; j < numberMatrix[0].length; j++){
                if(inputMatrix[i][j].equals("#")){
                    numberMatrix[i][j] = 0;
                    continue;
                }
                if(inputMatrix[i][j].equals(".")){
                    numberMatrix[i][j] = 1;
                    continue;
                }
                if(inputMatrix[i][j].equals("x")){
                    numberMatrix[i][j] = 2;
                    continue;
                }
                if(inputMatrix[i][j].equalsIgnoreCase("s")){
                    numberMatrix[i][j] = 3;
                    continue;
                }
            }
        }
        return numberMatrix;
    }

    public static int[][] createNumberedGrid(int rows,int columns){
        int[][] grid = new int[rows][columns];
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++){
                grid[i][j] = k;
                k++;
            }
        }

        return grid;
    }

    public static String readFileToString(String filepath){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filepath));
            StringBuilder lines = new StringBuilder();
            String line = reader.readLine();
            while(line != null){
                lines.append(line);
                line = reader.readLine();
            }
            reader.close();
            return lines.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
