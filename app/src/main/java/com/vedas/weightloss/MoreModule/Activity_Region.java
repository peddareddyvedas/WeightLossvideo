package com.vedas.weightloss.MoreModule;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vedas.weightloss.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Rise on 14/06/2018.
 */

public class Activity_Region extends AppCompatActivity {
    Toolbar toolbar;
    ImageView btn_back;


    ArrayList<String> name = new ArrayList<>();

    RecyclerView regionRecyclerView;
    static int selectedPosition = -1;
    Region adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        ButterKnife.bind(this);
        init();
        setToolbar();


    }
    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btn_back = (ImageView) toolbar.findViewById(R.id.back);
        btn_back.setBackgroundResource(R.drawable.ic_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextView toolbartext = (TextView) toolbar.findViewById(R.id.toolbar_text);
        toolbartext.append("Region");
    }

    private void init() {
        name.add("Guyana");
        name.add("Haiti");
        name.add("Honduras");

        name.add("Hong Kong");
        name.add("Hungary");
        name.add("Iceland");

        name.add("India");
        name.add("Indonasia");
        name.add("Irland");

        name.add("Israel");
        name.add("Italy");
        name.add("China");

        name.add("Iraque");
        name.add("Japan");
        name.add("Europe");

        regionRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewregion);
        adapter = new Region(name, getApplicationContext());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        regionRecyclerView.setLayoutManager(horizontalLayoutManager);
        regionRecyclerView.setAdapter(adapter);
        regionRecyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
    }


    // Step 1:-
    public class Region extends RecyclerView.Adapter<Region.ViewHolder> {

        // step 3:-
        ArrayList<String> arrayList = new ArrayList<>();
        Context ctx;

        public Region(ArrayList<String> arrayList, Context ctx) {
            this.ctx = ctx;
            this.arrayList = arrayList;
        }

        // step 5:-
        @Override
        public Region.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.regionlist, parent, false);


            Region.ViewHolder myViewHolder = new Region.ViewHolder(view, ctx, arrayList);
            return myViewHolder;


        }

        //step 6:-
        @Override
        public void onBindViewHolder(final Region.ViewHolder holder, final int position) {

            holder.name.setText(name.get(position));

            if (selectedPosition == position) {
                holder.imageView.setVisibility(View.VISIBLE);
                holder.name.setTextColor(Color.parseColor("#FF0012"));

            } else {
                holder.imageView.setVisibility(View.GONE);
                holder.name.setTextColor(Color.parseColor("#000000"));


            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                   if (selectedPosition != position) {
                        selectedPosition = position;
                    } else {
                        selectedPosition = -1;
                    }

                    notifyDataSetChanged();
                }
            });


        }

        // step 4:-
        @Override
        public int getItemCount() {

            Log.e("regionlist", "" + arrayList.size());
            return arrayList.size();
        }


        // Step 2:-
        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


            TextView name;
            ImageView imageView;

            ArrayList<String> arrayList = new ArrayList<String>();
            Context ctx;

            public ViewHolder(View itemView, Context ctx, final ArrayList<String> arrayList) {
                super(itemView);

                this.arrayList = arrayList;
                this.ctx = ctx;

                name = (TextView) itemView.findViewById(R.id.regionname);
                imageView=(ImageView)itemView.findViewById(R.id.image);


                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {


            }


        }
    }
    }

