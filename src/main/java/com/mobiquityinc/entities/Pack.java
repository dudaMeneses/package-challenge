package com.mobiquityinc.entities;

import com.google.common.base.Joiner;
import com.google.common.collect.Ordering;

import java.util.Set;

public class Pack {
    private int weight;
    private Set<Item> items;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        if(items.isEmpty())
            return "-";
        else {
            return Joiner.on(",").join(Ordering.natural().sortedCopy(items));
        }
    }
}
