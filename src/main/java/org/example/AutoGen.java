package org.example;

import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.Scanner;


public class AutoGen {
    boolean gen;
    boolean klaviatur;
    private GenerateMatrix generateMatrix;
    public AutoGen(GenerateMatrix generateMatrix,boolean klaviatur, boolean gen) {
        this.generateMatrix = generateMatrix;
        this.gen = gen;
        this.klaviatur = klaviatur;
    }
    public void gen() {
        IterMethod iterMethod = new IterMethod();
        Matrix matrix;
        matrix = generateMatrix.getMatrix(System.in, klaviatur, gen);
        matrix.print();
        matrix = iterMethod.convertDiagonal(matrix);
        if (matrix == null) {
            System.out.println("Невозможно привести матрицу к диагональному виду");
            return;
        }
        System.out.println("Матрица приведенная к диагональному виду");
        matrix.print();
        iterMethod.iter(matrix);
    }
}
