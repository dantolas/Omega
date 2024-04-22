package com.kuta.pathfinding_algos;

import java.util.Objects;

/**
 * XYNode
 */
public record CoordinateModel(int x, int y) {

        public CoordinateModel(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == this) return true;
            if(obj == null || !(obj instanceof CoordinateModel)) return false;
            CoordinateModel that = (CoordinateModel)obj;
            if(that.x == x && that.y == y) return true;
            return false;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x,y);
        }

        @Override
        public String toString() {
            return "{x:" + x + ";y:" + y+"}";
        }



}
