package com.wisekiddo.shopping;

//import com.wisekiddo.rxandroidcurrency.model.Currency;

import com.wisekiddo.shopping.model.Item;

import java.util.List;

/**
 * Created by ronaldbernardo on 23/12/17.
 */

public class Application {
    private static final Application ourInstance = new Application();

    public static Application getInstance() {
        return ourInstance;
    }

    public List<Item> itemList;
    // public Currency baseCode;
    // public Currency targetCode;

    public String baseCode ="USD";
    public String targetCode ="EUR";

    public int selectedPosition;
    public Double defaultValue=1.00;

    private Application() {

    }
}