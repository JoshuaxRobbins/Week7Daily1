package com.example.josh.week7daily1;

import android.content.SharedPreferences;
import android.util.Log;

public class SignIn {
    String email;
    String password;
    SignIn.Callback callback;
    SharedPreferences sharedPreferences;
    public static final String TAG = "_TAG";

    public SignIn(String email, String password, SignIn.Callback callback, SharedPreferences sharedPreferences) {
        Log.d(TAG, "SignUp: " + password);
        this.email = email;
        this.password = password;
        this.callback = callback;
        this.sharedPreferences = sharedPreferences;
    }

    public void onSignIn(){
        if(!sharedPreferences.contains(email)){
            callback.onFailedSignIn("Email not registered.");
        }
        else if(sharedPreferences.getString(email,"").equals(password)){
            callback.onSignedIn();
        }
        else {
            Log.d(TAG, "onSignIn: " + sharedPreferences.getString(email,""));
            callback.onFailedSignIn("Incorrect Password.");
        }

    }




    public interface Callback{
        void onSignedIn();
        void onFailedSignIn(String error);
    }
}
