package src.main.java.com.zs.assignment2;

import java.util.Scanner;

public class Matrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MatrixOperations matrixOperations = new MatrixOperations();

        while (true) {
            System.out.println("Choose a option to perform\n 1. A+B \n 2. A-B \n 3. A*B \n 4. A'\n 5. k*A \n 0. To exit\n");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("A + B= ");
                    matrixOperations.add();
                    break;
                case 2:
                    System.out.println("A - B= ");
                    matrixOperations.subtract();
                    break;
                case 3:
                    System.out.println("A * B= ");
                    matrixOperations.multiply();
                    break;
                case 4:
                    System.out.println("Transpose of A is ");
                    matrixOperations.transpose();
                    break;
                case 5:
                    System.out.println("k * A = ");
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
