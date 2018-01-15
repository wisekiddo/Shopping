package com.wisekiddo.shopping.controller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wisekiddo.shopping.R;
import com.wisekiddo.shopping.controller.fragment.ItemListFragment.OnListFragmentInteractionListener;
import com.wisekiddo.shopping.model.Item;

import java.util.List;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {

    private final List<Item> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ItemRecyclerViewAdapter(List<Item> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mItem = mValues.get(position);
        holder.mIdView.setText("Item " + mValues.get(position).getId());
        holder.mNameView.setText(mValues.get(position).getTitle());

        mValues.get(position).getPrice();
        holder.mPriceView.setText("$"+mValues.get(position).getPrice()+"");

        Picasso.with(holder.mView.getContext())
                .load(mValues.get(position).getThumbnailUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit().centerCrop()
                .error(R.mipmap.ic_launcher)
                .into(holder.mImageView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onItemListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        try{
            return mValues.size();
        }catch (Exception err){

        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final ImageView mImageView;
        public final TextView mIdView;
        public final TextView mNameView;
        public final TextView mPriceView;
        public Item mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.imageView);
            mNameView = (TextView) view.findViewById(R.id.name);
            mIdView = (TextView) view.findViewById(R.id.id);
            mPriceView = (TextView) view.findViewById(R.id.price);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }

    }
}