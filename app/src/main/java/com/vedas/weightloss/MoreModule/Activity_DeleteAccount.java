package com.vedas.weightloss.MoreModule;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vedas.weightloss.Alert.AlertShowingDialog;
import com.vedas.weightloss.Alert.RefreshShowingDialog;
import com.vedas.weightloss.DataBase.UserDataController;
import com.vedas.weightloss.LoginModule.LoginViewActivity;
import com.vedas.weightloss.R;
import com.vedas.weightloss.ServerApis.ServerApisInterface;
import com.vedas.weightloss.ServerObjects.UserServerObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Rise on 13/06/2018.
 */

public class Activity_DeleteAccount extends AppCompatActivity {
    TextView text, text1, text2;
    Button delete, back;
    EditText password;
    String st_password;
    Toolbar toolbar;
    ImageView btn_back;
    RefreshShowingDialog refreshShowingDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletaccount);
        ButterKnife.bind(this);
        setToolbar();
        init();
        refreshShowingDialog = new RefreshShowingDialog(Activity_DeleteAccount.this);

        UserDataController.getInstance().fetchUserData();

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
        toolbartext.append("Delete Account");
    }

    private void init() {
        text = (TextView) findViewById(R.id.text);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);

      /*  text.setText(Html.fromHtml("<h2><br><p> I Understand that this was permantly delete my Weightloss account,that my information could not be recover,and that this action could't be undone</p>"));
        text1.setText(Html.fromHtml("<h2><br><p> I understand that this was permenently lose access to allof data associated with my profile including food.entries,workout, weight entries and  news feed.  </p>"));
        text2.setText(Html.fromHtml("<h2><br><p> I Understand that this was prementely  delet my Weightloss account,my unused  giftcard can't be recovered. </p>"));
    */
        password = (EditText) findViewById(R.id.password);

        delete = (Button) findViewById(R.id.deleteaccount);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAction();

            }
        });

    }

    public void deleteAction() {

        st_password = password.getText().toString();

        if (st_password.length() > 0) {

            if (isValidPasword(st_password)) {

                if (isConn()) {


                    deletApiExecution();
                } else {
                    new AlertShowingDialog(Activity_DeleteAccount.this, " No Internet Connection");

                }
            } else {


                new AlertShowingDialog(Activity_DeleteAccount.this, "Password must contain at least 1 number, 1 letter, 1 special characters, and minimum of 8 characters in length without space.");
            }

        } else {
            new AlertShowingDialog(Activity_DeleteAccount.this, "Please enter your password");

        }
    }

    // Validate password
    private boolean isValidPasword(String password) {
        boolean isValid = false;

        String expression = "^(?=.*[a-z])(?=.*[$@$#!%*?&])[A-Za-z\\d$@$#!%*?&]{8,}";
        CharSequence inputStr = password;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            System.out.println("if");
            isValid = true;
        } else {
            System.out.println("else");
        }
        return isValid;
    }

    public boolean isConn() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity.getActiveNetworkInfo() != null) {
            if (connectivity.getActiveNetworkInfo().isConnected())
                return true;
        }
        return false;
    }

    //DeleteAps////

    private void deletApiExecution() {

        final UserServerObject requestBody = new UserServerObject();


        requestBody.mailid = UserDataController.getInstance().currentUser.getEmail();
        requestBody.password = st_password.trim();


        Log.e("mailid", "" + requestBody.mailid);
        Log.e("password", "" + requestBody.password);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerApisInterface.home_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServerApisInterface api = retrofit.create(ServerApisInterface.class);
        Call<UserServerObject> callable = api.delete(requestBody);
        callable.enqueue(new Callback<UserServerObject>() {
            @Override
            public void onResponse(Call<UserServerObject> call, retrofit2.Response<UserServerObject> response) {
                refreshShowingDialog.hideRefreshDialog();
                String statuscode = response.body().result;
                String message = response.body().message;

                Log.e("codefor3", "call" + statuscode);
                Log.e("message", "call" + message);


                if (!statuscode.equals(null)) {
                    if (statuscode.equals("success")) {

                        startActivity(new Intent(getApplicationContext(), LoginViewActivity.class));

                    } else {
                        new AlertShowingDialog(Activity_DeleteAccount.this, message);

                    }
                }
            }

            @Override
            public void onFailure(Call<UserServerObject> call, Throwable t) {
                refreshShowingDialog.hideRefreshDialog();
            }
        });
    }
}
