package com.harshank.dealse.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.harshank.dealse.R;
import com.harshank.dealse.activity.Activity_DealsDetails;
import com.harshank.dealse.activity.HomeScreen;
import com.harshank.dealse.objects.Deals;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

/**
 * Created by harshank on 19/6/18.
 */

public class DealsListAdapter extends RecyclerView.Adapter<DealsListAdapter.MyViewHolder> {


    private Context a;
    private List<Deals> itemsList;
    int[] image_array = new int[] { R.drawable.image1, R.drawable.image2,
            R.drawable.image3, R.drawable.image4 };

    public DealsListAdapter(Context a, List<Deals> itemsList) {
        this.a = a;
        this.itemsList = itemsList;
    }

    @Override
    public DealsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offersitemview, parent, false);

        return new DealsListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DealsListAdapter.MyViewHolder holder, final int position) {


       // Picasso.with(a).load(image_array[position]).placeholder(R.drawable.image_10).into(holder.thingimg);

        Picasso.with(a)
                .load(itemsList.get(position).OfferImage)
                //       .fit()
                .skipMemoryCache()
                .into(holder.thingimg, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });


        holder.storenametv.setText(itemsList.get(position).storeName);
        holder.durationtv.setText("Until "+itemsList.get(position).closingTime);
        holder.offernametv.setText(itemsList.get(position).OfferName);
        holder.countdistancetv.setText(itemsList.get(position).itemsCount + " - " + itemsList.get(position).storeDistance);

        holder.lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent detailIntent = new Intent(a,Activity_DealsDetails.class);
                a.startActivity(detailIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView storenametv,durationtv,offernametv,countdistancetv;
        ImageView thingimg;
        ProgressBar progressBar;
        LinearLayout lyt_parent;


        public MyViewHolder(View view) {
            super(view);


            thingimg = (ImageView)view.findViewById(R.id.image);
            lyt_parent = (LinearLayout)view.findViewById(R.id.lyt_parent);

            storenametv = (TextView) view.findViewById(R.id.title);
            durationtv = (TextView)view.findViewById(R.id.subtitle);
            offernametv = (TextView)view.findViewById(R.id.date1);
            countdistancetv = (TextView)view.findViewById(R.id.date);


        }
    }
}