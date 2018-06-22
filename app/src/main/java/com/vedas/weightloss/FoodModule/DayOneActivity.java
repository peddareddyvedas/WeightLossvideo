package com.vedas.weightloss.FoodModule;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.afollestad.sectionedrecyclerview.SectionedViewHolder;
import com.vedas.weightloss.Alert.Constants;
import com.vedas.weightloss.Controllers.DayFoodController;
import com.vedas.weightloss.Controllers.NewsFeedController;
import com.vedas.weightloss.R;
import com.vedas.weightloss.Transition.BaseDetailActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class DayOneActivity extends BaseDetailActivity {
    RecyclerView addRecyclerView;
    DashBoardAdapter adapter;
    Toolbar toolbar;
    ImageView home, btn_back;
    int type;
    private boolean hideEmpty;
    private boolean showFooters = true;
    public static String foodType="";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getExtras().getInt(EXTRA_TYPE);
        setupWindowAnimations();
        setContentView(R.layout.activity_dayone);
        ButterKnife.bind(this);
        setRecyclerView();
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
        toolbartext.append("Day 1");
    }

    private void setRecyclerView() {
        addRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewbeakfast);
        adapter = new DashBoardAdapter();
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        addRecyclerView.setLayoutManager(horizontalLayoutManager);
        adapter.shouldShowHeadersForEmptySections(hideEmpty);
        adapter.shouldShowFooters(showFooters);
        addRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume", "call");
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @SuppressLint("DefaultLocale")
  public   class DashBoardAdapter extends SectionedRecyclerViewAdapter<DashBoardAdapter.MainVH> {
        ArrayList<String> footerArray = new ArrayList<>();
        ArrayList<String> headerArray = new ArrayList<>();
        ArrayList<String> headerSubArray = new ArrayList<>();

        @Override
        public int getSectionCount() {
            return 7;
        }

        @Override
        public int getItemCount(int section) {

            if (section == 0) {
                if (DayFoodController.getInstance().selectedFoodObject.weightArrayList.size() > 0) {
                    return DayFoodController.getInstance().selectedFoodObject.weightArrayList.size();
                }
            }
            if (section == 1) {
                if (DayFoodController.getInstance().selectedFoodObject.breakfastArrayList.size() > 0) {
                    return DayFoodController.getInstance().selectedFoodObject.breakfastArrayList.size();
                }
            }// lunch
            if (section == 2) {
                if (DayFoodController.getInstance().selectedFoodObject.lunchArrayList.size() > 0) {
                    return DayFoodController.getInstance().selectedFoodObject.lunchArrayList.size();
                }
            }
            // dinner
            if (section == 3) {
                if (DayFoodController.getInstance().selectedFoodObject.dinnerArrayList.size() > 0) {
                    return DayFoodController.getInstance().selectedFoodObject.dinnerArrayList.size();
                }
            }
            // snacks
            if (section == 4) {
                if (DayFoodController.getInstance().selectedFoodObject.snacksArrayList.size() > 0) {
                    return DayFoodController.getInstance().selectedFoodObject.snacksArrayList.size();
                }
            }
            if (section == 5) {
                if (DayFoodController.getInstance().selectedFoodObject.exerciseArrayList.size() > 0) {
                    return DayFoodController.getInstance().selectedFoodObject.exerciseArrayList.size();
                }
            }
            if (section == 6) {
                if (DayFoodController.getInstance().selectedFoodObject.waterArrayList.size() > 0) {
                    return DayFoodController.getInstance().selectedFoodObject.waterArrayList.size();
                }
            }
            return 1;
        }

        @Override
        public void onBindHeaderViewHolder(MainVH holder, int section, boolean expanded) {
            headerArray.add("Weight");
            headerArray.add("Breakfast");
            headerArray.add("Lunch");
            headerArray.add("Dinner");
            headerArray.add("Snacks");
            headerArray.add("Exercises");
            headerArray.add("Water");

            //
            headerSubArray.add("0 Kg");
            headerSubArray.add("0 cal");
            headerSubArray.add("0 cal");
            headerSubArray.add("");
            headerSubArray.add("0 cal");
            headerSubArray.add("0 cal");
            headerSubArray.add("");


            holder.headertitle.setText(headerArray.get(section).toString());
            holder.headertitle1.setText(headerSubArray.get(section).toString());
        }

        @Override
        public void onBindFooterViewHolder(MainVH holder, final int section) {
            footerArray.add("Add Weight");
            footerArray.add("Add Food");
            footerArray.add("Add Food");
            footerArray.add("Add Food");
            footerArray.add("Add Food");
            footerArray.add("Add Exercise");
            footerArray.add("Add Water");

            holder.name.setText(footerArray.get(section).toString());
            holder.footerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("footerpos", "call" + footerArray.get(section).toString());
                    if (section == 0) {
                        Intent i = new Intent(getApplicationContext(), AddWeightActivity.class);
                        transitionTo(i);
                    } else if (section == 1) {
                        foodType= Constants.foodKeys.breakfast.toString();
                        Intent i = new Intent(getApplicationContext(), BreakfastActivity.class);
                        transitionTo(i);
                    }else if (section == 2) {
                        foodType= Constants.foodKeys.lunch.toString();
                        Intent i = new Intent(getApplicationContext(), BreakfastActivity.class);
                        transitionTo(i);
                    }else if (section == 3) {
                        foodType= Constants.foodKeys.dinner.toString();
                        Intent i = new Intent(getApplicationContext(), BreakfastActivity.class);
                        transitionTo(i);
                    } else if (section == 4) {
                        foodType= Constants.foodKeys.snacks.toString();
                        Intent i = new Intent(getApplicationContext(), BreakfastActivity.class);
                        transitionTo(i);
                    } else if (section == 5) {
                        Intent i = new Intent(getApplicationContext(), ExerciseActivity.class);
                        transitionTo(i);
                    } else if (section == 6) {
                        Intent i = new Intent(getApplicationContext(), AddWatterActivity.class);
                        transitionTo(i);
                    }
                }
            });
        }

        @Override
        public void onBindViewHolder(MainVH holder, int section, int relativePosition, int absolutePosition) {
            //load text view data
            DayFoodController.getInstance().loadTextviewdata(holder,section,relativePosition,absolutePosition);
        }

        @Override
        public int getItemViewType(int section, int relativePosition, int absolutePosition) {
            return super.getItemViewType(section, relativePosition, absolutePosition);
        }

        @Override
        public MainVH onCreateViewHolder(ViewGroup parent, int viewType) {
            int layout;
            switch (viewType) {
                case VIEW_TYPE_HEADER:
                    layout = R.layout.list_item_header;
                    break;
                case VIEW_TYPE_ITEM:
                    layout = R.layout.list_item_main;
                    break;
                case VIEW_TYPE_FOOTER:
                    layout = R.layout.list_item_footer;
                    break;
                default:
                    // Our custom item, which is the 0 returned in getItemViewType() above
                    layout = R.layout.list_item_main;
                    break;
            }

            View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
            return new MainVH(v, this);
        }

   public class MainVH extends SectionedViewHolder implements View.OnClickListener {

        public     TextView name, calories, exercise_time;
       public RelativeLayout footerView;
       public TextView headertitle, headertitle1;
            // final ImageView caret;
            final DashBoardAdapter adapter;
       public  View view;

            MainVH(View itemView, DashBoardAdapter adapter) {
                super(itemView);
                this.name = itemView.findViewById(R.id.title);
                this.calories = itemView.findViewById(R.id.title1);
                this.headertitle = itemView.findViewById(R.id.headertitle);
                this.headertitle1 = itemView.findViewById(R.id.headertitle1);
                this.footerView = itemView.findViewById(R.id.footer);
                this.view = itemView.findViewById(R.id.view);
                this.exercise_time = itemView.findViewById(R.id.time);
                this.adapter = adapter;
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                if (isFooter()) {
                    Log.e("footer", "call");

                }

            }
        }
    }

    }

        /*if (DayFoodController.getInstance().selectedFoodObject.breakfastArrayList.size() > 0) {
            adapter.notifyDataSetChanged();
        }
        if (DayFoodController.getInstance().selectedFoodObject.lunchArrayList.size() > 0) {
            adapter.notifyDataSetChanged();
        }
        if (DayFoodController.getInstance().selectedFoodObject.dinnerArrayList.size() > 0) {
            adapter.notifyDataSetChanged();
        }
        if (DayFoodController.getInstance().selectedFoodObject.snacksArrayList.size() > 0) {
            adapter.notifyDataSetChanged();
        }
        if (DayFoodController.getInstance().selectedFoodObject.weightArrayList.size() > 0) {
            adapter.notifyDataSetChanged();
        }
        if (DayFoodController.getInstance().selectedFoodObject.exerciseArrayList.size() > 0) {
            adapter.notifyDataSetChanged();
        }
        if (DayFoodController.getInstance().selectedFoodObject.waterArrayList.size() > 0) {
            adapter.notifyDataSetChanged();
        }*/




