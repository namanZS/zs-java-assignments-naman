package com.zs.assignment2;
import java.util.Scanner;
public class MatrixOperations {
    private final Scanner input;
    public MatrixOperations() {
        this.input = new Scanner(System.in);
    }
    public int input(){
        int num;
        while(true){
            num=input.nextInt();
            if(num<=0)System.out.println("Not a valid value Enter Again");
            else return num;
        }
    }

    /**
     * Function to read a matrix
     * @return
     */
    public int[][] readMatrix() {
        System.out.print("Enter row size ");
        int row = input();

        System.out.print("Enter column size ");
        int col = input();

        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("Enter value [" + i + "][" + j + "] ");
                matrix[i][j] = input.nextInt();
            }
        }
        return matrix;
    }

    /**
     * Function to add two matrices A & B
     */
    public void add() {

        System.out.println("Enter A matrix ");
        int[][] matrixA = readMatrix();

        System.out.println("Enter B matrix ");
        int[][] matrixB = readMatrix();
        if (matrixA.length != matrixB.length || matrixA[0].length != matrixB[0].length) {
            System.out.println("Invalid matrix sizes");
            return;
        }
        int[][] result = new int[matrixA.length][matrixA[0].length];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                result[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        printResult(result,"Addition");

    }

    /**
     * Function to subtract matrix matrixB from A
     */
    public void subtract(){

        System.out.println("Enter A matrix ");
        int [][]matrixA= readMatrix();

        System.out.println("Enter B matrix ");
        int [][]matrixB= readMatrix();
        if (matrixA.length != matrixB.length || matrixA[0].length != matrixB[0].length) {
            System.out.println("Invalid matrix sizes");
            return;
        }

        int[][] result = new int[matrixA.length][matrixA[0].length];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                result[i][j] = matrixB[i][j] - matrixA[i][j];
            }
        }
        printResult(result,"Substraction");

    }

    /**
     * Function to multiply matrix A with matrix B
     */
    public void multiply(){

        System.out.println("Enter A matrix ");
        int [][]matrixA= readMatrix();

        System.out.println("Enter B matrix ");
        int [][]matrixB= readMatrix();

        if (matrixA[0].length != matrixB.length) {
            System.out.println("Incompatible matrix sizes");
            return;
        }

        int[][] result = new int[matrixA.length][matrixB[0].length];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for (int k = 0; k < matrixB.length; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        printResult(result,"Multiplication");

    }

    /**
     * Function to multiply the matrix A with an integer k
     */
    public void scalarMultiply(){

        System.out.println("Enter matrix ");
        int [][]matrixA= readMatrix();

        System.out.print("Enter value for k ");
        int  k=input.nextInt();

        int[][]result=new int[matrixA.length][matrixA[0].length];
        for(int i=0;i<matrixA.length;i++){
            for(int j=0;j<matrixA[0].length;j++){
                result[i][j]=k*matrixA[i][j];
            }
        }
        printResult(result, "Scaler Multiplication");


    }

    /**
     * Function to Transpose the matrix A
     */
    public void transpose(){
        System.out.println("Enter matrix ");
        int [][]matrixA= readMatrix();
        int[][]result=new int[matrixA[0].length][matrixA.length];
        for(int i=0;i<matrixA[0].length;i++){
            for(int j=0;j<matrixA.length;j++){
                    result[i][j]=matrixA[j][i];
            }
        }
        printResult(result,"Transpose");


    }

    /**
     * Function to print any matrix
     * @param result
     */

    public void printResult(int [][]result,String operation){
        System.out.println(operation+" Result: ");
        for (int[] rows : result) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(rows[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
       
        
    }
    
}





