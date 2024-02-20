package org.example;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

@Service
public class GenerateMatrix {
    public Matrix getMatrix(InputStream inputStream, boolean klaviatur, boolean gen) {
        Scanner sc = new Scanner(inputStream);
        if (klaviatur) {
            System.out.println("Введите точность");
        }
        double toch;
        while (true) {
            try {
                toch = sc.nextDouble();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод. Скорее всего данные предаваемые вами не являются числом");
                sc.nextLine();
            }
        }
        if (klaviatur) {
            System.out.println("Введите количество переменных");
        }
        int var;
        while (true) {
            try {
                var = sc.nextInt();
                if (var > 20) {
                    System.out.println("Количество переменных должно быть <= 20");
                    sc.nextLine();
                } else {
                    sc.nextLine();
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод. Скорее всего данные предаваемые вами не являются числом.");
                sc.nextLine();
            }
        }
        Matrix matrix = new Matrix(var, toch);
        if (gen) {
            Random random = new Random();
            double[][] generatedMatrix = new double[var][var + 1];
            for (int i = 0; i < var; i++) {
                double summ = 0;

                for (int j = 0; j < var; j++) {
                    if (i != j) {
                        double current = random.nextDouble(10 * 2) - 10;
                        summ += Math.abs(current);
                        generatedMatrix[i][j] = current;
                    }
                }
                generatedMatrix[i][var] = random.nextDouble(10 * 2) - 10;
                generatedMatrix[i][i] = Math.round(Math.random()) == 1 ? summ + 0.1 : -summ - 0.1;
            }
            matrix.setMatrix(generatedMatrix);
            return matrix;
        }
        if (klaviatur) {
            System.out.println("Введите матрицу");
        }
        for (int i =  0; i < var; i++) {
            boolean isValidRow = false;
            while (!isValidRow) {
                try {
                    String row = sc.nextLine();
                    String[] elements = row.split("\\s+"); // Split the input by whitespace
                    if (elements.length != (var +  1)) {
                        throw new InputMismatchException("Неверное количество переменных в строке");
                    }
                    for (int j =  0; j < var + 1; j++) {
                        matrix.setElement(i, j, Double.parseDouble(elements[j]));
                    }
                    isValidRow = true;
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage() + ". Строка должна быть с " + (var +  1) + " числами.");
                    isValidRow = false; // If we catch an exception, the row is not valid
                } catch (NumberFormatException e) {
                    System.out.println("Неверный формат числа.");
                    isValidRow = false; // If we catch an exception, the row is not valid
                }
            }
        }
        matrix.print();
        return matrix;
    }
}
