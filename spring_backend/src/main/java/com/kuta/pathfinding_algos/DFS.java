package com.kuta.pathfinding_algos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import com.kuta.app.models.ApiResponseModel;

public abstract class DFS implements Pathfinder {

public static ApiResponseModel solve(String[][] charMatrix, String startString, String endString) {
    List<CoordinateModel> searchPath = new ArrayList<>();
    List<CoordinateModel> completePath = new ArrayList<>();
    boolean pathFound = false;
    List<CoordinateModel> stops = getStops(charMatrix);

    long timerStart = System.currentTimeMillis();

    CoordinateModel start = findPosition(charMatrix, startString);
    pathFound = dfs(charMatrix, start, endString, searchPath, completePath);

    int stopsFound = stops.size();
    int of = stopsFound;

    List<CoordinateModel> stopsNotFound = new ArrayList<>();
    for (CoordinateModel stop : stops) {
        if (!completePath.contains(stop)) {
            stopsNotFound.add(stop);
        }
    }

    long finalTime = (System.currentTimeMillis() - timerStart);
    ApiResponseModel solved = new ApiResponseModel(pathFound, stopsFound - stopsNotFound.size(), of, stopsNotFound, completePath, searchPath, finalTime);
    return solved;
}

private static boolean dfs(String[][] charMatrix, CoordinateModel current, String endString, List<CoordinateModel> searchPath, List<CoordinateModel> completePath) {
    int rows = charMatrix.length;
    int cols = charMatrix[0].length;
    int x = current.x();
    int y = current.y();

    if (charMatrix[x][y].equals(endString)) {
        completePath.add(current);
        return true;
    }

    searchPath.add(current);
    charMatrix[x][y] = "#"; // Mark current cell as visited

    List<CoordinateModel> neighbors = getNeighbors(charMatrix, x, y);
    for (CoordinateModel neighbor : neighbors) {
        if (dfs(charMatrix, neighbor, endString, searchPath, completePath)) {
            completePath.add(current); // Add current cell to complete path
            return true;
        }
    }
    return false;
}

public static List<CoordinateModel> getNeighbors(String[][] charMatrix, int x, int y) {
    List<CoordinateModel> neighbors = new ArrayList<>();
    int rows = charMatrix.length;
    int cols = charMatrix[0].length;

    // Define the possible directions: UP, DOWN, LEFT, RIGHT
    int[] rowDirections = {-1, 1, 0, 0};
    int[] colDirections = {0, 0, -1, 1};

    for (int i = 0; i < 4; i++) {
        int newX = x + rowDirections[i];
        int newY = y + colDirections[i];
        // Check if the new position is within the bounds of the maze
        if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !charMatrix[newX][newY].equals("#")) {
            neighbors.add(new CoordinateModel(newX, newY));
        }
    }
    return neighbors;
}

    /**
     * Finds the path to the closest target from a given starting point
     * @param charMatrix The character matrix representing the maze
     * @param startX The starting X coordinate
     * @param startY The starting Y coordinate
     * @param find The target character to find
     * @param ignoreList List of coordinates to ignore
     * @return List containing the path to the closest target and the visited coordinates
     */
public static List<List<CoordinateModel>> getPathToClosest(String[][] charMatrix, int startX, int startY, String find, List<CoordinateModel> ignoreList) {
        Queue<Integer> rowq = new LinkedList<>();
        Queue<Integer> colq = new LinkedList<>();
        int rows = charMatrix.length;
        int cols = charMatrix[0].length;
        boolean[][] visited = new boolean[rows][cols];

        rowq.add(startX);
        colq.add(startY);

        Map<CoordinateModel, CoordinateModel> parentCoordinateModels = new HashMap<>();
        // Define the order of directions to prioritize
        int[] rowDirections = {0, 1, 0, -1}; // Right, Down, Left, Up
        int[] colDirections = {1, 0, -1, 0};
        int row;
        int col;
        int checkX;
        int checkY;
        List<CoordinateModel> searchPath = new ArrayList<>();
        ArrayList<CoordinateModel> path = new ArrayList<>();
        int moves = 0;
        while (rowq.size() > 0) {
            row = rowq.poll();
            col = colq.poll();
            if (visited[row][col]) continue;
            visited[row][col] = true;
            CoordinateModel current = new CoordinateModel(row, col);
            searchPath.add(current);
            if (charMatrix[row][col].equals(find) && !ignoreList.contains(current)) {
                for (CoordinateModel parent = new CoordinateModel(row, col); parent != null; parent = parentCoordinateModels.get(parent)) {
                    if (parent.x() == startX && parent.y() == startY) continue;
                    path.add(parent);
                }
                path = new ArrayList<>(path.reversed());
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = row + rowDirections[i];
                int nextCol = col + colDirections[i];

                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols && !visited[nextRow][nextCol]) {
                    rowq.add(nextRow);
                    colq.add(nextCol);
                    CoordinateModel nextInLine = new CoordinateModel(nextRow, nextCol);
                    parentCoordinateModels.put(nextInLine, current);
                }
            }
            moves++;
        }
        List<List<CoordinateModel>> pathAndVisited = new ArrayList<>();
        pathAndVisited.add(path);
        pathAndVisited.add(searchPath);
        return pathAndVisited;
    }

    /**
     * Gets the coordinates of all stops in the maze
     * @param charMatrix The character matrix representing the maze
     * @return List of CoordinateModel objects representing the stops
     */
    public static List<CoordinateModel> getStops(String[][] charMatrix) {
        List<CoordinateModel> stops = new ArrayList<>();
        for (int i = 0; i < charMatrix.length; i++) {
            for (int j = 0; j < charMatrix[i].length; j++) {
                if (charMatrix[i][j].equals("x")) stops.add(new CoordinateModel(i, j));
            }
        }
        return stops;
    }

    /**
     * Gets the coordinates of all non-stop positions in the maze
     * @param charMatrix The character matrix representing the maze
     * @return List of CoordinateModel objects representing the non-stop positions
     */
    public static List<CoordinateModel> getStopsCoords(String[][] charMatrix) {
        List<CoordinateModel> positions = new ArrayList<>();
        for (int i = 0; i < charMatrix.length; i++) {
            for (int j = 0; j < charMatrix[i].length; j++) {
                if (!charMatrix[i][j].equalsIgnoreCase("x")) continue;
                positions.add(new CoordinateModel(i, j));
            }
        }
        return positions;
    }

    /**
     * Checks if the path is complete from start to end in the maze
     * @param inputMatrix The character matrix representing the maze
     * @param path List of CoordinateModel objects representing the path
     * @param start The starting character
     * @param end The ending character
     * @return True if path is complete, otherwise False
     */
    public static boolean pathComplete(String[][] inputMatrix, List<CoordinateModel> path, String start, String end) {
        if (path.size() == 0) return false;
        String pathStart = inputMatrix[path.getFirst().x()][path.getFirst().y()];
        String pathEnd = inputMatrix[path.getLast().x()][path.getLast().y()];
        return (pathStart.equals(start) && pathEnd.equals(end));
    }

    /**
     * Finds the position of a given value in the maze
     * @param charMatrix The character matrix representing the maze
     * @param value The value to find
     * @return CoordinateModel object representing the position of the value
     */
    public static CoordinateModel findPosition(String[][] charMatrix, String value) {
        for (int i = 0; i < charMatrix.length; i++) {
            for (int j = 0; j < charMatrix[i].length; j++) {
                if (charMatrix[i][j].equals(value)) return new CoordinateModel(i, j);
            }
        }
        return null;
    }
}
