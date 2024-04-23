package com.kuta.pathfinding_algos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.kuta.app.models.ApiResponseModel;

/**
 * An implementation of the BFS algorhitm, providic static functionality to search grids
 *
 */
public abstract class BFS implements Pathfinder{

    public static ApiResponseModel solve(String[][] charMatrix,String startString, String endString){
        List<CoordinateModel> searchPath = new ArrayList<>();
        List<CoordinateModel> completePath = new ArrayList<>();
        boolean pathFound = false;
        int stopsFound = 0;
        int of;
        List<CoordinateModel> stopsNotFound = new ArrayList<>();


        List<CoordinateModel> stops = getStops(charMatrix);
        List<CoordinateModel> stopsFoundCoords = new ArrayList<>();;
        List<CoordinateModel> newPath = new ArrayList<>();
        List<List<CoordinateModel>> solvedPaths = new ArrayList<>();

        long timerStart = System.currentTimeMillis();

        CoordinateModel start = findPosition(charMatrix,startString);
        completePath.add(start);
        while(true){
            if(stopsFoundCoords.size() == stops.size()) break;
            solvedPaths = getPathToClosest(charMatrix, start.x(), start.y(),"x",stopsFoundCoords);
            newPath = solvedPaths.get(0);
            if(newPath.size() == 0) break;
            searchPath.addAll(solvedPaths.get(1));
            completePath.addAll(newPath);
            start = newPath.getLast();
            stopsFoundCoords.add(start);
        }
    
        stopsFound = stopsFoundCoords.size();
        of = stops.size();
        for (CoordinateModel stop : stops) {
            if(stopsFoundCoords.contains(stop)) continue;
            stopsNotFound.add(stop);
        }
        

        solvedPaths = getPathToClosest(charMatrix,start.x(),start.y(),endString,stopsFoundCoords);
        newPath = solvedPaths.get(0);
        searchPath.addAll(solvedPaths.get(1));
        completePath.addAll(newPath);
        pathFound = pathComplete(charMatrix,completePath,startString,endString);

        long finalTime = (System.currentTimeMillis() - timerStart);
        ApiResponseModel solved = new ApiResponseModel(pathFound, stopsFound, of, stopsNotFound, completePath, searchPath, finalTime);
        return solved;
    }

    public static List<List<CoordinateModel>>
    getPathToClosest(String[][] charMatrix,int startX,int startY,String find, List<CoordinateModel> ignoreList){
        Queue<Integer> rowq = new LinkedList<>();
        Queue<Integer> colq = new LinkedList<>();
        int rows = charMatrix.length;
        int cols = charMatrix[0].length;
        boolean[][] visited = new boolean[rows][cols];

        int nodesCurrentLayer = 1;
        int nodesNextLayer = 0;

        rowq.add(startX);
        colq.add(startY);

        Map<CoordinateModel,CoordinateModel> parentCoordinateModels = new HashMap<>();
        // Direction vectors : NORTH,SOUTH,EAST,WEST
        int[] rowDirections = {-1,1,0,0}; 
        int[] colDirections = {0,0,1,-1};
        int row;
        int col;
        int checkX;
        int checkY;
        List<CoordinateModel> searchPath = new ArrayList<>();
        ArrayList<CoordinateModel> path = new ArrayList<>();
        @SuppressWarnings("unused") //LSP Couldn't see the variable used 36 lines below
        int moves = 0;
        while(rowq.size() > 0){
            row = rowq.poll();
            col = colq.poll();
            if(visited[row][col]) continue;
            visited[row][col] = true;
            CoordinateModel current = new CoordinateModel(row,col);
            searchPath.add(current);
            if(charMatrix[row][col].equals(find) && !ignoreList.contains(current)){
                for(CoordinateModel parent = new CoordinateModel(row,col); parent != null; parent = parentCoordinateModels.get(parent)){
                    if(parent.x() == startX && parent.y() == startY) continue;
                    path.add(parent);
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

                rowq.add(checkX);
                colq.add(checkY);
                CoordinateModel nextInLine = new CoordinateModel(checkX,checkY);
                parentCoordinateModels.put(nextInLine, current);
                nodesNextLayer ++;

            }
            nodesCurrentLayer --;
            if(nodesCurrentLayer >  0) continue;
            nodesCurrentLayer = nodesNextLayer;
            nodesNextLayer = 0;
            moves++;
        }
        List<List<CoordinateModel>> pathAndVisited = new ArrayList<>();
        pathAndVisited.add(path);
        pathAndVisited.add(searchPath);
        return pathAndVisited;
    }

    public static List<CoordinateModel> getStops(String[][] charMatrix){
        List<CoordinateModel> stops = new ArrayList<>();
        for (int i = 0; i < charMatrix.length; i++) {
            for(int j = 0; j < charMatrix[i].length; j++){
                if(charMatrix[i][j].equals("x")) stops.add(new CoordinateModel(i, j));
            }
        }
        return stops;
    };

    public static List<CoordinateModel> getStopsCoords(String[][] charMatrix){
        List<CoordinateModel> positions = new ArrayList<>();
        for (int i = 0; i < charMatrix.length; i++) {
            for(int j = 0; j < charMatrix[i].length; j++){
                if(!charMatrix[i][j].equalsIgnoreCase("x")) continue;
                positions.add(new CoordinateModel(i, j));
            }
        }
        return positions;
    }

    public static boolean pathComplete(String[][] inputMatrix,List<CoordinateModel> path, String start, String end){
        if(path.size() == 0) return false;
        String pathStart = inputMatrix[path.getFirst().x()][path.getFirst().y()];
        String pathEnd = inputMatrix[path.getLast().x()][path.getLast().y()];
        return (pathStart.equals(start) && pathEnd.equals(end));
    }
    
    public static CoordinateModel findPosition(String[][] charMatrix,String value){
        for (int i = 0; i < charMatrix.length; i++) {
            for(int j = 0; j < charMatrix[i].length; j++){
                if(charMatrix[i][j].equals(value)) return new CoordinateModel(i,j);
            }
        }
        return null;
    }
}
