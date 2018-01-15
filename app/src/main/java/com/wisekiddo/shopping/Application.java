package com.wisekiddo.shopping;

//import com.wisekiddo.rxandroidcurrency.model.Currency;

import com.wisekiddo.shopping.model.CartItem;
import com.wisekiddo.shopping.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ronaldbernardo on 23/12/17.
 */

public class Application {
    private static final Application ourInstance = new Application();

    public static Application getInstance() {
        return ourInstance;
    }

    public List<Item> itemList;
    public List<CartItem> cartItemList;

    public Map<Integer, Item> mapItem = new HashMap<Integer, Item>();
    public Map<String, CartItem> cartItem = new HashMap<String, CartItem>();

    private Application() {

    }
}