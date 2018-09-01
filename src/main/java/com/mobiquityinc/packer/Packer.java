package com.mobiquityinc.packer;

import com.mobiquityinc.entities.Item;
import com.mobiquityinc.entities.Pack;
import com.mobiquityinc.exception.APIException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Packer {

    public static String pack(String filePath) {
        try(Stream<String> stream = Files.lines(Paths.get(filePath))){
            return stream.map(line -> packLine(line).toString()).collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new APIException();
        }
    }

    private static Pack packLine(String line) {
        String[] packParts = line.split(" : ");
        Item[] items = parseLineToItems(packParts[1].trim());

        Pack pack = new Pack();
        pack.setWeight(Integer.valueOf(packParts[0].trim()) * 100);
        pack.setItems(combineItems(items, pack.getWeight()));

        return pack;
    }

    private static Item[] parseLineToItems(String itemsSplitted) {
        Pattern pattern = Pattern.compile("\\((?<index>\\d+)\\,(?<weight>\\d+(\\.\\d{1,2})?)\\,\\p{Sc}(?<cost>\\d+(\\.\\d{1,2})?)\\)");
        Matcher matcher = pattern.matcher(itemsSplitted);

        if(matcher.groupCount() > 15) throw new APIException();

        List<Item> items = new ArrayList<>();

        while(matcher.find()){
            items.add(new Item(matcher.group("index"), matcher.group("weight"), matcher.group("cost")));
        }

        return items.toArray(new Item[items.size()]);
    }

    private static Set<Item> combineItems(Item[] items, int maxWeight) {
        double[][] matrix = getValuesMatrix(items, maxWeight);
        int column = getMatrixColumn(matrix[items.length], maxWeight);
        Set<Item> combinedItems = getCombinedItems(items, matrix, column);
        return combinedItems;
    }

    private static Set<Item> getCombinedItems(Item[] items, double[][] matrix, int column) {
        Set<Item> combinedItems = new HashSet<>();
        for (int index = items.length; index > 0; index--) {
            if (matrix[index][column] != matrix[index - 1][column]) {
                combinedItems.add(items[index - 1]);
                column -= items[index - 1].getWeight();
            }
        }
        return combinedItems;
    }

    private static int getMatrixColumn(double[] matrix, int maxWeight) {
        int column = maxWeight;
        double totalCost = matrix[maxWeight];
        for (; column > 0 && matrix[column - 1] == totalCost; column--);
        return column;
    }

    private static double[][] getValuesMatrix(Item[] items, int maxWeight) {
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

}
