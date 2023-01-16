package com.example.EcomX.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.EcomX.Adapters.CustomOrderItemAdapter;
import com.example.EcomX.Models.OrderApiResponse;
import com.example.EcomX.Models.OrderInfo;
import com.example.EcomX.Listeners.OnFetchOrdersListener;
import com.example.EcomX.R;
import com.example.EcomX.Retrofit.RequestManager;

import java.io.Serializable;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    Dialog dialog;
    RecyclerView recyclerView;
    CustomOrderItemAdapter adapter;
    Button graph_btn,viewsdetails_btn;
    List<OrderInfo> orderslist;
    String username;
    OrderApiResponse orderresponse;
    SharedPreferences sharedPreferences;
//
//    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//        alertDialog.setTitle("ORDER ID :11\n");
//        alertDialog.setMessage("product : milk\nprice : 32\nquantitiy:3\n_____________________\nproduct : milk\nprice : 32\nquantitiy:3\n_____________________\nproduct : milk\nprice : 32\nquantitiy:3\n_____________________\nproduct : milk\nprice : 32\nquantitiy:3\n_____________________\nproduct : milk\nprice : 32\nquantitiy:3\n_____________________\nproduct : milk\nprice : 32\nquantitiy:3\n_____________________\n");
//        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//            new DialogInterface.OnClickListener() {
//        public void onClick(DialogInterface dialog, int which) {
//            dialog.dismiss();
//        }
//    });
//        alertDialog.show();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching Orders");
        dialog.show();
        sharedPreferences=getSharedPreferences("remember file",MODE_PRIVATE);
        username=sharedPreferences.getString("username","tester1");

        RequestManager manager = new RequestManager(this);
        manager.getCustomerOrders(listener,username);

        graph_btn = findViewById(R.id.order_stat);
        graph_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(OrderActivity.this, GraphActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("OrderApiResponse",(Serializable)orderresponse);
                intent.putExtra("BUNDLE",args);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

    }






    private void showOrders(List<OrderInfo> list) {

        recyclerView = findViewById(R.id.recycler_main3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new CustomOrderItemAdapter(this,list);
        recyclerView.setAdapter(adapter);

    }



    private final OnFetchOrdersListener<OrderApiResponse> listener = new OnFetchOrdersListener<OrderApiResponse>() {

        @Override
        public void onFetchOrdersData(OrderApiResponse ordersResponse, String message) {
            if (!ordersResponse.getStatues().equals("true")){
                Toast.makeText(OrderActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
            }
            else {
                orderresponse = ordersResponse;
                showOrders(ordersResponse.getOrdersinfo_list());
                dialog.dismiss();
            }
        }

        @Override
        public void onError(String message) {
            Toast.makeText(OrderActivity.this, message, Toast.LENGTH_LONG).show();
        }
    };








}