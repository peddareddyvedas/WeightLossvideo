package com.vedas.weightloss.MoreModule;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vedas.weightloss.Controllers.PersonalInfoController;
import com.vedas.weightloss.DataBase.PersonalInfoDataController;
import com.vedas.weightloss.DataBase.UserDataController;
import com.vedas.weightloss.Models.PersonalInfoModel;
import com.vedas.weightloss.R;
import com.vedas.weightloss.Transition.BaseDetailActivity;

import java.io.ByteArrayInputStream;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by VEDAS on 6/12/2018.
 */

public class ProfilePageActivity extends BaseDetailActivity {
    TextView gender, age, dob, name, height, weight, bmi, email;
    CircleImageView circleImageView;
    ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        loadIds();
        setCurrentUserData();

    }

    private void loadIds() {
        circleImageView = (CircleImageView) findViewById(R.id.project_image);
        gender = (TextView) findViewById(R.id.editgender);
        age = (TextView) findViewById(R.id.ageedit);
        dob = (TextView) findViewById(R.id.editdob);
        name = (TextView) findViewById(R.id.name);
        height = (TextView) findViewById(R.id.editheight);
        weight = (TextView) findViewById(R.id.editweight);
        bmi = (TextView) findViewById(R.id.editbmi);
        email = (TextView) findViewById(R.id.editemail);
        back = (ImageView) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        finish();
                                    }
                                }
        );

    }

    private void setCurrentUserData() {
        if (PersonalInfoDataController.getInstance().personalInfoArray.size() > 0) {
            PersonalInfoModel objPersonalInfo = PersonalInfoDataController.getInstance().currentMember;
            name.setText("" + UserDataController.getInstance().currentUser.getEmail());
            gender.setText(objPersonalInfo.getGender());
            dob.setText(objPersonalInfo.getBirthday());
            gender.setText(objPersonalInfo.getGender());
            height.setText(objPersonalInfo.getHeight());
            weight.setText(objPersonalInfo.getWeight());
            bmi.setText(objPersonalInfo.getBmi());
            email.setText(UserDataController.getInstance().currentUser.getEmail());
            if (objPersonalInfo.getBirthday() != null) {
                String selectedDate[] = objPersonalInfo.getBirthday().split("/");
                //convert them to int
                int mDay = Integer.valueOf(selectedDate[0]);
                int mMonth = Integer.valueOf(selectedDate[1]);
                int mYear = Integer.valueOf(selectedDate[2]);
                String mage = PersonalInfoController.getInstance().calculatingAge(mYear, mMonth, mDay);
                age.setText(mage);
            }
            if (objPersonalInfo.getMprofilepicturepath() != null) {
                circleImageView.setImageBitmap(convertByteArrayTOBitmap(objPersonalInfo.getMprofilepicturepath()));

            }
        }
    }

    public Bitmap convertByteArrayTOBitmap(byte[] profilePic) {
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(profilePic);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
        return bitmap;
    }
}
