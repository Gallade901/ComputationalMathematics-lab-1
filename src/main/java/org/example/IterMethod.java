package org.example;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class IterMethod {
    public Matrix convertDiagonal(Matrix matrix) {
        double[][] mt = matrix.getMatrix();
        int size = matrix.getSize();
        double[][] newMatrix = new double[size][size + 1];
        boolean[] indMat = new boolean[size];
        for (int i = 0; i < size; i++) {
            double maxEl = Double.MIN_VALUE;
            double sumSt = 0;
            int ind = 0;
            for (int j = 0; j < size; j++) {
                sumSt += Math.abs(mt[i][j]);
                if (Math.abs(mt[i][j]) > maxEl) {
                    maxEl = Math.abs(mt[i][j]);
                    ind = j;
                }
            }
            sumSt -= maxEl;
            if (maxEl <= sumSt) {
                return null;
            }
            if (indMat[ind]) {
                return null;
            }
            newMatrix[ind] = mt[i];
            indMat[ind] = true;
        }
        return new Matrix(size, newMatrix, matrix.getToch());
    }
    public void iter(Matrix matrix) {
        int iter = 0;
        double[][] mt = matrix.getMatrix();
        int size = matrix.getSize();
        ArrayList<double[]> otvList = new ArrayList<>();
        otvList.add(new double[size]);
        double[] otv = new double[size];
        for (int i = 0; i < size; i++) {
            double gl = mt[i][i];
            for (int j = 0; j <= size; j++) {
                mt[i][j] /= -gl;
                if (j == size) {
                    mt[i][j] *= -1;
                }
            }
            otv[i] = mt[i][size];
        }
        otvList.add(otv);
        double maxPogr = Double.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            if (Math.abs(otvList.get(1)[i] - otvList.get(0)[i]) > maxPogr) {
                maxPogr = Math.abs(otvList.get(1)[i] - otvList.get(0)[i]);
            }
        }
        System.out.println(iter + " итерация: " + maxPogr);
        if (maxPogr < matrix.getToch()) {
            System.out.println("Ответ:");
            for (int i = 0; i < size; i++) {
                System.out.println("X_" + (i + 1) + " = " + otvList.get(1)[i]);
            }
            return;
        }
        while (true) {
            double[] otv1 = new double[size];
            for (int i = 0; i < size; i++) {
                double ans = 0;
                for (int j = 0; j < size; j++) {
                    if (i != j) {
                        ans += otvList.get(1)[j] * mt[i][j];
                    }
                }
                ans += mt[i][size];
                otv1[i] = ans;
            }
            otvList.remove(0);
            otvList.add(otv1);
            maxPogr = Double.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                if (Math.abs(otvList.get(1)[i] - otvList.get(0)[i]) > maxPogr) {
                    maxPogr = Math.abs(otvList.get(1)[i] - otvList.get(0)[i]);
                }
            }
            iter++;
            System.out.println(iter + " итерация: " + maxPogr);
            if (maxPogr < matrix.getToch()) {
                System.out.println("Ответ:");
                for (int i = 0; i < size; i++) {
                    System.out.println("X_" + (i + 1) + " = " + otvList.get(1)[i]);
                }
                break;
            }
        }
    }
}
