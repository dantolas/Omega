package com.kuta.util;

/**
 * MatrixPrint
 */
public class MatrixPrint {

    
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
