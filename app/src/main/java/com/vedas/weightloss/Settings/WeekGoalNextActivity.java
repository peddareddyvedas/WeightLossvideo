package com.vedas.weightloss.Settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vedas.weightloss.Alert.RefreshShowingDialog;
import com.vedas.weightloss.Controllers.PersonalInfoController;
import com.vedas.weightloss.DataBase.PersonalInfoDataController;
import com.vedas.weightloss.DataBase.UserDataController;
import com.vedas.weightloss.Models.PersonalInfoModel;
import com.vedas.weightloss.R;
import com.vedas.weightloss.Transition.BaseDetailActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by VEDAS on 5/11/2018.
 */

public class WeekGoalNextActivity extends BaseDetailActivity {
    int type;
    //public static String goalCalories;
    TextView goal_calories, txt_bmr, txt_calories;
    String str_bmr;
    public static RefreshShowingDialog refreshShowingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekgoalnext);
        ButterKnife.bind(this);
        setupToolbar();
        setupWindowAnimations();
        goal_calories = (TextView) findViewById(R.id.goaltext);
        txt_bmr = (TextView) findViewById(R.id.textmsg1);
        txt_calories = (TextView) findViewById(R.id.textmsg2);
        refreshShowingDialog=new RefreshShowingDialog(WeekGoalNextActivity.this);
        setUserData();
    }
    public void onResume() {
        super.onResume();
      //  checkIsUserHavingData();
    }

    @OnClick({R.id.starttrack})//    @OnClick({R.id.forgot})
    public void trackAction() {
        Log.e("chandu", "call");
        PersonalInfoController.getInstance().selectedPersonalInfoModel.setUser(UserDataController.getInstance().currentUser);
        refreshShowingDialog.showAlert();
        PersonalInfoController.getInstance().personalInfoApiExecution(PersonalInfoController.getInstance().selectedPersonalInfoModel, this);
    }
    /*private void checkIsUserHavingData() {
        if (PersonalInfoDataController.getInstance().personalInfoArray.size() > 0) {
            PersonalInfoDataController.getInstance().fetchPersonalInfoData();
            Log.e("selectedmodel", "call" + PersonalInfoDataController.getInstance().personalInfoArray);
            PersonalInfoModel objPersonalInfoModel = PersonalInfoDataController.getInstance().currentMember;
            goal_calories.setText(objPersonalInfoModel.getTargetCalories());
            txt_bmr.setText("You can take daily " + objPersonalInfoModel.getBmr() + " calories to maintain your weight. ");
            txt_calories.setText("You need to take  daily " + objPersonalInfoModel.getTargetCalories() + " calories to reduce your target weight.");
        }
    }*/
    private void setUserData(){
        if (PersonalInfoController.getInstance().retrivePersonalDataFromUserDefaults()){
            Log.e("selectedmodel","call");
            if (PersonalInfoController.getInstance().selectedPersonalInfoModel.getTargetCalories() !=null )
            {
                    goal_calories.setText(PersonalInfoController.getInstance().selectedPersonalInfoModel.getTargetCalories());
                    txt_bmr.setText("You can take daily " + PersonalInfoController.getInstance().selectedPersonalInfoModel.getBmr() + " calories to maintain your weight. ");
                    txt_calories.setText("You need to take  daily " + PersonalInfoController.getInstance().selectedPersonalInfoModel.getTargetCalories() + " calories to reduce your target weight.");
            }
        }
    }

    void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        TextView textView = (TextView) toolbar.findViewById(R.id.title);
        textView.setText("Weekly Goal");
        Button btn_back = (Button) toolbar.findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.centerImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent i = new Intent(ActivityLevelActivity.this, PersonalInfoNextActivity.class);
                i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
                transitionTo(i);*/
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

    /*private Transition buildEnterTransition() {
        Slide enterTransition = new Slide();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        enterTransition.setSlideEdge(Gravity.LEFT);
        return enterTransition;
    }*/
    private Transition buildEnterTransition() {
        Explode enterTransition = new Explode();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        return enterTransition;
    }

    @Override
    public void onBackPressed() {    //when click on phone backbutton
        finish();
    }

}
