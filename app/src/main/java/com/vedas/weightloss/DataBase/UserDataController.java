package com.vedas.weightloss.DataBase;

import android.content.Context;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.vedas.weightloss.Models.User;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by dell on 20-09-2017.
 */

public class UserDataController extends OrmLiteBaseActivity {
    public DataBaseHelper helper;
    public ArrayList<User> allUsers = new ArrayList<>();
    public  User currentUser;
    //public  Context context;
    public static UserDataController myObj;

    public static  UserDataController getInstance() {
        if (myObj == null) {
            myObj = new UserDataController();
        }

        return myObj;
    }
    public void  fillContext(Context context1)
    {

        Log.e("DBStatus","Fill Context Called");
        helper = new DataBaseHelper(context1);
    }
    //insert the userdata into user table
    public void insertUserData(User userdata) {
        try {
            helper.getUserDao().create(userdata);
            fetchUserData();
            Log.e("fetc", ""+allUsers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Fetching all the user data
    public ArrayList<User> fetchUserData() {
        allUsers = null;
        allUsers = new ArrayList<>();

        try {
            allUsers = (ArrayList<User>) helper.getUserDao().queryForAll();
            if(allUsers.size()>0){
                currentUser = allUsers.get(0);
                Log.e("currentUser","call"+currentUser.getEmail());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.e("fetching", "user data fectched successfully"+allUsers.size());

        return allUsers;
    }

    //updating the userdata
    public void updateUserData( User user) {
        try {
            UpdateBuilder<User, Integer> updateBuilder = helper.getUserDao().updateBuilder();
            updateBuilder.updateColumnValue("password", user.getPassword());
            updateBuilder.updateColumnValue("name", user.getName());
            updateBuilder.updateColumnValue("isVerified", user.getVerified());
            updateBuilder.updateColumnValue("registerTime", user.getRegisterTime());
            updateBuilder.updateColumnValue("latitude", user.getLatitude());
            updateBuilder.updateColumnValue("longitude", user.getLongitude());
            updateBuilder.where().eq("userName", user.getEmail());
            updateBuilder.update();
            Log.e("update data", "updated the data sucessfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Deleting all users in database
    public void deleteUserData(ArrayList<User> user) {
        try {
            helper.getUserDao().delete(user);
            Log.e("deleteuserdata", "sucessfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


