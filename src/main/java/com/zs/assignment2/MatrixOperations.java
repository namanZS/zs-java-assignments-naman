package src.main.java.com.zs.assignment2;
import java.util.Scanner;
public class MatrixOperations {
    public int input(){
        Scanner in = new Scanner(System.in);
        int num;
        while(true){
            num=in.nextInt();
            if(num<=0)System.out.println("Not a valid value Enter Again");
            else return num;
        }
    }

    public int [][] readMatrix(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter rows size ");
        int row=input();

        System.out.print("Enter column size ");
        int col=input();

        int [][]matrix=new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                System.out.print("Enter value ["+i+"]["+ j+ "] " );
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;

    }

    /**
     * Function to add two matrices A & B
     */
    public void add() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter A matrix ");
        int[][] A = readMatrix();

        System.out.println("Enter B matrix ");
        int[][] B = readMatrix();
        if (A.length != B.length || A[0].length != B[0].length) {
            System.out.println("Invalid matrix sizes");
            return;
        }
        int[][] result = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        printResult(result);

    }

    /**
     * Function to subtract matrix B from A
     */
    public void subtract(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter A matrix ");
        int [][]A= readMatrix();

        System.out.println("Enter B matrix ");
        int [][]B= readMatrix();
        if (A.length != B.length || A[0].length != B[0].length) {
            System.out.println("Invalid matrix sizes");
            return;
        }

        int[][] result = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                result[i][j] = B[i][j] - A[i][j];
            }
        }
        printResult(result);

    }

    /**
     * Function to multiply matrix A with matrix B
     */
    public void multiply(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter A matrix ");
        int [][]A= readMatrix();

        System.out.println("Enter B matrix ");
        int [][]B= readMatrix();

        if (A[0].length != B.length) {
            System.out.println("Incompatible matrix sizes");
            return;
        }

        int[][] result = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        printResult(result);

    }

    /**
     * Function to multiply the matrix A with an integer k
     */
    public void scalarMultiply(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter matrix ");
        int [][]A= readMatrix();

        System.out.print("Enter value for k ");
        int  k=scanner.nextInt();

        int[][]result=new int[A.length][A[0].length];
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[0].length;j++){
                result[i][j]=k*A[i][j];
            }
        }
        printResult(result);


    }

    /**
     * Function to Transpose the matrix A
     */
    public void transpose(){
        System.out.println("Enter matrix ");
        int [][]A= readMatrix();
        int[][]result=new int[A[0].length][A.length];
        for(int i=0;i<A[0].length;i++){
            for(int j=0;j<A.length;j++){
                    result[i][j]=A[j][i];
            }
        }
        printResult(result);


    }

    /**
     * Function to print a matrix
     * @param result
     */

    public void printResult(int [][]result){
        for (int[] rows : result) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(rows[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
       
        
    }
    
}





