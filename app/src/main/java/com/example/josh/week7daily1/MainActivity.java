package com.example.josh.week7daily1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SignUp.Callback,SignIn.Callback{
    public static final String TAG = "_TAG";
    private EditText etPassword;
    private EditText etEmail;
    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvError = findViewById(R.id.tvError);
    }


    public void onLogin(View view) {
        Log.d(TAG, "onSignIn: ");
        SignIn signIn = new SignIn(etEmail.getText().toString(),etPassword.getText().toString(),this,this.getPreferences(Context.MODE_PRIVATE));
        signIn.onSignIn();
    }

    public void onSignUp(View view) {
        Log.d(TAG, "onSignUp: ");
        SignUp signUp = new SignUp(etEmail.getText().toString(),etPassword.getText().toString(),this,this.getPreferences(Context.MODE_PRIVATE));
        signUp.onSignup();
    }


    @Override
    public void onSignedUp() {
        Log.d(TAG, "onSignedUp: ");
        Toast.makeText(this,"Sign Up Successful, Please Log In",Toast.LENGTH_LONG).show();
        tvError.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFailedSignUp(String error) {
        Log.d(TAG, "onFailedSignUp: ");
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(error);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this,"Logged out for inactivity.",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignedIn() {
        Log.d(TAG, "onSignedIn: ");
        tvError.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);

    }

    @Override
    public void onFailedSignIn(String error) {
        Log.d(TAG, "onFailedSignIn: ");
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(error);
    }
}
