package org.example;

public class Matrix {
    private double[][] matrix;
    private double toch;
    private int size;
    public Matrix(int size, double toch) {
        this.size = size;
        this.matrix = new double[size][size + 1];
        this.toch = toch;
    }
    public Matrix(int size, double[][] matrix, double toch) {
        this.size = size;
        this.matrix = matrix;
        this.toch = toch;
    }

    // Method to set an element at a specific row and column
    public void setElement(int row, int column, double value) {
        if (row >=  0 && row < size && column >=  0 && column < size + 1) {
            matrix[row][column] = value;
        } else {
            throw new IllegalArgumentException("Invalid row or column index");
        }
    }

    public void print() {
        for (double[] row : matrix) {
            for (double elm : row) {
                System.out.printf("%.5f ", elm);
                System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println("Точность: " + toch);
    }
    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double getToch() {
        return toch;
    }

    public void setToch(double toch) {
        this.toch = toch;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
