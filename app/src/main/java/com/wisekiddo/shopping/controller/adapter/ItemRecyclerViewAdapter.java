package com.wisekiddo.shopping.controller.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.wisekiddo.shopping.R;
import com.wisekiddo.shopping.api.RetroClient;
import com.wisekiddo.shopping.controller.fragment.ItemListFragment.OnListFragmentInteractionListener;
import com.wisekiddo.shopping.model.Item;
import com.wisekiddo.shopping.model.data.DummyContent.DummyItem;

import java.util.List;
import java.util.Random;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {

    private final List<Item> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Random random = new Random();
    private Integer min = 10, max=100;


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
        int randomNum = random.nextInt((max - min) + 1) + min;
        mValues.get(position).setPrice( (double) randomNum);


        holder.mItem = mValues.get(position);

        holder.mNameView.setText(mValues.get(position).getTitle());
        mValues.get(position).getPrice();
        holder.mPriceView.setText("$"+mValues.get(position).getPrice()+"");


        /*Transformation transformation = new RoundedTransformationBuilder()
                .borderWidthDp(0)
                .cornerRadiusDp(50)
                .oval(false)
                .build();*/

        Picasso.with(holder.mView.getContext())
                .load(mValues.get(position).getThumbnailUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit().centerCrop()
                .error(R.mipmap.ic_launcher)
               // .transform(transformation)
                .into(holder.mImageView);

        //Log.i("DFDFD",RetroClient.ROOT_URL+mValues.get(position).getThumbnailUrl());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onItemListFragmentInteraction(holder.mItem);
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
        public final ImageView mImageView;
        public final TextView mNameView;
        public final TextView mPriceView;
        public Item mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.imageView);
            mNameView = (TextView) view.findViewById(R.id.name);
            mPriceView = (TextView) view.findViewById(R.id.price);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
