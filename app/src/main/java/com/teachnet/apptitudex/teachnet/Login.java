package com.teachnet.apptitudex.teachnet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class Login extends Activity {
    LoginButton fbloginbutton;
    TextView loginStatus;
    TextView userName;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        fbloginbutton = (LoginButton)findViewById(R.id.FB_login_button);
        loginStatus = (TextView)findViewById(R.id.loginStatus);
        userName = (TextView)findViewById(R.id.userName);
        callbackManager = CallbackManager.Factory.create();
        fbloginbutton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
          @Override
            public void onSuccess(LoginResult loginResult) {
                loginStatus.setText("Success \n" +
                loginResult.getAccessToken().getUserId() +
                "\n"+ loginResult.getAccessToken().getToken());
              userName.setText("name");
             // loginStatus.setText("Here \n" );
             //  Intent i = new Intent(Login.this, MainActivity.class);
              // userName = (TextView)findViewById(R.id.userName);
              // userName.setText(name);
              // startActivity(i);
             }
            @Override
            public void onCancel() {
                loginStatus.setText("Login Cancelled");
            }

            @Override
            public void onError(FacebookException error) { loginStatus.setText("Error"); }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//        super.onActivityResult(requestCode, resultCode, data);
//    }
    
}
