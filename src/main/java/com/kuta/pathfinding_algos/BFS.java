package com.kuta.pathfinding_algos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

/**
 * BFS
 */
public abstract class BFS {


    public static List<XYNode> findCompletePath(String[][] charMatrix){

        List<XYNode> completePath = new ArrayList<>();
        for(int i = 0; i < countXs(charMatrix); i++){
        }

        return null;
    }
    public static List<XYNode> findClosestX(String[][] charMatrix,int startX,int startY){
        Queue<Integer> rowq = new LinkedList<>();
        Queue<Integer> colq = new LinkedList<>();
        int rows = charMatrix.length;
        int cols = charMatrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        visited[startX][startY] = true;

        int moves = 0;
        int nodesCurrentLayer = 1;
        int nodesNextLayer = 0;

        rowq.add(startX);
        colq.add(startY);
        visited[startX][startY] = true;

        Map<XYNode,XYNode> parentXYNodes = new HashMap<>();
        // Direction vectors : NORTH,SOUTH,EAST,WEST
        int[] rowDirections = {-1,1,0,0}; 
        int[] colDirections = {0,0,1,-1};
        int row;
        int col;
        int checkX;
        int checkY;
        while(rowq.size() > 0){
            row = rowq.poll();
            col = colq.poll();
            XYNode parent = new XYNode(row,col);
            visited[row][col] = true;
            if(charMatrix[row][col].equals("x")){
                System.out.println("Finished, path found in :"+moves+" moves");
                System.out.println("ENDPOINT:"+row+" "+col);
                ArrayList<XYNode> path = new ArrayList<>();
                for(XYNode at = new XYNode(row,col); at != null; at = parentXYNodes.get(at)){
                    path.add(at);
                }
                System.out.println("Path length:"+path.size());
                return path;
            }
            for(int i = 0; i < 4; i++){
                checkX = row+rowDirections[i];
                checkY = col+colDirections[i];

                if((checkX < 0 || checkY < 0) || (checkX >= rows || checkY >= cols) ) continue;
                if(charMatrix[checkX][checkY].equals("#")) continue;
                if(visited[checkX][checkY]) continue;

                rowq.add(checkX);
                colq.add(checkY);
                XYNode child = new XYNode(checkX,checkY);
                parentXYNodes.put(child, parent);
                nodesNextLayer ++;

            }
            nodesCurrentLayer --;
            if(nodesCurrentLayer >  0) continue;
            nodesCurrentLayer = nodesNextLayer;
            nodesNextLayer = 0;
            moves++;
        }
        return new ArrayList<>();
    }

    public static int countXs(String[][] charMatrix){
        int counter = 0;
        for (int i = 0; i < charMatrix.length; i++) {
            for(int j = 0; j < charMatrix[i].length; j++){
                if(charMatrix[i][j].equals("x")) counter++;
            }
        }
        return counter;
    };
    
    public static int[] findStart(String[][] charMatrix){
        for (int i = 0; i < charMatrix.length; i++) {
            for(int j = 0; j < charMatrix[i].length; j++){
                if(charMatrix[i][j].equals("S")) return new int[]{i,j};
            }
        }
        return null;
    }
}
