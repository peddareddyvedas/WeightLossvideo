package com.vedas.weightloss.FoodModule;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vedas.weightloss.Controllers.DayFoodController;
import com.vedas.weightloss.Models.ExerciseObject;
import com.vedas.weightloss.R;
import com.vedas.weightloss.Transition.BaseDetailActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class ExerciseActivity extends BaseDetailActivity {

    ArrayList<String> exerciseArray;

    RecyclerView recyclerView;
    View view;
    int selectedPosition = -1;
    ExerciseAdapter adapter;
    Toolbar toolbar;
    ImageView home, btn_back;
    com.github.clans.fab.FloatingActionButton fab;
    EditText searchBox;
    ImageView cancel;
    int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //type = getIntent().getExtras().getInt(EXTRA_TYPE);
        setupWindowAnimations();
        setContentView(R.layout.activity_exercise);
        ButterKnife.bind(this);
        init();
        setToolbar();
    }


    private void setupWindowAnimations() {
        Transition transition;

        if (type == TYPE_PROGRAMMATICALLY) {
            transition = buildEnterTransition();
        } else {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.slide);
        }
        getWindow().setEnterTransition(transition);
    }
    private Transition buildEnterTransition() {
        Explode enterTransition = new Explode();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
        return enterTransition;
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btn_back = (ImageView) toolbar.findViewById(R.id.back);
        home = (ImageView) toolbar.findViewById(R.id.toolbar_icon);
        btn_back.setBackgroundResource(R.drawable.ic_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        TextView toolbartext = (TextView) toolbar.findViewById(R.id.toolbar_text);
        toolbartext.append("Add Exercise");
    }

    private void setRecyclerView(ArrayList<String> nameList) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewexercise);
        adapter = new ExerciseAdapter(nameList, getApplicationContext());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void init() {
        exerciseArray = new ArrayList<>();
        exerciseArray.add("Aerobics, general");
        exerciseArray.add("Aerobics, high impact");
        exerciseArray.add("Aerobics, low impact");
        exerciseArray.add("Aerobics, step , with 10-12 inch step");
        exerciseArray.add("Aerobics, step , with 6-8 inch step");
        exerciseArray.add("Badminton");
        exerciseArray.add("BaseBall");
        exerciseArray.add("Basketball");
        exerciseArray.add("Bycyclling, 16-19 kph, light");
        exerciseArray.add("Bycyclling, 24-30 kph, Hard");

        fab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                Intent intent1 = new Intent(getApplicationContext(),AddExerciseActivity.class);
                intent1.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
                transitionTo(intent1);
            }
        });
        searchBox = (EditText) findViewById(R.id.searchBox);
        cancel = (ImageView) findViewById(R.id.img_cancel);

        setRecyclerView(exerciseArray);

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString().toLowerCase());
                cancel.setVisibility(View.VISIBLE);
                if (searchBox.getText().toString().isEmpty()) {
                    cancel.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString().toLowerCase());
                cancel.setVisibility(View.VISIBLE);
                if (searchBox.getText().toString().isEmpty()) {
                    cancel.setVisibility(View.GONE);
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchBox.getText().toString().length() > 0) {
                    searchBox.setText("");
                }
            }
        });
    }

    private void filter(String text) {
        ArrayList<String> nameList = new ArrayList<>();
        for (String name : exerciseArray) {
            if (name.toLowerCase().startsWith(text)) {
                nameList.add(name);
            }
        }
        setRecyclerView(nameList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {
        ArrayList<String> arrayList = new ArrayList<>();
        Context ctx;
        public ExerciseAdapter(ArrayList<String> arrayList, Context ctx) {
            this.ctx = ctx;
            this.arrayList = arrayList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_breakfast, parent, false);
            ViewHolder myViewHolder = new ViewHolder(view, ctx, arrayList);
            return myViewHolder;
        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.exercise_name.setText(arrayList.get(position));
            if (selectedPosition == position) {
                loadDialogueForAddingCalories(arrayList.get(position));
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedPosition!=position){
                        selectedPosition = position;
                        adapter.notifyDataSetChanged();
                    }else {
                        selectedPosition=-1;
                        adapter.notifyDataSetChanged();

                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView exercise_name, calories;
            ArrayList<String> arrayList = new ArrayList<String>();
            Context ctx;

            public ViewHolder(View itemView, Context ctx, final ArrayList<String> arrayList) {
                super(itemView);
                this.arrayList = arrayList;
                this.ctx = ctx;
                exercise_name = (TextView) itemView.findViewById(R.id.itemName);
                calories = (TextView) itemView.findViewById(R.id.calories);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
            }
        }
    }
    public void loadDialogueForAddingCalories(String exercisename) {
        final Dialog mod = new Dialog(ExerciseActivity.this);
        mod.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mod.setContentView(R.layout.activity_exerciseaddalert);
        mod.setCanceledOnTouchOutside(false);
        mod.setCancelable(false);
        mod.show();
        mod.getWindow().setBackgroundDrawableResource(R.drawable.layout_cornerbg);
        RelativeLayout rl_measure = (RelativeLayout) mod.findViewById(R.id.measure);
        final TextView selected_measure = (TextView) mod.findViewById(R.id.selected_type);
        final TextView iteam = (TextView) mod.findViewById(R.id.iteam);
        iteam.setText(exercisename);
        final TextView txt_set = (TextView) mod.findViewById(R.id.set);
        final EditText caloriestext = (EditText) mod.findViewById(R.id.caloriesedittext);
        final EditText ed_hour = (EditText) mod.findViewById(R.id.ed1);
        final EditText ed_min = (EditText) mod.findViewById(R.id.ed2);

        txt_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExerciseObject exerciseObject=new ExerciseObject();
                exerciseObject.setExerciseName(iteam.getText().toString());
                exerciseObject.setEstimatedCaloriesBurned(caloriestext.getText().toString());
                exerciseObject.setExerciseDuration(ed_hour.getText().toString()+" : "+ed_min.getText().toString());
                DayFoodController.getInstance().selectedFoodObject.exerciseArrayList.add(exerciseObject);
                mod.dismiss();
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }


}


