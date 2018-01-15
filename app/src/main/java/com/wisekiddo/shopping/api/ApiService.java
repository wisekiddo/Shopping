package com.wisekiddo.shopping.api;


import com.wisekiddo.shopping.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ronaldbernardo on 21/12/17.
 */

public interface ApiService {

    @GET("/photos")
    Call<List<Item>> getItems();


}
