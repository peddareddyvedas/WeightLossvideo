package com.vedas.weightloss.LoginModule;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.vedas.weightloss.Controllers.DayFoodController;
import com.vedas.weightloss.Controllers.NewsFeedController;
import com.vedas.weightloss.Controllers.PersonalInfoController;
import com.vedas.weightloss.Controllers.LoginModuleApisController;
import com.vedas.weightloss.DashBoardModule.DashBoardTabsActivity;
import com.vedas.weightloss.DataBase.PersonalInfoDataController;
import com.vedas.weightloss.DataBase.UserDataController;
import com.vedas.weightloss.R;
import com.vedas.weightloss.ServerApis.LocationTracker;
import com.vedas.weightloss.Settings.PersonalInfoActivity;

import butterknife.ButterKnife;

public class SplashScreenViewActivity extends AppCompatActivity {
    TextView title;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splachscreen);
        ButterKnife.bind(this);
        title = (TextView) findViewById(R.id.weightloss);

        handler = new Handler();

        LocationTracker.getInstance().fillContext(getApplicationContext());
        LocationTracker.getInstance().startLocation();
        LoginModuleApisController.getInstance().fillContext(getApplicationContext());
        PersonalInfoController.getInstance().fillContext(getApplicationContext());
        DayFoodController.getInstance().fillContext(getApplicationContext());
        NewsFeedController.getInstance().fillCOntext(getApplicationContext());

        UserDataController.getInstance().fillContext(getApplicationContext());
        UserDataController.getInstance().fetchUserData();
        if (UserDataController.getInstance().allUsers.size() > 0) {
            PersonalInfoDataController.getInstance().fetchPersonalInfoData();
        }
        checkIfUserHavingData();
    }

    private void checkIfUserHavingData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (UserDataController.getInstance().allUsers.size() > 0)
                {
                    if (PersonalInfoDataController.getInstance().personalInfoArray.size() > 0)
                    {
                        startActivity(new Intent(getApplicationContext(), DashBoardTabsActivity.class));
                    } else {
                        startActivity(new Intent(getApplicationContext(), PersonalInfoActivity.class));
                    }
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginViewActivity.class));
                }
            }

        }, 1000);

    }
}