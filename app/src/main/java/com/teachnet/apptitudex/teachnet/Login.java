package com.teachnet.apptitudex.teachnet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class Login extends Activity {
    LoginButton fbloginbutton;
    TextView loginStatus;
    TextView fbName;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        fbloginbutton = (LoginButton)findViewById(R.id.FB_login_button);
        loginStatus = (TextView)findViewById(R.id.loginStatus);
        callbackManager = CallbackManager.Factory.create();

        fbloginbutton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
          @Override
            public void onSuccess(LoginResult loginResult) {
                loginStatus.setText("Success \n" +
                loginResult.getAccessToken().getUserId() +
                "\n"+ loginResult.getAccessToken().getToken());
             // fbName = (TextView)findViewById(R.id.fbName);
                String userid =    loginResult.getAccessToken().getUserId();
              GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                          @Override
                          public void onCompleted(JSONObject object, GraphResponse response) {
                              displayUserInfo(object);
                          }
                      });

              Bundle parameters = new Bundle();
              parameters.putString("fields", "first_name, last_name"); //, email, id");

              graphRequest.setParameters(parameters);
              graphRequest.executeAsync();

             }
            @Override
            public void onCancel() {
                loginStatus.setText("Login Cancelled");
            }

            @Override
            public void onError(FacebookException error) { loginStatus.setText("Error"); }
        });

      }

    public  void  displayUserInfo(JSONObject object) {

               String firstName, lastName; //, email; //, email, id;
              try {
                  firstName = object.getString("first_name");
                  lastName = object.getString("last_name");
                // email = object.getString("email");
                // id = object.getString("id");
                  fbName = (TextView)findViewById(R.id.fbName);
                  fbName.setText(firstName + " " +  lastName); //+ " " + email + " " + id);



                  //setContentView(R.layout.activity_main);
               //   TextView userName;
                  //Intent i = new Intent(Login.this, MainActivity.class);
                  //Intent i = new Intent(Login.this, MainActivity.class);
                  //  startActivity(i);
                  //(R.id.userName)

              }
              catch (JSONException e){
                  fbName = (TextView)findViewById(R.id.fbName);
                  fbName.setText("Error");
                  e.printStackTrace();
              }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    
}

