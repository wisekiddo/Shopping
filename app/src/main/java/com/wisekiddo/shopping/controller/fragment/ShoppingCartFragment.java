package com.wisekiddo.shopping.controller.fragment;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wisekiddo.shopping.Application;
import com.wisekiddo.shopping.R;
import com.wisekiddo.shopping.controller.adapter.ShoppingCartRecyclerViewAdapter;
import com.wisekiddo.shopping.model.CartItem;

import java.util.ArrayList;

public class ShoppingCartFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private ArrayList<CartItem> cartItemList = new ArrayList<>() ;
    private ShoppingCartRecyclerViewAdapter adapter;
    private TextView txtSubtotal;
    private TextView txtTotal;
    private TextView txtDiscount;


    private Double subtotal=0.00;
    private Double total=0.00;
    private Double totalDiscount=0.00;

    public ShoppingCartFragment() {
    }

    public static ShoppingCartFragment newInstance(int columnCount) {
        ShoppingCartFragment fragment = new ShoppingCartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cartitem_list, container, false);

        adapter = new ShoppingCartRecyclerViewAdapter(cartItemList, mListener);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        txtSubtotal = (TextView) view.findViewById(R.id.subtotal);
        txtTotal = (TextView) view.findViewById(R.id.total);
        txtDiscount = (TextView) view.findViewById(R.id.discount);

        Button btnClear = (Button) view.findViewById(R.id.clear);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clear();
                subtotal =0.00;
                totalDiscount =0.00;
                total =0.00;

                txtSubtotal.setText("$ "+subtotal);
                txtDiscount.setText("($ "+totalDiscount+")");
                txtTotal.setText("Charge $ "+total);

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public void addItem(CartItem item){

        adapter.updateCart(item);


        subtotal += item.getPrice()*item.getQuantity();
        if(item.getDiscount()>0) {
            totalDiscount += (item.getPrice() * item.getQuantity()) * (item.getDiscount() / 100);
        }
        total = subtotal-totalDiscount;

        txtSubtotal.setText("$ "+subtotal);
        txtDiscount.setText("($ "+totalDiscount+")");
        txtTotal.setText("Charge $ "+total);
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(CartItem item);
    }


}
