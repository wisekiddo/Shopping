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
import com.wisekiddo.shopping.model.CartItem;
import com.wisekiddo.shopping.model.Discount;
import com.wisekiddo.shopping.model.Item;
import com.wisekiddo.shopping.model.Library;

import java.util.List;

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

    private ItemListFragment itemListFragment = new ItemListFragment();
    private DiscountListFragment discountListFragment = new DiscountListFragment();
    private AddCartFragment addCartFragment;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        addCartFragment = (AddCartFragment) getFragmentManager().findFragmentById(R.id.fragmentTop);

        showAddCard(false);

        loadCurrencies();
    }


    @Override
    public void onLibraryFragmentInteraction(Library item){

        Log.i("MAIN", item.getName());

        fragmentTransaction= fm.beginTransaction();

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

    @Override
    public void onDiscountFragmentInteraction(Discount item){

    }

    @Override
    public void onItemListFragmentInteraction(Item item){

        Log.i("ITEMLIST",item.getId()+"");

        showAddCard(true);

        addCartFragment.addCart(item.getId());

    }



    private void loadCurrencies(){

        ApiService api = RetroClient.getApiService();

        Call<List<Item>> call = api.getItems();

        call.enqueue(new Callback<List<Item>>(){
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response){

                if(response.isSuccessful()){
                    itemList = response.body();
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
    public void onAddCardFragmentInteraction(CartItem item){
        Log.i("CHECK",item.getTitle());

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
