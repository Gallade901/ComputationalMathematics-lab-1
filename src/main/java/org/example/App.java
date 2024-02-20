package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Component
public class App {
    Matrix matrix;
    private GenerateMatrix generateMatrix;
    private IterMethod iterMethod;

    @Autowired
    public App(GenerateMatrix generateMatrix, IterMethod iterMethod) {
        this.generateMatrix = generateMatrix;
        this.iterMethod = iterMethod;

    }
    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Запуск программы 'Метод простых итераций'");
            System.out.println("Хотите сгенирировать матрицу? (Да) / (если нет 'любой ввод')");
            String genStr = sc.nextLine().toLowerCase();
            boolean gen = genStr.equals("да");
            AutoGen autoGen = new AutoGen(generateMatrix, true, gen);
            if (gen) {
                autoGen.gen();
                return;
            }
            System.out.println("Хотите вводить данные с клавиатуры? (Да) / (если нет 'любой ввод')");
            String userInput = sc.nextLine().toLowerCase();
            boolean klaviatur = userInput.equals("да");
            if (klaviatur) {
                matrix = generateMatrix.getMatrix(System.in, klaviatur, gen);
                matrix = iterMethod.convertDiagonal(matrix);
                if (matrix == null) {
                    System.out.println("Невозможно привести матрицу к диагональному виду");
                    return;
                }
                System.out.println("Матрица приведенная к диагональному виду");
                matrix.print();
                iterMethod.iter(matrix);

            } else {
                String fileName;
                BufferedReader br = null;

                do {
                    System.out.println("Введите имя файла:");
                    fileName = sc.nextLine();
                    try {
                        FileInputStream fl = new FileInputStream(fileName);
                        matrix = generateMatrix.getMatrix(fl, klaviatur, gen);
                        matrix = iterMethod.convertDiagonal(matrix);
                        if (matrix == null) {
                            System.out.println("Невозможно привести матрицу к диагональному виду");
                            return;
                        }
                        System.out.println("Матрица приведенная к диагональному виду");
                        matrix.print();
                        iterMethod.iter(matrix);
                        break;
                    } catch (IOException e) {
                        System.out.println("Ошибка чтения файла: " + e.getMessage());
                    }
                } while (true);

                sc.close();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Завершение программы");
            return;
        }
    }
}
