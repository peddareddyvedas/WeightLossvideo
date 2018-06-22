package com.vedas.weightloss.FoodModule;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.vedas.weightloss.Controllers.DayFoodController;
import com.vedas.weightloss.Models.WaterObject;
import com.vedas.weightloss.R;
import com.vedas.weightloss.Transition.BaseDetailActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by VEDAS on 6/7/2018.
 */

public class AddWatterActivity extends BaseDetailActivity {
    int type;
    Toolbar toolbar;
    TextView tool_text, txt_measure;
    ImageView btn_back, imag_alarm, img_info;
    Button ml, ml1, ml2;
    TextInputEditText txt_water;
    ArrayList<String> waterArray;
    String selectedMeasure = "Milliliter";
    int selectedmlVal;
    double selectedliVal=0.0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addwatter);
        ButterKnife.bind(this);
        setupWindowAnimations();
        setToolbar();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                txt_water.setVisibility(View.VISIBLE);
            }
        }, 500);
        loadWaterMeasureSpinner();
        setButtonActions();
    }

    @OnClick({R.id.set})
    public void saveAction() {
        WaterObject waterObject=new WaterObject();
        waterObject.setWaterUnits(selectedMeasure);
        if (selectedMeasure=="Milliliter"){
            waterObject.setWaterContent(String.valueOf(selectedmlVal));
        }else {
            waterObject.setWaterContent(String.valueOf(selectedliVal));
        }
        DayFoodController.getInstance().selectedFoodObject.waterArrayList.add(waterObject);
        finish();

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tool_text = (TextView) toolbar.findViewById(R.id.toolbar_text);
        tool_text.setText("Add Watter");
        btn_back = (ImageView) toolbar.findViewById(R.id.back);
        imag_alarm = (ImageView) toolbar.findViewById(R.id.img_share);
        img_info = (ImageView) toolbar.findViewById(R.id.img_refresh);
        imag_alarm.setBackgroundResource(R.drawable.ic_alarm);
        img_info.setBackgroundResource(R.drawable.ic_info);
        txt_water = (TextInputEditText) findViewById(R.id.water);
        txt_measure = (TextView) findViewById(R.id.txt_measure);

        ml = (Button) findViewById(R.id.ml);
        ml1 = (Button) findViewById(R.id.ml1);
        ml2 = (Button) findViewById(R.id.ml2);

        btn_back.setBackgroundResource(R.drawable.ic_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txt_water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(txt_water.getWindowToken(), 0);
            }
        });
    }

    private void setButtonActions() {
        ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String buttonText = ml.getText().toString();
                String[] a = buttonText.split(" ");
                if (buttonText.contains("ml"))
                {
                    if (txt_water.getText().toString().length() > 0) {
                        selectedmlVal = selectedmlVal + Integer.parseInt(a[1]);
                        Log.e("chanduty", "call" + selectedmlVal);
                        txt_water.setText(String.valueOf(selectedmlVal));
                    } else {
                        selectedmlVal = 250;
                        txt_water.setText("250");
                    }
                }else {
                    if (txt_water.getText().toString().length() > 0) {
                        selectedliVal = selectedliVal + Double.parseDouble(a[1]);
                        txt_water.setText(String.valueOf(selectedliVal));
                    } else {
                        selectedliVal = 0.25;
                        txt_water.setText("0.25");
                    }
                }


            }
        });
        ml1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String buttonText = ml1.getText().toString();
                String[] a = buttonText.split(" ");
                if (buttonText.contains("ml")){
                    if (txt_water.getText().toString().length() > 0) {
                        selectedmlVal = selectedmlVal + Integer.parseInt(a[1]);
                        Log.e("chanduty", "call" + selectedmlVal);
                        txt_water.setText(String.valueOf(selectedmlVal));
                    } else {
                        selectedmlVal = 500;
                        txt_water.setText("500");
                    }
                }else {
                    if (txt_water.getText().toString().length() > 0) {
                        selectedliVal = selectedliVal + Double.parseDouble(a[1]);
                        Log.e("chanduty", "call" + selectedliVal);
                        txt_water.setText(String.valueOf(selectedliVal));
                    } else {
                        selectedliVal = 0.5;
                        txt_water.setText("0.5");
                    }
                }


            }
        });
        ml2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String buttonText = ml2.getText().toString();
                String[] a = buttonText.split(" ");
                if (buttonText.contains("ml")){
                    if (txt_water.getText().toString().length() > 0) {
                        selectedmlVal = selectedmlVal + Integer.parseInt(a[1]);
                        Log.e("chanduty", "call" + selectedmlVal);
                        txt_water.setText(String.valueOf(selectedmlVal));
                    } else {
                        selectedmlVal = 1000;
                        txt_water.setText("1000");
                    }
                }else {
                    Log.e("licalleddathu", "call" + Double.parseDouble(a[1]));
                    if (txt_water.getText().toString().length() > 0) {
                        selectedliVal = selectedliVal + Double.parseDouble(a[1]);
                        Log.e("licalled", "call" + selectedliVal);
                        txt_water.setText(String.valueOf(selectedliVal));
                    } else {
                        selectedliVal = 1.0;
                        txt_water.setText("1.0");
                    }
                }

            }
        });

    }

    private double convertMilliletersTOLiters(int milliVal) {
        Log.e("millileater", "call" + milliVal);
        double liters = milliVal / 1000.0;
        Log.e("liters", "calll" + liters);
        return liters;
    }

    private int convertLitersToMillileters(int liters) {
        int millil = liters * 1000;
        Log.e("millil", "calll" + millil);
        return millil;
    }

    private void loadWaterMeasureSpinner() {
        waterArray = new ArrayList<>();
        waterArray.add("Milliliter");
        waterArray.add("Liter");
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinnerwater);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, waterArray);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        if (selectedMeasure != null) {
            int spinnerPosition = aa.getPosition(selectedMeasure);
            spin.setSelection(spinnerPosition);
        }
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMeasure = waterArray.get(i);
                txt_measure.setText(selectedMeasure);
                loadButtonText(selectedMeasure);
                if (selectedMeasure == "Milliliter") {
                    Log.e("selectedGender", "call" + selectedMeasure);
                    String v = txt_water.getText().toString();
                    txt_measure.setText("ml");

                    Log.e("POINTVVAL", "CALL" + v);
                    if (v.contains(".")) {
                        int d = (int) (Double.parseDouble(v) * 1000);
                        Log.e("POINTVVAL", "CALL" + d);
                        selectedmlVal = d;
                        txt_water.setText(String.valueOf(selectedmlVal));
                    } else {
                        int li = convertLitersToMillileters(selectedmlVal);
                        txt_water.setText(String.valueOf(li));
                    }
                } else {
                    Log.e("selectedGenderd", "call" + selectedMeasure);
                    double d = convertMilliletersTOLiters(selectedmlVal);
                    txt_water.setText(String.valueOf(d));
                    selectedliVal=d;
                    txt_measure.setText("li");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadButtonText(String measure) {
        if (measure=="Milliliter"){
            ml.setText("+ 250 ml");
            ml1.setText("+ 500 ml");
            ml2.setText("+ 1000 ml");
        }else {
            ml.setText("+ 0.25 li");
            ml1.setText("+ 0.5 li");
            ml2.setText("+ 1.0 li");
        }

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
