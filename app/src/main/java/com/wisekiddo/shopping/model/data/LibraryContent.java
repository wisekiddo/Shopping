package com.wisekiddo.shopping.model.data;

import com.wisekiddo.shopping.model.Library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LibraryContent {

    public static final List<Library> ITEMS = new ArrayList<Library>();
    public static final Map<Integer, Library> ITEM_MAP = new HashMap<Integer, Library>();

    private static final int COUNT = 2;

    static {
        // Add some sample items.
        Library item;

        item = new Library();
        item.setId(0);
        item.setName("All Discounts");

        addItem(item);

        item = new Library();
        item.setId(1);
        item.setName("All Items");

        addItem(item);


    }

    private static void addItem(Library item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }






}
