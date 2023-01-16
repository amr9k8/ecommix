package com.example.EcomX.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.EcomX.Models.RegisterApiResponse;
import com.example.EcomX.Listeners.OnRegisterLoginListener;
import com.example.EcomX.R;
import com.example.EcomX.Retrofit.RequestManager;

public class ResetCodeActivity extends AppCompatActivity {
    EditText username;
    Button getcode;
    String name;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_code);

        dialog = new ProgressDialog(ResetCodeActivity.this);
        dialog.setTitle("Checking if Account Exist , Please Wait ..");
        username=findViewById(R.id.mail_recovery_pass);
        getcode=findViewById(R.id.load_pass);

        getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                name = username.getText().toString();
                if(name.equals("")){
                    Toast.makeText(ResetCodeActivity.this, "Enter Username Please", Toast.LENGTH_LONG).show();
                }else{
                    RequestManager manager = new RequestManager(ResetCodeActivity.this);
                    manager.forgetUserPass(listener,name.toLowerCase());
                }

            }
        });


    }



    private final OnRegisterLoginListener<RegisterApiResponse> listener = new OnRegisterLoginListener<RegisterApiResponse>() {
        @Override
        public void onRegisterLogin(String message) {
            if (    (!message.equals("Email sending failed!") ) && (!message.equals("This User is not registerd") )  ){
                dialog.dismiss();
                Toast.makeText(ResetCodeActivity.this, message, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ResetCodeActivity.this, UpdatePasswordActivity.class);
                intent.putExtra("username",name);
                startActivity(intent);
                finish();
            }
            else{
                dialog.dismiss();
                Toast.makeText(ResetCodeActivity.this, message, Toast.LENGTH_LONG).show();
            }



        }

        @Override
        public void onError(String error) {
            Toast.makeText(ResetCodeActivity.this, error, Toast.LENGTH_SHORT).show();
        }
    };





}