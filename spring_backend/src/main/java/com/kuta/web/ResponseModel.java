package com.kuta.web;

import java.util.List;

import com.kuta.pathfinding_algos.XYNode;

/**
 * ResponseModel
 */
public record ResponseModel(
    boolean pathFound
    ,int pathLength
    , int moves
    ,List<XYNode> path
    ,String[][] matrix
) {
}
