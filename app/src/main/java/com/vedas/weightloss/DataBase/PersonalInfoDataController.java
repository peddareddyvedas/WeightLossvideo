package com.vedas.weightloss.DataBase;

import android.util.Log;

import com.vedas.weightloss.Models.PersonalInfoModel;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Created by dell on 20-09-2017.
 */
public class PersonalInfoDataController {
    public ArrayList<PersonalInfoModel> personalInfoArray;
    //public  Context context;
    public PersonalInfoModel currentMember;
    public static PersonalInfoDataController myObj;

    public static PersonalInfoDataController getInstance() {
        if (myObj == null) {
            myObj = new PersonalInfoDataController();
        }
        return myObj;
    }

    //insert the userdata into user table
    public void insertPersonalInfoData(PersonalInfoModel userdata) {
        try {
            UserDataController.getInstance().helper.getPersonalInfoDao().create(userdata);
            fetchPersonalInfoData();
            Log.e("fetc", "" + personalInfoArray);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Fetching all the user data
    public ArrayList<PersonalInfoModel> fetchPersonalInfoData() {
        personalInfoArray = null;
        personalInfoArray = new ArrayList<>();
            ArrayList<PersonalInfoModel> personalInfoModels = new ArrayList<PersonalInfoModel>(UserDataController.getInstance().currentUser.getPersonalInformationdata());
            if (personalInfoModels != null) {
                personalInfoArray = personalInfoModels;
                if (personalInfoArray.size()>0){
                    currentMember = personalInfoArray.get(0);
                    Log.e("currentMember","call"+currentMember.getRecommendedPlan());
                    Log.e("currentMember","call"+currentMember.getTargetDays());
                }
            }
            Log.e("fetching", "personaainfo data fectched successfully" + personalInfoArray.size());
        return personalInfoArray;
    }

    //Deleting all users in database
    public void deleteProfileData(ArrayList<PersonalInfoModel> user) {
        try {
           // UserDataController.getInstance().helper.getPersonalInfoDao().delete(user);
            UserDataController.getInstance().helper.getPersonalInfoDao().deleteBuilder().delete();
            personalInfoArray.removeAll(personalInfoArray);
            Log.e("delete", "personaainfo successfully" + personalInfoArray.size());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


