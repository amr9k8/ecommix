package com.example.EcomX.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.EcomX.Models.RegisterApiResponse;
import com.example.EcomX.Listeners.OnRegisterLoginListener;
import com.example.EcomX.R;
import com.example.EcomX.Retrofit.RequestManager;

public class LoginActivity extends AppCompatActivity {
    EditText username , password;
    Button login_btn;
    TextView forget_pass,signup;

    CheckBox rememberme;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    boolean login;

    String name ;
    String pass ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);




        username=findViewById(R.id.username_login);
        password=findViewById(R.id.password_login);
        login_btn=findViewById(R.id.btn_login);
        forget_pass=findViewById(R.id.forget_password);
        signup=findViewById(R.id.go_sign_up_btn);

        //remember me
        rememberme=findViewById(R.id.remember);

        sharedPreferences=getSharedPreferences("remember file",MODE_PRIVATE);
        login= sharedPreferences.getBoolean("login",false);

         name = username.getText().toString();
         pass = password.getText().toString();

        if(login){
            SharedPreferences prefs = getSharedPreferences("remember file", MODE_PRIVATE);
            String  name1 = prefs.getString("username","null");
            String  pass1 =  prefs.getString("password","null");
           // Toast.makeText(LoginActivity.this,name1+" "+pass1 , Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);

            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetCodeActivity.class);
                startActivity(intent);
            }
        });





    }




    private final OnRegisterLoginListener<RegisterApiResponse> listener = new OnRegisterLoginListener<RegisterApiResponse>() {
        @Override
        public void onRegisterLogin(String message) {
            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
            if ( message.equals("true") ){
              boolean save_session = false;
                if (rememberme.isChecked())
                    save_session=true;
                    keepLogin(name, pass,save_session);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else
            Toast.makeText(LoginActivity.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();





        }

        @Override
        public void onError(String error) {
            Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
        }
    };

    protected void login() {
        name = username.getText().toString();
        pass = password.getText().toString();

        if (name.equals("") || pass.equals(""))
            Toast.makeText(this, "Please Enter username and password", Toast.LENGTH_SHORT).show();

        else {
            RequestManager manager = new RequestManager(this);
            manager.loginUser(listener,name.toLowerCase(),pass.toLowerCase());
            }

    }


    protected void keepLogin(String username , String pass,boolean save_session){
        editor=sharedPreferences.edit();
        editor.putString("username",username) ;
        editor.putString("password",pass);
        if(save_session)
        editor.putBoolean("login",true);
        else
            editor.putBoolean("login",false);
        editor.apply();
    }











}