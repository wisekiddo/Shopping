package com.wisekiddo.shopping.controller.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.wisekiddo.shopping.Application;
import com.wisekiddo.shopping.R;
import com.wisekiddo.shopping.api.ApiService;
import com.wisekiddo.shopping.api.RetroClient;
import com.wisekiddo.shopping.controller.fragment.AddCartFragment;
import com.wisekiddo.shopping.controller.fragment.DiscountListFragment;
import com.wisekiddo.shopping.controller.fragment.ItemListFragment;
import com.wisekiddo.shopping.controller.fragment.LibraryFragment;
import com.wisekiddo.shopping.controller.fragment.ShoppingCartFragment;
import com.wisekiddo.shopping.model.CartItem;
import com.wisekiddo.shopping.model.Discount;
import com.wisekiddo.shopping.model.Item;
import com.wisekiddo.shopping.model.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements
        LibraryFragment.OnListFragmentInteractionListener,
        DiscountListFragment.OnListFragmentInteractionListener,
        ItemListFragment.OnListFragmentInteractionListener,
        AddCartFragment.OnFragmentInteractionListener{

    private FragmentManager fm = getFragmentManager();
    private List<Item> itemList;
    private List<CartItem> cartItemList;

    private ItemListFragment itemListFragment = new ItemListFragment();
    private DiscountListFragment discountListFragment = new DiscountListFragment();
    private ShoppingCartFragment shoppingCartFragment = new ShoppingCartFragment();
    private AddCartFragment addCartFragment;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loadCurrencies();

        addCartFragment = (AddCartFragment) getFragmentManager().findFragmentById(R.id.fragmentTop);
        shoppingCartFragment = (ShoppingCartFragment) getFragmentManager().findFragmentById(R.id.fragmentRight);

        cartItemList = new ArrayList<>();

        showAddCard(false);
    }

    @Override
    public void onLibraryFragmentInteraction(Library item){

        fragmentTransaction = fm.beginTransaction();

        if(item.getId()==0) {
            fragmentTransaction.replace(R.id.fragmentLeft, discountListFragment ,null);
        }
        else if(item.getId()==1) {
            fragmentTransaction.replace(R.id.fragmentLeft, itemListFragment ,null);
        }

        // fragmentTransaction.add(R.id.fragmentLeft,itemListFragment,"ll");

        fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
    }

    //ITEMS INTERACTION
    @Override
    public void onItemListFragmentInteraction(Item item){
        showAddCard(true);
        addCartFragment.selectedItem(item.getId());
    }

    @Override
    public void onDiscountFragmentInteraction(Discount item){

    }

    @Override
    public void onAddCardFragmentInteraction(CartItem item){
        shoppingCartFragment.addItem(item);
    }

    private void loadCurrencies(){

        final Random random = new Random();
        final Integer min = 10, max=100;

        ApiService api = RetroClient.getApiService();

        Call<List<Item>> call = api.getItems();

        call.enqueue(new Callback<List<Item>>(){
            int randomNum;
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response){

                if(response.isSuccessful()){
                    itemList = response.body();
                    for(int i=0;i<itemList.size();i++){
                        randomNum = random.nextInt((max - min) + 1) + min;
                        itemList.get(i).setPrice( (double) randomNum);
                        Application.getInstance().mapItem.put(itemList.get(i).getId(), itemList.get(i));
                    }

                    Application.getInstance().itemList = response.body();

                }else{
                    Log.i("ListView","NOT LOADING");
                }
            }
            @Override
            public void onFailure(Call<List<Item>> call, Throwable t){
                Log.i("LIST VIEW","DISMISS" + t);
            }
        });
    }

    @Override
    public void onHideAddCardFragment(){
        showAddCard(false);
    }

    private void showAddCard(Boolean bol){

        fragmentTransaction= fm.beginTransaction();

        if(bol){
            fragmentTransaction.show(addCartFragment);
        }else{
            fragmentTransaction.hide(addCartFragment);
        }

        fragmentTransaction.commit();
    }

}
