package src.main;
import java.util.Scanner;
public class MatrixOperations {
    public static void main(String []args){
        Scanner scanner= new Scanner(System.in);
        int aRow=scanner.nextInt();
        int aCol=scanner.nextInt();
        int [][]A= readMatrix(aRow,aCol,scanner) ;
        int bRow=scanner.nextInt();
        int bCol=scanner.nextInt();
        int [][]B= readMatrix(bRow,bCol,scanner) ;
        int  k=scanner.nextInt();
        System.out.println("A + B= ");
        printResult(add(A,B));
        System.out.println("A - B= ");
        printResult(subtract(A,B));
        System.out.println("Tranpose of A is ");
        printResult(transpose(A));
        System.out.println("A * B= ");
        printResult(multiply(A, B));
        System.out.println("k * A = ");
        printResult(scalarMultiply(k, A));
    }
    private static int [][] readMatrix(int row,int col,Scanner scanner){
        int [][]matrix=new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;

    }
    //function to add matrix A and matrix B
    private static int[][] add(int [][]A,int [][]B){
        int[][]result=new int[A.length][A[0].length];
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[0].length;j++){
                result[i][j]=A[i][j]+B[i][j];
            }
        }
        return result;

    }
    //Function to subtract B from A
    private static int[][] subtract(int [][]A,int [][]B){
        int[][]result=new int[A.length][A[0].length];
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[0].length;j++){
                result[i][j]=B[i][j] - A[i][j];
            }
        }
        return result;

    }
//    Function to multiply A with B
    private static int[][] multiply(int [][]A,int [][]B){
        int rowA=A.length;
        int colA=A[0].length;
        int colB=B[0].length;
        int[][]result=new int[A.length][A[0].length];
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colB; j++) {
                for (int k = 0; k < colA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;

    }
//    Function to multiply a matrix with a scalar value
    private static int[][] scalarMultiply(int k,int [][]A){
        int[][]result=new int[A.length][A[0].length];
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[0].length;j++){
                result[i][j]=k*A[i][j];
            }
        }
        return result;


    }
//    Function to transpose a matrix
    private static int[][] transpose(int [][]A){
        int[][]result=new int[A.length][A[0].length];
        for(int i=0;i<A[0].length;i++){
            for(int j=0;j<A.length;j++){
                    result[i][j]=A[j][i];
            }
        }
        return result;


    }
//    function to print the matrix
    private static void printResult(int [][]result){
        for (int[] ints : result) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        
    }
    
}





