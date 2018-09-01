package com.mobiquityinc.entities;

public class Item implements Comparable<Item>{
    private int index;
    private int weight;
    private int cost;

    public Item(String index, String weight, String cost) {
        this.index = Integer.valueOf(index);
        this.weight = (Double.valueOf(weight).intValue() * 100);
        this.cost = Integer.valueOf(cost);
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

}
