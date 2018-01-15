package com.wisekiddo.shopping.model.data;

import com.wisekiddo.shopping.model.Discount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DiscountContent {
    public static final List<Discount> ITEMS = new ArrayList<Discount>();
    public static final Map<Integer, Discount> ITEM_MAP = new HashMap<Integer, Discount>();

    private static final int COUNT = 2;

    static {
        // Add some sample items.
        Discount item;

        item = new Discount();
        item.setId(0);
        item.setPercentage(0.00);
        item.setName("Discount A");

        addItem(item);

        item = new Discount();
        item.setId(1);
        item.setPercentage(10.00);
        item.setName("Discount B");

        addItem(item);

        item = new Discount();
        item.setId(2);
        item.setPercentage(35.50);
        item.setName("Discount C");

        addItem(item);

        item = new Discount();
        item.setId(3);
        item.setPercentage(50.00);
        item.setName("Discount D");

        addItem(item);

        item = new Discount();
        item.setId(4);
        item.setPercentage(100.00);
        item.setName("Discount E");

        addItem(item);




    }

    private static void addItem(Discount item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }






}
