package com.mobiquityinc.controller;

import com.mobiquityinc.model.Item;

public class MatrixController {

    public double[][] get(Item[] items, int maxWeight) {
        double[][] matrix = new double[items.length + 1][maxWeight + 1];

        for (int row = 1; row < items.length + 1; row++) {
            Item item = items[row - 1];

            for (int column = 1; column < maxWeight + 1; column++) {
                if (item.getWeight() > column) {
                    matrix[row][column] = matrix[row - 1][column];
                } else {
                    matrix[row][column] = Math.max(matrix[row - 1][column], matrix[row - 1][column - item.getWeight()] + item.getCost());
                }
            }
        }
        return matrix;
    }

    public int getInitialColumn(double[] matrix, int maxWeight) {
        int column = maxWeight;
        double totalCost = matrix[maxWeight];
        for (; column > 0 && matrix[column - 1] == totalCost; column--);
        return column;
    }
}
