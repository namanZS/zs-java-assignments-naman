package src.main.java.com.zs.assignment2;

import java.util.Scanner;

public class Matrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MatrixOperations matrixOperations = new MatrixOperations();

        while (true) {
            System.out.println("Choose a option to perform\n 1. Matrix Addition \n 2. Matrix Substraction \n 3. Matrix Multiplication \n 4. Transpose of a matrix\n 5. Scalar Multiplication \n 0. To exit\n");
            switch (scanner.nextInt()) {
                case 1:
                    matrixOperations.add();
                    break;
                case 2:
                    matrixOperations.subtract();
                    break;
                case 3:
                    matrixOperations.multiply();
                    break;
                case 4:
                    matrixOperations.transpose();
                    break;
                case 5:
                    matrixOperations.scalarMultiply();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println(" Wrong value entered, please try again ! ");
            }
        }
    }
}
