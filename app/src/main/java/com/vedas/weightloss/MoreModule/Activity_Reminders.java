package com.vedas.weightloss.MoreModule;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vedas.weightloss.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Rise on 13/06/2018.
 */

public class Activity_Reminders extends AppCompatActivity {

    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> name1 = new ArrayList<>();

    RecyclerView remainderRecyclerView;
    static int selectedPosition = -1;
    Remainder adapter;
    Button button;

    Toolbar toolbar;
    ImageView btn_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder);

        ButterKnife.bind(this);
        setToolbar();
        init();
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
        toolbartext.append("Reminders");
    }

    private void init() {

        name.add("Breakfast");
        name.add("Exercise");
        name.add("Water");


        name1.add("08:30Am");
        name1.add("06:00Am");
        name1.add("Every 1 hour");


        remainderRecyclerView = (RecyclerView) findViewById(R.id.remaindrrecyclerview);
        adapter = new Remainder(name, getApplicationContext());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        remainderRecyclerView.setLayoutManager(horizontalLayoutManager);
        remainderRecyclerView.setAdapter(adapter);
        remainderRecyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();


    }


    // Step 1:-
    public class Remainder extends RecyclerView.Adapter<Remainder.ViewHolder> {

        // step 3:-
        ArrayList<String> arrayList = new ArrayList<>();
        Context ctx;

        public Remainder(ArrayList<String> arrayList, Context ctx) {
            this.ctx = ctx;
            this.arrayList = arrayList;
        }

        // step 5:-
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.remainderlist, parent, false);


            ViewHolder myViewHolder = new ViewHolder(view, ctx, arrayList);
            return myViewHolder;


        }

        //step 6:-
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            holder.name.setText(name.get(position));
            holder.texttime.setText(name1.get(position));


            if (selectedPosition == position) {
                Log.e("if", "called");
            } else {
                Log.e("else", "called");

            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedPosition = position;
                    Log.e("pos", "" + selectedPosition);


                    startActivity(new Intent(getApplicationContext(), ActivityRemainder_EditPage.class));


                  /*  if (selectedPosition != position) {
                        selectedPosition = position;
                    } else {
                        selectedPosition = -1;
                    }

                    notifyDataSetChanged();*/
                }
            });


        }

        // step 4:-
        @Override
        public int getItemCount() {

            Log.e("listarray", "" + arrayList.size());
            return arrayList.size();
        }


        // Step 2:-
        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


            TextView name, texttime;

            ArrayList<String> arrayList = new ArrayList<String>();
            Context ctx;

            public ViewHolder(View itemView, Context ctx, final ArrayList<String> arrayList) {
                super(itemView);

                this.arrayList = arrayList;
                this.ctx = ctx;

                name = (TextView) itemView.findViewById(R.id.textname);
                texttime = (TextView) itemView.findViewById(R.id.texttime);

                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {


            }


        }
    }
}
