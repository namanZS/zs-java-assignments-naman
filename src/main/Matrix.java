package src.main;

import java.util.Scanner;

public class Matrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MatrixOperations obj = new MatrixOperations();

        while (true) {
            System.out.println("Choose a option to perform\n 1. A+B \n 2. A-B \n 3. A*B \n 4. A'\n 5. k*A \n 0. To exit\n");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("A + B= ");
                    obj.printResult(obj.add(scanner));
                    break;
                case 2:
                    System.out.println("A - B= ");
                    obj.printResult(obj.subtract(scanner));
                    break;
                case 3:
                    System.out.println("A * B= ");
                    obj.printResult(obj.multiply(scanner));
                    break;
                case 4:
                    System.out.println("Transpose of A is ");
                    obj.printResult(obj.transpose(scanner));
                    break;
                case 5:
                    System.out.println("k * A = ");
                    obj.printResult(obj.scalarMultiply(scanner));
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println(" Wrong value entered, please try again ! ");
            }
        }
    }
}
