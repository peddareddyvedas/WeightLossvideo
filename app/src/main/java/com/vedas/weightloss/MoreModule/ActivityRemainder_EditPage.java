package com.vedas.weightloss.MoreModule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.vedas.weightloss.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Rise on 13/06/2018.
 */

public class ActivityRemainder_EditPage extends AppCompatActivity {

    ArrayList<String> remainderArray;
    String remainder = "Breakfast";
    TextView textday, texttime;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityremainder_editpage);
        ButterKnife.bind(this);
        init();
        loadkilogramSpinner();
    }

    private void init() {
        textday = (TextView) findViewById(R.id.textday);
        texttime = (TextView) findViewById(R.id.texttime);
        button = (Button) findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadkilogramSpinner() {
        remainderArray = new ArrayList<>();
        remainderArray.add("Breakfast");
        remainderArray.add("Exercise");
        remainderArray.add("Water");

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.remainderspinner);
        ArrayAdapter aa = new ArrayAdapter(this, R.layout.spinner_item, remainderArray);
        aa.setDropDownViewResource(R.layout.spinner_item);
        spin.setAdapter(aa);
        if (remainder != null) {
            int spinnerPosition = aa.getPosition(remainder);
            spin.setSelection(spinnerPosition);
        }
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                remainder = remainderArray.get(i);

                if (remainder == remainderArray.get(2)) {
                    textday.setText("Every 1 hour");
                    texttime.setText("");
                } else if (remainder == remainderArray.get(1)) {
                    textday.setText("Every Day");
                    texttime.setText("06:00Am");
                } else {
                    textday.setText("Every Day");
                    texttime.setText("08:30Am");
                }

                Log.e("selectedremainder", "call" + remainder);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
