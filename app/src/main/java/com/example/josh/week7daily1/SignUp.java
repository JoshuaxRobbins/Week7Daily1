package com.example.josh.week7daily1;

import android.content.SharedPreferences;
import android.util.Log;


public class SignUp {

    String email;
    String password;
    Callback callback;
    SharedPreferences sharedPreferences;
    public static final String TAG = "_TAG";
    public SignUp(String email, String password, Callback callback, SharedPreferences sharedPreferences) {
        Log.d(TAG, "SignUp: " + password);
        this.email = email;
        this.password = password;
        this.callback = callback;
        this.sharedPreferences = sharedPreferences;
    }

    public void onSignup(){
        if(sharedPreferences.contains(email)){
            callback.onFailedSignUp("Email already in use.");
        }
        else if(PatternMatcher.testPassword(password)){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(email,password);
            editor.apply();
            callback.onSignedUp();
        }
        else{
            callback.onFailedSignUp("Password needs Uppercase,Lowercase,Number, and Special Character");
        }



    }

    public interface Callback{
        void onSignedUp();
        void onFailedSignUp(String error);
    }




}
