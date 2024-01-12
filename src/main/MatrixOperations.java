package src.main;
import java.util.Scanner;
public class MatrixOperations {
    public static void main(String []args){

        Scanner scanner= new Scanner(System.in);

        while(true) {
            System.out.println("Choose a option to perform\n 1. A+B \n 2. A-B \n 3. A*B \n 4. A'\n 5. k*A \n 0. To exit\n");
        switch(scanner.nextInt()){
            case 1:
                System.out.println("A + B= ");
                printResult(add(scanner));
                break;
            case 2:
                System.out.println("A - B= ");
                printResult(subtract(scanner));break;
            case 3:
                System.out.println("A * B= ");
                printResult(multiply(scanner));break;
            case 4:
                System.out.println("Transpose of A is ");
                printResult(transpose(scanner));break;
            case 5:
                System.out.println("k * A = ");
                printResult(scalarMultiply(scanner));break;
            case 0:
                System.exit(0);
            default:
                System.out.println(" Wrong value entered, please try again ! ");
        }
        }
    }
    private static int input(Scanner in){
        int n;
        while(true){
            n=in.nextInt();

            if(n<=0)System.out.println("Not a valid value Enter Again");
            else return n;
        }
    }

    private static int [][] readMatrix(Scanner scanner){

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
    private static int[][] add(Scanner scanner){

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
    private static int[][] subtract(Scanner scanner){

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
    private static int[][] multiply(Scanner scanner){

        System.out.println("Enter A matrix ");
        int [][]A= readMatrix(scanner);

        System.out.println("Enter B matrix ");
        int [][]B= readMatrix(scanner);

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
    
    private static int[][] scalarMultiply(Scanner scanner){

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
     * @param A
     * @return transposed matrix
     */
    private static int[][] transpose(Scanner scanner){
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
     *
     * @param result
     */
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





