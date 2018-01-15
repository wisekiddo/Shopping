package com.wisekiddo.shopping.controller.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wisekiddo.shopping.Application;
import com.wisekiddo.shopping.R;
import com.wisekiddo.shopping.controller.fragment.ShoppingCartFragment.OnListFragmentInteractionListener;
import com.wisekiddo.shopping.model.CartItem;

import java.util.ArrayList;

public class ShoppingCartRecyclerViewAdapter extends RecyclerView.Adapter<ShoppingCartRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<CartItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ShoppingCartRecyclerViewAdapter(ArrayList<CartItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cartitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        if(mValues.get(position).getPrice() != null) {
            Double totalItemPrice = mValues.get(position).getPrice() * mValues.get(position).getQuantity();

        holder.mItem = mValues.get(position);
        holder.mNameView.setText(mValues.get(position).getName());
        holder.mQuantityView.setText("x"+mValues.get(position).getQuantity());
        holder.mPriceView.setText("$ "+totalItemPrice);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void updateCart(CartItem item){

        boolean same=false;
        if(mValues.size()>0){

            for(int i=0;i<mValues.size();i++){

                if(mValues.get(i).getItemId().equals(item.getItemId())){
                    Log.i("--->",mValues.get(i).getItemId() +"=="+item.getItemId());

                    mValues.get(i).setQuantity(mValues.get(i).getQuantity() + item.getQuantity());
                    same=true;
                    break;
                }
            }




        }

        if(!same){
            Application.getInstance().cartItem.put("item"+item.getId()+item.getDiscount(),item);
            mValues.add(item);
        }


        notifyDataSetChanged();
    }

    public void clear(){
        mValues.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mNameView;
        public final TextView mQuantityView;
        public final TextView mPriceView;
        public CartItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.name);
            mQuantityView = (TextView) view.findViewById(R.id.quantity);
            mPriceView = (TextView) view.findViewById(R.id.price);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }

    }

}
