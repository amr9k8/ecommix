package com.example.EcomX.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.EcomX.Adapters.CustomCartItemAdapter;
import com.example.EcomX.Models.CartItem;
import com.example.EcomX.Models.CartPostApiRequest;
import com.example.EcomX.Models.Product;
import com.example.EcomX.Models.RegisterApiResponse;
import com.example.EcomX.Listeners.OnCartItemActionsListener;
import com.example.EcomX.Listeners.OnRegisterLoginListener;
import com.example.EcomX.R;
import com.example.EcomX.Retrofit.RequestManager;
import com.example.EcomX.Listeners.SelectListener;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mumayank.com.airlocationlibrary.AirLocation;

public class CartActivity extends AppCompatActivity implements SelectListener, OnCartItemActionsListener,AirLocation.Callback {
    Context context;
    RecyclerView recyclerView;
    CustomCartItemAdapter adapter;
    ArrayList<Product> productList;
    Button getaddress,confirmOrder;
    EditText address;
    Dialog dialog;
    TextView org_price,vat,totalcost;
    double totalprice,vatt,orginalprice;
    String username,usraddress="Write your Address Here ...";
    ArrayList<CartItem> items = new ArrayList<CartItem>();
    CartPostApiRequest cart_data = new CartPostApiRequest();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Intent intent = getIntent();

        if (intent.hasExtra("BUNDLE")) {
            Bundle args = intent.getBundleExtra("BUNDLE");
            productList = (ArrayList<Product>) args.getSerializable("ARRAYLIST");
            showCartItems(productList);
        }


        address = (EditText) findViewById(R.id.Adress);
        getaddress = (Button)findViewById(R.id.confirm_addr);
        getaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AirLocation airLocation=new AirLocation(CartActivity.this,CartActivity.this,true,0,"");
                airLocation.start();
            }
        });
        confirmOrder = (Button)findViewById(R.id.confirm_order);
        confirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO check username,useraddress,items

                for (Product oneprodd :productList) {
                    CartItem item = new CartItem();
                    item.setProd_amount(oneprodd.getQuantity());
                    item.setProd_id(oneprodd.getId());
                    items.add(item);
                }
                sharedPreferences=getSharedPreferences("remember file",MODE_PRIVATE);
                username=sharedPreferences.getString("username","username");
                cart_data.setItems(items);
                cart_data.setUser_name(username);
                if(usraddress==null || username== null ||cart_data.getItems().isEmpty()){
                    Toast.makeText(CartActivity.this, "address or name are missing ...", Toast.LENGTH_SHORT).show();
                }else {
                    cart_data.setAddress(usraddress);
                    RequestManager manager = new RequestManager(CartActivity.this);
                    manager.postOrderRequest(listener,cart_data);

                }




            }
        });

        if (intent.hasExtra("address")  ){
            usraddress = intent.getStringExtra("address");
            if (usraddress != null)
            {
                address.setText(usraddress);
                getaddress.setText("Update Address");
            }
        }
        org_price =(TextView) findViewById(R.id.order_price_org);
        vat =(TextView) findViewById(R.id.vat_cost);
        totalcost = (TextView) findViewById(R.id.total_cost);
        calcPrice(productList);





    }



    private void showCartItems(ArrayList<Product> list) {
       // Toast.makeText(this, list.get(0).getName(), Toast.LENGTH_SHORT).show();
        recyclerView = findViewById(R.id.recycler_main2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new CustomCartItemAdapter(this,list,this,this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onRemove(int itemstotal,int productPosition) {
        productList.remove(productPosition);
        if (productList.isEmpty()){
            Toast.makeText(CartActivity.this, "Shopping Cart is Empty Now", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CartActivity.this,MainActivity.class);
            startActivity(intent);
        }else{
        showCartItems(productList);
        calcPrice(productList);

        }

    }

    @Override
    public void onInc(int productPosition) {

    }

    @Override
    public void onDec(int productPosition) {

    }

    @Override
    public void onUpdate(int itemsTotal,int ProductArrindex) {
        calcPrice(productList);
    }


    @Override
    public void OnNewsClick(Product headlines) {

    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if(keyCode==KeyEvent.KEYCODE_BACK)   {
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            if(!productList.isEmpty() ){
                Toast.makeText(CartActivity.this, "here1", Toast.LENGTH_SHORT).show();
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)productList);
                intent.putExtra("BUNDLE",args);
                if(usraddress != null)
                intent.putExtra("address",usraddress);
                startActivity(intent);
            }


        }
        return true;
    }


    @Override
    public void onFailure(@NonNull AirLocation.LocationFailedEnum locationFailedEnum) {
        Toast.makeText(this, "failed to get location", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(@NonNull ArrayList<Location> arrayList) {
        double lat =arrayList.get(0).getLatitude();
       double lon = arrayList.get(0).getLongitude();
        // locText.setText("latitude= "+lat+"\nlongitude= "+lon);
        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> addressList = geocoder.getFromLocation(lat, lon, 1);
            usraddress= addressList.get(0).getAddressLine(0);
            Toast.makeText(this, "location detected successfully :\n "+usraddress, Toast.LENGTH_SHORT).show();
            address.setText(usraddress);
            Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+lat+","+lon));
            startActivity(in);

            // locText.append("\n"+line);
        } catch (IOException e) {
            Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
        }
    }




    public  void calcPrice(ArrayList<Product> list){
        double x =0;
        double items_Number;
        for (Product oneprod : list) {
            items_Number = Double.parseDouble(oneprod.getQuantity());
            x+= (   items_Number *  Double.parseDouble(oneprod.getPrice())  );
        }
        orginalprice = x;
        vatt = 0.1 * orginalprice;
        totalprice = vatt + orginalprice;
        Toast.makeText(this, Double.toString(x), Toast.LENGTH_SHORT).show();
        org_price.setText(String.format("%.2f L.E", orginalprice));
        vat.setText(String.format("%.2f L.E", vatt));
        totalcost.setText(String.format("%.2f L.E", totalprice));

    }

    private final OnRegisterLoginListener<RegisterApiResponse> listener = new OnRegisterLoginListener<RegisterApiResponse>() {
        @Override
        public void onRegisterLogin(String message) {
            Toast.makeText(CartActivity.this, message, Toast.LENGTH_LONG).show();
           if ( message.equals("true") ){
               Intent intent = new Intent(CartActivity.this, MainActivity.class);
               if(!productList.isEmpty() ){
                   productList.clear();
                   Bundle args = new Bundle();
                   args.putSerializable("ARRAYLIST",(Serializable)productList);
                   intent.putExtra("BUNDLE",args);
                   if(usraddress != null)
                       intent.putExtra("address",usraddress);
                   startActivity(intent);
               }

               Toast.makeText(CartActivity.this, "Order Added Successfulyy",Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(CartActivity.this, "failed", Toast.LENGTH_SHORT).show();





        }

        @Override
        public void onError(String error) {
            Toast.makeText(CartActivity.this, error, Toast.LENGTH_SHORT).show();
        }
    };






}