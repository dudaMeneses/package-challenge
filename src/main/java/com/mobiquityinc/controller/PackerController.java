package com.mobiquityinc.controller;

import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Pack;
import com.mobiquityinc.utils.WeightUtils;

import java.util.HashSet;
import java.util.Set;

public class PackerController {

    public Pack pack(String line) {
        String[] packParts = line.split(" : ");
        Item[] items = Item.parse(packParts[1].trim());

        Pack pack = new Pack();
        pack.setWeight(WeightUtils.removeDecimals(Integer.valueOf(packParts[0].trim())));
        pack.setItems(combine(items, pack.getWeight()));

        return pack;
    }

    private Set<Item> combine(Item[] items, int maxWeight) {
        MatrixController matrixController = new MatrixController();

        double[][] matrix = matrixController.get(items, maxWeight);
        int initialColumn = matrixController.getInitialColumn(matrix[items.length], maxWeight);
        Set<Item> combinedItems = getCombinedItems(items, matrix, initialColumn);

        return combinedItems;
    }

    private Set<Item> getCombinedItems(Item[] items, double[][] matrix, int column) {
        Set<Item> combinedItems = new HashSet<>();
        for (int index = items.length; index > 0; index--) {
            if (matrix[index][column] != matrix[index - 1][column]) {
                combinedItems.add(items[index - 1]);
                column -= items[index - 1].getWeight();
            }
        }
        return combinedItems;
    }

}
