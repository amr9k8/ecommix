package com.example.EcomX.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.EcomX.Models.RegisterApiResponse;
import com.example.EcomX.Listeners.OnRegisterLoginListener;
import com.example.EcomX.R;
import com.example.EcomX.Retrofit.RequestManager;

import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {

    EditText username , email,password,jop;
  //  TextView haveaccount;
    Button signup_btn,birthdate_btn;
    Spinner gender;
    String date,date1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        birthdate_btn=findViewById(R.id.date_btn);
        username=findViewById(R.id.username_signup);
        email=findViewById(R.id.email_signup);
        password=findViewById(R.id.password_signup);

        jop=findViewById(R.id.jop_signup);
        gender=findViewById(R.id.gender);
        signup_btn=findViewById(R.id.btn_signup);
//        haveaccount=findViewById(R.id.hvaccount);
//        haveaccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//            }
//        });


        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        birthdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(java.util.Calendar.YEAR);
                int month= c.get(java.util.Calendar.MONTH);
                int day = c.get(java.util.Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog =new DatePickerDialog(SignupActivity.this, new DatePickerDialog.OnDateSetListener()
                {@Override public void onDateSet(DatePicker datePicker, int y, int m, int d){}}
                ,year,month,day);
                date1 = year+"-"+(month)+"-"+day;
                datePickerDialog.show();

            }
        });




    }



    private final OnRegisterLoginListener<RegisterApiResponse> listener = new OnRegisterLoginListener<RegisterApiResponse>() {
        @Override
        public void onRegisterLogin(String message) {
            if(message.equals("true")){
                Toast.makeText(SignupActivity.this, "Registerd Sucessfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }else
                Toast.makeText(SignupActivity.this, message, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(String error) {
            Toast.makeText(SignupActivity.this, error, Toast.LENGTH_LONG).show();
        }
    };


    protected void signUp() {
        String name = username.getText().toString().trim();
        String mail = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String gen = gender.getSelectedItem().toString();
        String birthdate =date1;
        String joptitle=jop.getText().toString();

        if (name.equals("") || mail.equals("") || pass.equals(""))
            Toast.makeText(this, "Some fields not entered", Toast.LENGTH_SHORT).show();
        else {
            RequestManager manager = new RequestManager(this);
            manager.registerUser(listener,name,mail ,pass,birthdate,joptitle,gen);
        }


    }









}