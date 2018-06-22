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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.vedas.weightloss.Alert.Constants;
import com.vedas.weightloss.Controllers.DayFoodController;
import com.vedas.weightloss.Models.BreakfastObject;
import com.vedas.weightloss.R;
import com.vedas.weightloss.Transition.BaseDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class BreakfastActivity extends BaseDetailActivity {
    ArrayList<String> breakfastArray = new ArrayList<>();
    RecyclerView addRecyclerView;
    View view;
    int selectedPosition = -1;
    BreakfastAdapter adapter;
    Toolbar toolbar;
    ImageView home, btn_back;
    com.github.clans.fab.FloatingActionButton fab;
    EditText searchBox;
    ImageView cancel;
    int type;
    String servingSize = "0";
    String servingContainer = "0", units = "units";
    BreakfastObject breakfastObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //type = getIntent().getExtras().getInt(EXTRA_TYPE);
        setupWindowAnimations();
        setContentView(R.layout.activity_breakfast);
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
        toolbartext.append("Breakfast");
    }

    private void setRecyclerView(ArrayList<String> nameList) {
        addRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewbeakfast);
        adapter = new BreakfastAdapter(nameList, getApplicationContext());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        addRecyclerView.setLayoutManager(horizontalLayoutManager);
        addRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void init() {
        if (DayOneActivity.foodType.equals(Constants.foodKeys.lunch.toString())) {
            breakfastArray.add("Biriyani");
            breakfastArray.add("Rice");
            breakfastArray.add("Dall");
            breakfastArray.add("Rasam");
            breakfastArray.add("Pickel");
            breakfastArray.add("Curd Rice");
            breakfastArray.add("Aalu fry");
            breakfastArray.add("Egg");
        } else if (DayOneActivity.foodType.equals(Constants.foodKeys.dinner.toString())) {
            breakfastArray.add("Biriyani");
            breakfastArray.add("Rice");
            breakfastArray.add("Dall");
            breakfastArray.add("Rasam");
            breakfastArray.add("Pickel");
            breakfastArray.add("Curd Rice");
            breakfastArray.add("Aalu fry");
            breakfastArray.add("Egg");
            // snacksArray = ["bhelpuri","panipuri","gobi","cutlet","noodles","aloopuri","samosa"]
        }else if (DayOneActivity.foodType.equals(Constants.foodKeys.snacks.toString())) {
            breakfastArray.add("bhelpuri");
            breakfastArray.add("panipuri");
            breakfastArray.add("gobi");
            breakfastArray.add("cutlet");
            breakfastArray.add("noodles");
            breakfastArray.add("aloopuri");
            breakfastArray.add("samosa");
        }else {
            breakfastArray.add("Dosa");
            breakfastArray.add("Upma");
            breakfastArray.add("Pasaratu");
            breakfastArray.add("Pongal");
            breakfastArray.add("Samosa");
            breakfastArray.add("Kichidi");
            breakfastArray.add("Panipuri");
            breakfastArray.add("Roti");
            breakfastArray.add("Special Dosa");
            breakfastArray.add("Puri");
            breakfastArray.add("Rihte");
            breakfastArray.add("Idly");
            breakfastArray.add("Vada");
        }
        fab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                Intent intent1 = new Intent(getApplicationContext(), AddBreakfastActivity.class);
                intent1.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
                transitionTo(intent1);
            }
        });
        searchBox = (EditText) findViewById(R.id.searchBox);
        cancel = (ImageView) findViewById(R.id.img_cancel);

        setRecyclerView(breakfastArray);

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
        for (String name : breakfastArray) {
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

    public class BreakfastAdapter extends RecyclerView.Adapter<BreakfastAdapter.ViewHolder> {
        ArrayList<String> arrayList = new ArrayList<>();
        Context ctx;

        public BreakfastAdapter(ArrayList<String> arrayList, Context ctx) {
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
            holder.breakfast_name.setText(arrayList.get(position));
            if (selectedPosition == position) {
                loadDialogueForAddingCalories(arrayList.get(position));
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedPosition != position) {
                        selectedPosition = position;
                        adapter.notifyDataSetChanged();
                    } else {
                        selectedPosition = -1;
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
            TextView breakfast_name, calories;
            ArrayList<String> arrayList = new ArrayList<String>();
            Context ctx;

            public ViewHolder(View itemView, Context ctx, final ArrayList<String> arrayList) {
                super(itemView);
                this.arrayList = arrayList;
                this.ctx = ctx;
                breakfast_name = (TextView) itemView.findViewById(R.id.itemName);
                calories = (TextView) itemView.findViewById(R.id.calories);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
            }
        }
    }

    public void loadDialogueForAddingCalories(final String foodname) {
        final Dialog mod = new Dialog(BreakfastActivity.this);
        mod.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mod.setContentView(R.layout.activity_servingsize);
        mod.setCanceledOnTouchOutside(false);
        mod.setCancelable(false);
        mod.show();
        mod.getWindow().setBackgroundDrawableResource(R.drawable.layout_cornerbg);
        RelativeLayout rl_measure = (RelativeLayout) mod.findViewById(R.id.measure);
        final TextView selected_measure = (TextView) mod.findViewById(R.id.selected_type);
        final TextView iteam = (TextView) mod.findViewById(R.id.iteam);
        iteam.setText(foodname);
        final TextView txt_set = (TextView) mod.findViewById(R.id.set);
        final EditText caloriestext = (EditText) mod.findViewById(R.id.caloriesedittext);
        txt_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("foodname", "ca;l" + iteam.getText().toString());
                breakfastObject = new BreakfastObject();
                breakfastObject.setFoodName(iteam.getText().toString());
                breakfastObject.setEsimatedCalories(caloriestext.getText().toString());
                breakfastObject.setServingSize(servingSize);
                breakfastObject.setServingPerContainer(servingContainer);
                breakfastObject.setUnits(units);

                if (DayOneActivity.foodType.equals(Constants.foodKeys.lunch.toString()))
                {
                    DayFoodController.getInstance().selectedFoodObject.lunchArrayList.add(breakfastObject);
                } else if (DayOneActivity.foodType.equals(Constants.foodKeys.dinner.toString()))
                {
                    DayFoodController.getInstance().selectedFoodObject.dinnerArrayList.add(breakfastObject);
                }else if (DayOneActivity.foodType.equals(Constants.foodKeys.snacks.toString()))
                {
                    DayFoodController.getInstance().selectedFoodObject.snacksArrayList.add(breakfastObject);
                } else
                {
                    DayFoodController.getInstance().selectedFoodObject.breakfastArrayList.add(breakfastObject);
                }
                mod.dismiss();
                DayOneActivity.foodType="";
                finish();
            }
        });
        Spinner spin = (Spinner) mod.findViewById(R.id.spinnersize);
        List<String> categories = new ArrayList<String>();
        categories.add("0");
        categories.add("1");
        categories.add("2");
        categories.add("3");
        categories.add("4");
        categories.add("5");
        categories.add("6");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                servingSize = parent.getItemAtPosition(position).toString();
                // Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner spin1 = (Spinner) mod.findViewById(R.id.spinnerunits);
        List<String> categories1 = new ArrayList<String>();
        categories1.add("Units");
        categories1.add("1");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(dataAdapter1);

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                units = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner spin2 = (Spinner) mod.findViewById(R.id.spinnerpercontainer);
        List<String> categories2 = new ArrayList<String>();
        categories2.add("0");
        categories2.add("1");
        categories2.add("2");
        categories2.add("3");
        categories2.add("4");
        categories2.add("5");

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(dataAdapter2);
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                servingContainer = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }


}


