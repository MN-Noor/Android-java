package com.example.authentication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
EditText email,pass;
private static final String MY_PREFS="myPrefs";
private static final String emkey="email";
private static final String psskey="pass";

Button signin;
String semail,spass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signin=findViewById(R.id.button);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getlogininfo();
            }
        });



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                semail=email.getText().toString();
                spass=pass.getText().toString();
                createAlert(semail,spass);




            }
        });

    }

    private void saveLoginInfo(String em,String ps){
        SharedPreferences sharedPreferences=getSharedPreferences(MY_PREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("email",em);
        editor.putString("pass",ps);
        editor.apply();
        String email=sharedPreferences.getString("email","email");
        Toast.makeText(getApplicationContext(),email,Toast.LENGTH_SHORT).show();


    }
    private void getlogininfo()
    {
        SharedPreferences sharedPreferences=getSharedPreferences(MY_PREFS,Context.MODE_PRIVATE);
        email.setText(sharedPreferences.getString("email","email"));
        pass.setText(sharedPreferences.getString("pass","password"));

    }


    private void createAlert(String em,String ps)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Remember Me").setMessage("Save information for later use").
                setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveLoginInfo(em,ps);
                        dialog.dismiss();
                    }
                }).setNegativeButton("Later", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

}