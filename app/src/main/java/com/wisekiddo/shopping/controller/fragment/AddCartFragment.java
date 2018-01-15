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

import com.wisekiddo.shopping.Application;
import com.wisekiddo.shopping.R;
import com.wisekiddo.shopping.model.CartItem;
import com.wisekiddo.shopping.model.Item;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddCartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddCartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Integer quantity=0;

    public AddCartFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AddCartFragment newInstance(String param1, String param2) {
        AddCartFragment fragment = new AddCartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

        textViewTitle = (TextView) view.findViewById(R.id.title);
        textViewQuantity = (TextView) view.findViewById(R.id.quantity);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // updateDetail();
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

    // TODO: Rename method, update argument and hook method into UI event
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


    public void addCart(Integer id){

        String title=Application.getInstance().itemList.get(id).getTitle();
        Double price =Application.getInstance().itemList.get(id).getPrice();

        textViewTitle.setText(title + " - $"+ price);

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onAddCardFragmentInteraction(CartItem item);
        void onHideAddCardFragment();
    }
}
