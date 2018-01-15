package com.wisekiddo.shopping.controller.fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.wisekiddo.shopping.Application;
import com.wisekiddo.shopping.R;
import com.wisekiddo.shopping.model.CartItem;
import com.wisekiddo.shopping.model.Item;

public class AddCartFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private Integer quantity=1;
    private Integer id;
    private Double discount=0.00;

    private ToggleButton toggle1,toggle2,toggle3,toggle4,toggle5;

    public AddCartFragment() {

    }

    public static AddCartFragment newInstance(String param1, String param2) {
        AddCartFragment fragment = new AddCartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private TextView textViewTitle;
    private TextView textViewQuantity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_cart, container, false);
        Button btnSave = (Button) view.findViewById(R.id.save);
        Button btnCancel = (Button) view.findViewById(R.id.cancel);

        Button btnPlus = (Button) view.findViewById(R.id.plus);
        Button btnMinus = (Button) view.findViewById(R.id.minus);

        toggle1 = (ToggleButton) view.findViewById(R.id.toggleButton1);
        toggle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discount=toggleDiscount(1);
            }
        });
        toggle2 = (ToggleButton) view.findViewById(R.id.toggleButton2);
        toggle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discount=toggleDiscount(2);
            }
        });
        toggle3 = (ToggleButton) view.findViewById(R.id.toggleButton3);
        toggle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discount=toggleDiscount(3);
            }
        });
        toggle4 = (ToggleButton) view.findViewById(R.id.toggleButton4);
        toggle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discount=toggleDiscount(4);
            }
        });
        toggle5 = (ToggleButton) view.findViewById(R.id.toggleButton5);
        toggle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               discount=toggleDiscount(5);
            }
        });

        textViewTitle = (TextView) view.findViewById(R.id.title);
        textViewQuantity = (TextView) view.findViewById(R.id.quantity);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // updateDetail();
                createShoppingCartItem(id);
                mListener.onHideAddCardFragment();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // updateDetail();
                mListener.onHideAddCardFragment();
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(quantity<=10) {
                quantity++;
                textViewQuantity.setText(quantity+"");
            }
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(quantity>1) {
                quantity--;
                textViewQuantity.setText(quantity+"");
            }
            }
        });

        return view;
    }

    public void onButtonPressed(CartItem item) {
        if (mListener != null) {
            mListener.onAddCardFragmentInteraction(item);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void selectedItem(Integer id){
        this.id = id;
        String title=Application.getInstance().mapItem.get(id).getTitle();
        Double price =Application.getInstance().mapItem.get(id).getPrice();

        textViewTitle.setText(title + " - $"+ price);
    }

    private void createShoppingCartItem(Integer id){

        Item item = Application.getInstance().mapItem.get(id);

        CartItem cartItem = new CartItem();
        cartItem.setName("Item "+item.getId());
        cartItem.setItemId("item"+item.getId()+"d"+discount);
        cartItem.setTitle(item.getTitle());
        cartItem.setId(item.getId());
        cartItem.setPrice(item.getPrice());
        cartItem.setDiscount(discount);
        cartItem.setQuantity(quantity);



        mListener.onAddCardFragmentInteraction(cartItem);

    }

    private Double toggleDiscount(int target){

        Double discount=0.00;
        switch (target){

            case 1:
                toggle2.setChecked(false);
                toggle3.setChecked(false);
                toggle4.setChecked(false);
                toggle5.setChecked(false);
                discount = 0.00;
                break;

            case 2:
                toggle1.setChecked(false);
                toggle3.setChecked(false);
                toggle4.setChecked(false);
                toggle5.setChecked(false);
                discount = 10.00;
                break;
            case 3:
                toggle1.setChecked(false);
                toggle2.setChecked(false);
                toggle4.setChecked(false);
                toggle5.setChecked(false);
                discount = 35.50;

                break;
            case 4:
                toggle1.setChecked(false);
                toggle2.setChecked(false);
                toggle3.setChecked(false);
                toggle5.setChecked(false);
                discount = 50.00;

                break;
            case 5:
                toggle1.setChecked(false);
                toggle2.setChecked(false);
                toggle3.setChecked(false);
                toggle4.setChecked(false);
                discount = 100.00;

                break;
        }

        return discount;
    }

    public interface OnFragmentInteractionListener {
        void onAddCardFragmentInteraction(CartItem item);
        void onHideAddCardFragment();
    }
}
