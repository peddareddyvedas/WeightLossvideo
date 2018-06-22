package com.vedas.weightloss.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.vedas.weightloss.Models.PersonalInfoModel;
import com.vedas.weightloss.Models.User;

import java.sql.SQLException;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application --
    private static final String DATABASE_NAME = "weightloss.db";

    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the SimpleData table
    private Dao<User, Integer> userDao = null;
    private Dao<PersonalInfoModel, Integer> personalInfoDao = null;


    ConnectionSource objConnectionSource;

    public DataBaseHelper(Context contex) {
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        Log.e("DBStatus", "OnCreate" + connectionSource);

        try {
            //creating the user table
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, PersonalInfoModel.class);
            //
            userDao = DaoManager.createDao(connectionSource, User.class);
            personalInfoDao = DaoManager.createDao(connectionSource, PersonalInfoModel.class);

            Log.e("userDao", "user table is created");
            objConnectionSource = connectionSource;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Log.e("DBStatus", "OnUpgrade" + connectionSource);
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, PersonalInfoModel.class, true);
            onCreate(database, connectionSource);
            objConnectionSource = connectionSource;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //user DAO
    public Dao<User, Integer> getUserDao() {
        if (userDao == null) {
            try {
                //  userDao = DaoManager.createDao(objConnectionSource,User.class);
                userDao = getDao(User.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userDao;
    }

    //personal info DAO
    public Dao<PersonalInfoModel, Integer> getPersonalInfoDao() {
        if (personalInfoDao == null) {
            try {
                personalInfoDao = getDao(PersonalInfoModel.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personalInfoDao;
    }
}
