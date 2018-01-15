package com.wisekiddo.shopping.controller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wisekiddo.shopping.R;
import com.wisekiddo.shopping.controller.fragment.DiscountListFragment.OnListFragmentInteractionListener;
import com.wisekiddo.shopping.model.Discount;

import java.util.List;

public class DiscountRecyclerViewAdapter extends RecyclerView.Adapter<DiscountRecyclerViewAdapter.ViewHolder> {

    private final List<Discount> mValues;
    private final OnListFragmentInteractionListener mListener;

    public DiscountRecyclerViewAdapter(List<Discount> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_discount, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText("%"); //REPLACE WITH IMAGEVIEW
        holder.mNameView.setText(mValues.get(position).getName()+"");
        holder.mPercentageView.setText(mValues.get(position).getPercentage()+"%");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onDiscountFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView; //REPLACE WITH IMAGEVIEW
        public final TextView mNameView;
        public final TextView mPercentageView;
        public Discount mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mNameView = (TextView) view.findViewById(R.id.name);
            mPercentageView = (TextView) view.findViewById(R.id.percentage);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
