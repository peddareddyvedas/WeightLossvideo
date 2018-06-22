package com.vedas.weightloss.LoginModule;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vedas.weightloss.Alert.AlertShowingDialog;
import com.vedas.weightloss.Alert.RefreshShowingDialog;
import com.vedas.weightloss.Controllers.PersonalInfoController;
import com.vedas.weightloss.Controllers.LoginModuleApisController;
import com.vedas.weightloss.R;
import com.vedas.weightloss.Transition.BaseDetailActivity;

import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
public class LoginViewActivity extends BaseDetailActivity {
    TextView title;
    RelativeLayout rl_main;
    public static RefreshShowingDialog refreshShowingDialog;
    EditText userNameTextField, textInputPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
        ButterKnife.bind(this);
        rl_main=(RelativeLayout)findViewById(R.id.rl_main);
        title=(TextView)findViewById(R.id.weightloss) ;
        refreshShowingDialog = new RefreshShowingDialog(LoginViewActivity.this);
        userNameTextField = (EditText) findViewById(R.id.edittext1);
        textInputPassword = (EditText) findViewById(R.id.edittext2);
    }
    @OnCheckedChanged(R.id.chk)
    public void onChecked(boolean checked) {
        if (checked) {
            textInputPassword.setTransformationMethod(null);
        } else {
            textInputPassword.setTransformationMethod(new PasswordTransformationMethod());
        }
        // cursor reset his position so we need set position to the end of text
        textInputPassword.setSelection(textInputPassword.getText().length());
    }
    @OnClick(R.id.button)
    public void buttonAction(){
        mLogin();
    }

    @OnClick({R.id.signup})
     void signupAction(){
        //finish();
       startActivity(new Intent(getApplicationContext(),RegisterViewActivity.class));
    }
    @OnClick({R.id.forgot})
    void forgotAction(){
        moveToForgetPasswordPage(userNameTextField.getText().toString());
    }
    private void moveToForgetPasswordPage(String emailInfo) {

        Intent intent = new Intent(LoginViewActivity.this, ForgotViewActivity.class);
        intent.putExtra("email", emailInfo);
        startActivity(intent);

    }
    //////Validations for loigin////
    public void mLogin() {
        String st_emailandphone, st_password;
        st_emailandphone = userNameTextField.getText().toString();
        st_password = textInputPassword.getText().toString();

        if (st_emailandphone.length() > 0)
        {
            if (st_password.length() > 0)
            {
                if (isConn()) {
                    PersonalInfoController.getInstance().fillContext(getApplicationContext());
                    /*Intent i = new Intent(LoginViewActivity.this, PersonalInfoActivity.class);
                    transitionTo(i);*/
                    refreshShowingDialog.showAlert();
                    LoginModuleApisController.getInstance().loginApiExecution(st_emailandphone,st_password,this);

                } else {
                    new AlertShowingDialog(LoginViewActivity.this,"No Internet Connection");
                }
            } else {
                new AlertShowingDialog(LoginViewActivity.this,"Please enter your password");
            }
        } else {
            new AlertShowingDialog(LoginViewActivity.this,"Please enter your email");
        }
    }
    public boolean isConn() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity.getActiveNetworkInfo() != null) {
            if (connectivity.getActiveNetworkInfo().isConnected())
                return true;
        }
        return false;
    }
    @Override
    public void onBackPressed() {    //when click on phone backbutton
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}