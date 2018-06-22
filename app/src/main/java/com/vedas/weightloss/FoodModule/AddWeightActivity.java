package com.vedas.weightloss.FoodModule;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.vedas.weightloss.Controllers.DayFoodController;
import com.vedas.weightloss.Controllers.PersonalInfoController;
import com.vedas.weightloss.Models.DayFoodObject;
import com.vedas.weightloss.Models.WeightObject;
import com.vedas.weightloss.R;
import com.vedas.weightloss.Transition.BaseDetailActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by VEDAS on 6/7/2018.
 */

public class AddWeightActivity extends BaseDetailActivity {
    int type;
    TextInputEditText textInputweight;
    Toolbar toolbar;
    TextView tool_text;
    ImageView btn_back;
    ArrayList<String> weightMeasures;
    String selectedMeasure = "Kilograms";
    String selectedWeight="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addweight);
        ButterKnife.bind(this);
        setupWindowAnimations();
        textInputweight = (TextInputEditText) findViewById(R.id.weight);
        setToolbar();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                textInputweight.setVisibility(View.VISIBLE);
            }
        }, 500);
        textInputweight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("weight", "call" + s.toString());
                selectedWeight=s.toString();
            }
        });
        loadWeghtMeasureSpinner();
    }
    @OnClick({R.id.set})
    public void saveAction(){
        if (selectedWeight.length()>0){
            WeightObject weightObject=new WeightObject();
            weightObject.setWeight(selectedWeight);
            weightObject.setUnits(selectedMeasure);
            DayFoodController.getInstance().selectedFoodObject.weightArrayList.add(weightObject);
            Log.e("weightarray","call"+DayFoodController.getInstance().selectedFoodObject.weightArrayList.size());
            finish();
        }else {
            final View contextView = findViewById(R.id.context_view);
            Snackbar.make(contextView, R.string.selectitem2, Snackbar.LENGTH_LONG)
                    .setActionTextColor(Color.CYAN)
                    .setAction(R.string.undo, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }

                    }).show();
        }
        /*Intent intent1 = new Intent(getApplicationContext(),DayOneActivity.class);
        intent1.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
        transitionTo(intent1);*/
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tool_text = (TextView) toolbar.findViewById(R.id.toolbar_text);
        tool_text.setText("Add Weight");
        btn_back = (ImageView) toolbar.findViewById(R.id.back);
        btn_back.setBackgroundResource(R.drawable.ic_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

    private void loadWeghtMeasureSpinner() {
        weightMeasures = new ArrayList<>();
        weightMeasures.add("Kilograms");
        weightMeasures.add("Lbs");

        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, weightMeasures);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        if (selectedMeasure != null) {
            int spinnerPosition = aa.getPosition(selectedMeasure);
            spin.setSelection(spinnerPosition);
        }
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMeasure = weightMeasures.get(i);
                Log.e("selectedMeasure", "call" + selectedMeasure);
                if (selectedWeight.length()>0){
                    if (selectedMeasure.equals("Kilograms")){
                        int weight=Integer.parseInt(selectedWeight);
                        double kgValue = weight * 0.453592;
                        selectedWeight=String.valueOf(Math.round(kgValue));
                        textInputweight.setText(selectedWeight);
                    }else {
                        int weight=Integer.parseInt(selectedWeight);
                        double kgValue = weight *  2.2046;
                        selectedWeight=String.valueOf(Math.round(kgValue));
                        textInputweight.setText(selectedWeight);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setupWindowAnimations() {
        Transition transition;
        if (type == TYPE_PROGRAMMATICALLY) {
            transition = buildEnterTransition();
        } else {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
        }
        getWindow().setEnterTransition(transition);
    }

    private Transition buildEnterTransition() {
        Explode enterTransition = new Explode();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
        return enterTransition;
    }
}
