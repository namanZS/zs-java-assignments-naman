package src.main.java.com.zs.assignment2;
import java.util.Scanner;
public class MatrixOperations {
    public int input(Scanner in){
        int n;
        while(true){
            n=in.nextInt();

            if(n<=0)System.out.println("Not a valid value Enter Again");
            else return n;
        }
    }

    public int [][] readMatrix(Scanner scanner){

        System.out.print("Enter rows size ");
        int row=input(scanner);

        System.out.print("Enter column size ");
        int col=input(scanner);

        int [][]matrix=new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                System.out.print("Enter value ["+i+"]["+ j+ "] " );
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;

    }
    //function to add matrix A and matrix B
    public int[][] add(Scanner scanner){

        System.out.println("Enter A matrix ");
        int [][]A= readMatrix(scanner);

        System.out.println("Enter B matrix ");
        int [][]B= readMatrix(scanner);

        int[][] result = new int[A.length][A[0].length];
        if(A.length==B.length && A[0].length==B[0].length) {

            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    result[i][j] = A[i][j] + B[i][j];
                }
            }

        }
        else {
            System.out.println("Invalid matrix sizes");
        }
        return result;

    }
    //Function to subtract B from A
    public int[][] subtract(Scanner scanner){

        System.out.println("Enter A matrix ");
        int [][]A= readMatrix(scanner);

        System.out.println("Enter B matrix ");
        int [][]B= readMatrix(scanner);

        int[][] result = new int[A.length][A[0].length];
        if(A.length==B.length && A[0].length==B[0].length) {

            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    result[i][j] = B[i][j] - A[i][j];
                }
            }

        }
        else {
            System.out.println("Invalid matrix sizes");

        }
        return result;

    }
//    Function to multiply A with B
    public int[][] multiply(Scanner scanner){

        System.out.println("Enter A matrix ");
        int [][]A= readMatrix(scanner);

        System.out.println("Enter B matrix ");
        int [][]B= readMatrix(scanner);

        int[][]result=new int[A.length][B[0].length];

        if (A[0].length != B.length) { return result;}
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;

    }
//    Function to multiply a matrix with a scalar value
    public int[][] scalarMultiply(Scanner scanner){

        System.out.println("Enter matrix ");
        int [][]A= readMatrix(scanner);

        System.out.print("Enter value for k ");
        int  k=scanner.nextInt();

        int[][]result=new int[A.length][A[0].length];
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[0].length;j++){
                result[i][j]=k*A[i][j];
            }
        }
        return result;


    }
//    Function to transpose a matrix

    /**
     * This function takes a matrix as input transpose it.
     * @param scanner object
     * @return transposed matrix
     */
    public int[][] transpose(Scanner scanner){
        System.out.println("Enter matrix ");
        int [][]A= readMatrix(scanner);
        int[][]result=new int[A[0].length][A.length];
        for(int i=0;i<A[0].length;i++){
            for(int j=0;j<A.length;j++){
                    result[i][j]=A[j][i];
            }
        }
        return result;


    }
//    function to print the matrix

    /**
     * Printing the matrix
     * @param result
     */
    public void printResult(int [][]result){
        for (int[] ints : result) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
       
        
    }
    
}





