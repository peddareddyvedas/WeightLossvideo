package com.vedas.weightloss.MoreModule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vedas.weightloss.R;

/**
 * Created by Rise on 12/06/2018.
 */

public class Activity_Goals extends AppCompatActivity {
    TextView startingkg, currentweight, goalweight, weeklygoal, activitylevel, caloriesperday;
    Button button;
    Toolbar toolbar;
    ImageView btn_back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
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
        toolbartext.append("Goals");
    }
    private void init() {
        startingkg = (TextView) findViewById(R.id.startingkg);

        currentweight = (TextView) findViewById(R.id.currentweight);

        goalweight = (TextView) findViewById(R.id.goalweight);

        weeklygoal = (TextView) findViewById(R.id.weeklygoal);

        activitylevel = (TextView) findViewById(R.id.activitylevel);

        caloriesperday = (TextView) findViewById(R.id.caloriesperday);


    }
}
