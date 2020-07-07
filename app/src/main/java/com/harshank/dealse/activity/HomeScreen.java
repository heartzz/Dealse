package com.harshank.dealse.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.harshank.dealse.R;
import com.harshank.dealse.adapter.DealsListAdapter;
import com.harshank.dealse.objects.Deals;
import com.harshank.dealse.utility.LoaderDialog;
import com.harshank.dealse.utility.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HARSHANK on 02-10-2018.
 */
public class HomeScreen extends AppCompatActivity {

    private Toolbar mToolbar;
    RecyclerView recyclerView;
    FloatingActionButton fab;
    List<Deals> itemList;
    DealsListAdapter requestListAdapter;
    public static String TAG = "Activity_RecentRequests";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_homescreen);
        //initToolbar();

        recyclerView = (RecyclerView)findViewById(R.id.search_results_list);

        LinearLayoutManager llm = new LinearLayoutManager(HomeScreen.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);




        LoaderDialog.showLoader(HomeScreen.this);
        showItemList();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Dealse");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        Tools.setSystemBarColor(this, R.color.pink_400);
    }

    private void showItemList(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference storesData = database.getReference("AllOffers");
        // Read from the database
        storesData.child("demooffers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemList = new ArrayList<>();
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    itemList.add(child.getValue(Deals.class));
                }
                if(itemList != null && !itemList.isEmpty()) {
                    requestListAdapter = new DealsListAdapter(HomeScreen.this, itemList);
                    recyclerView.setAdapter(requestListAdapter);
                }
                LoaderDialog.hideLoader();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                LoaderDialog.hideLoader();
                Log.w(TAG, "Failed to read value.", error.toException());
            }

        });
    }


}
