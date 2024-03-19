package com.kuta.pathfinding_algos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * An implementation of the BFS algorhitm, providic static functionality to search grids
 *
 */
public abstract class BFS {


    public static List<XYNode> findCompletePathChain(String[][] charMatrix,String[] chars){

        if(chars.length != 3){
            return null;
        }

        List<XYNode> lootPositions = new ArrayList<>();;
        List<XYNode> completePath = new ArrayList<>();
        List<XYNode> newPath = new ArrayList<>();
        XYNode start = findPosition(charMatrix,chars[0]);
        while(true){
            newPath = findClosest(charMatrix, start.x, start.y,chars[2],lootPositions);
            if(newPath.size()==0)break;
            completePath.addAll(newPath);
            start = newPath.getLast();
            lootPositions.add(start);
        }
        newPath = findClosest(charMatrix,start.x,start.y,chars[1],lootPositions);
        completePath.addAll(newPath);
        return completePath;
    }

    public static List<XYNode> findClosest(String[][] charMatrix,int startX,int startY,String lootCharacter, List<XYNode> ignoreList){
        Queue<Integer> rowq = new LinkedList<>();
        Queue<Integer> colq = new LinkedList<>();
        int rows = charMatrix.length;
        int cols = charMatrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        boolean[][] inQ = new boolean[rows][cols];
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
        ArrayList<XYNode> path = new ArrayList<>();
        while(rowq.size() > 0){
            row = rowq.poll();
            col = colq.poll();
            XYNode parent = new XYNode(row,col);
            visited[row][col] = true;
            if(charMatrix[row][col].equals(lootCharacter) && !ignoreList.contains(parent)){

                for(XYNode at = new XYNode(row,col); at != null; at = parentXYNodes.get(at)){
                    path.add(at);
                }
                path = new ArrayList<>(path.reversed());
                break;
            }
            for(int i = 0; i < 4; i++){
                checkX = row+rowDirections[i];
                checkY = col+colDirections[i];

                if((checkX < 0 || checkY < 0) || (checkX >= rows || checkY >= cols) ) continue;
                if(charMatrix[checkX][checkY].equals("#")) continue;
                if(visited[checkX][checkY]) continue;

                if(inQ[checkX][checkY]) continue;
                rowq.add(checkX);
                colq.add(checkY);
                inQ[checkX][checkY] = true;
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
        return path;
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

    public static List<XYNode> getXs(String[][] charMatrix){
        List<XYNode> positions = new ArrayList<>();
        for (int i = 0; i < charMatrix.length; i++) {
            for(int j = 0; j < charMatrix[i].length; j++){
                if(!charMatrix[i][j].equalsIgnoreCase("x")) continue;
                positions.add(new XYNode(i, j));
            }
        }
        return positions;
    }
    
    public static XYNode findPosition(String[][] charMatrix,String startChar){
        for (int i = 0; i < charMatrix.length; i++) {
            for(int j = 0; j < charMatrix[i].length; j++){
                if(charMatrix[i][j].equals(startChar)) return new XYNode(i,j);
            }
        }
        return null;
    }
}
