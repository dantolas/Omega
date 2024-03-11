package com.kuta.pathfinding_algos;

import java.util.Objects;

/**
 * XYNode
 */
public class XYNode {

        public int x;
        public int y;

        public XYNode(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == this) return true;
            if(obj == null || !(obj instanceof XYNode)) return false;
            XYNode that = (XYNode)obj;
            if(that.x == x && that.y == y) return true;
            return false;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x,y);
        }

        @Override
        public String toString() {
            return "Node x=" + x + ", y=" + y;
        }



}
