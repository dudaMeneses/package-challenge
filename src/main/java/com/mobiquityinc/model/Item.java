package com.mobiquityinc.model;

import com.mobiquityinc.exception.APIException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Item implements Comparable<Item>{
    private int index;
    private int weight;
    private int cost;

    public Item(String index, String weight, String cost) {
        this.index = Integer.valueOf(index);
        this.weight = (Double.valueOf(weight).intValue() * 100);
        this.cost = Integer.valueOf(cost);
    }

    public static Item[] parse(String itemsSplitted) {
        Pattern pattern = Pattern.compile("\\((?<index>\\d+)\\,(?<weight>\\d+(\\.\\d{1,2})?)\\,\\p{Sc}(?<cost>\\d+(\\.\\d{1,2})?)\\)");
        Matcher matcher = pattern.matcher(itemsSplitted);

        if(matcher.groupCount() > 15) throw new APIException();

        List<Item> items = new ArrayList<>();

        while(matcher.find()){
            items.add(new Item(matcher.group("index"), matcher.group("weight"), matcher.group("cost")));
        }

        return items.toArray(new Item[items.size()]);
    }

    public int getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.valueOf(index);
    }

    @Override
    public int compareTo(Item item) {
        if(this.index > item.index)
            return 1;

        if(this.index < item.index)
            return -1;

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return index == item.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
