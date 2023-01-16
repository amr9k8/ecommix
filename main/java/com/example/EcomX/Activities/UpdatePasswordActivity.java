package com.example.EcomX.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.EcomX.Listeners.OnRegisterLoginListener;
import com.example.EcomX.Models.RegisterApiResponse;
import com.example.EcomX.R;
import com.example.EcomX.Retrofit.RequestManager;

public class UpdatePasswordActivity extends AppCompatActivity {
    EditText newpass;
    EditText resetcode;
    Button updatepass;
    String password;
    String code;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            username = extras.getString("username");



        newpass=findViewById(R.id.newpass);
        resetcode=findViewById(R.id.resetcode);
        updatepass =findViewById(R.id.changepass);
        updatepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                password = newpass.getText().toString();
                code = resetcode.getText().toString();
                if(password.equals("") ||code.equals("")  ){
                    Toast.makeText(UpdatePasswordActivity.this, "Enter Username Please", Toast.LENGTH_LONG).show();
                }else{
                    RequestManager manager = new RequestManager(UpdatePasswordActivity.this);
                    manager.updateUserPass(listener,username.toLowerCase(),code,password);
                }

            }
    });






    }



    private final OnRegisterLoginListener<RegisterApiResponse> listener = new OnRegisterLoginListener<RegisterApiResponse>() {
        @Override
        public void onRegisterLogin(String message) {
            if ( message.equals("true") ){
                Toast.makeText(UpdatePasswordActivity.this, "Password Changed Successfulyy", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdatePasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(UpdatePasswordActivity.this, "ResetCode Didn't Match,try again later .. ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdatePasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }



        }

        @Override
        public void onError(String error) {
            Toast.makeText(UpdatePasswordActivity.this, error, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UpdatePasswordActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };










}