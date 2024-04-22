package com.kuta.app.models;

import java.util.List;

import com.kuta.pathfinding_algos.CoordinateModel;

/**
 * Class to model the response the REST API will provide after a correct request
 * @param pathFound (boolean) | Indicates whether a full path was found
 * @param stopsFound (int) | Amount of stops found
 * @param of (int) | Total number of spots in maze
 * @param stopsNotFound (List<CoordinateModel>) | Coordinates of stops not found
 * @param path (List<CoordinateModel>) | Path that was traversed
 * @param visited (List<CoordinateModel>) | All coordinates visited
 * @param time (long) | Time taken to solve in miliseconds
 */
public record 
ApiResponseModel(
    boolean pathFound,
    int stopsFound,
    int of,
    List<CoordinateModel> stopsNotFound,
    List<CoordinateModel> path,
    List<CoordinateModel> searchPath,
    long time
) {

}
