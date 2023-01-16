package com.example.EcomX.Activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.EcomX.Adapters.CustomAdapter;
import com.example.EcomX.Models.Product;
import com.example.EcomX.Models.ProductApiResponse;
import com.example.EcomX.Listeners.OnAddCartListener;
import com.example.EcomX.Listeners.OnFetchDataListener;
import com.example.EcomX.R;
import com.example.EcomX.Retrofit.RequestManager;
import com.example.EcomX.Listeners.SelectListener;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener, OnAddCartListener, View.OnClickListener{
    ArrayList<Product> ShoppingCartProducts = new  ArrayList<>();
    String usr_address,username,voice_string;
    RecyclerView recyclerView;
    CustomAdapter adapter;
    Context context;
    ProgressDialog dialog;
    Button btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_logout,gps,shopcartbtn,username_btn,orderbtn,voice_btn,barsrch_btn;
    SearchView searchView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    double lat,lon;

    ActivityResultLauncher<ScanOptions> barcodeScanner=registerForActivityResult(new ScanContract(), result ->
    {
        if(result.getContents() != null)
        {
            androidx.appcompat.app.AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("Result");
            alert.setMessage(result.getContents());
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    String barcode_txt = result.getContents();
                    Toast.makeText(MainActivity.this, barcode_txt, Toast.LENGTH_SHORT).show();
                    RequestManager manager = new RequestManager(MainActivity.this);
                    manager.getProducts(listener,null,null,null,barcode_txt);

                }
            }).show();
        }

    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching Products ..");
        dialog.show();

        btn_1 = findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        btn_2 = findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        btn_3 = findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        btn_4 = findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
        btn_logout =  findViewById(R.id.btn_22);
        btn_logout.setOnClickListener(this);
        shopcartbtn =  findViewById(R.id.btn_33);
        shopcartbtn.setOnClickListener(this);
        username_btn = findViewById(R.id.btn_11);
        orderbtn =  findViewById(R.id.btn_44);
        orderbtn.setOnClickListener(this);

        voice_btn =(Button) findViewById(R.id.btn_111);
        voice_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_search();
            }
        });
        barsrch_btn= findViewById(R.id.btn_112);
        barsrch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanBarcode();
            }
        });


        searchView = findViewById(R.id.search_view);


        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching news articles of"+query);
                dialog.show();
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getProducts(listener,null,null,query,null);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });




        RequestManager manager = new RequestManager(this);
        manager.getProducts(listener,null,null ,null ,null);

        //set username
        sharedPreferences=getSharedPreferences("remember file",MODE_PRIVATE);
        username=sharedPreferences.getString("username","username");
       if(username != null)
           username_btn.setText(username);


        // set shoppinglist
        Intent intent = getIntent();

        if (intent.hasExtra("BUNDLE")) {
            Bundle args = intent.getBundleExtra("BUNDLE");
            ShoppingCartProducts = (ArrayList<Product>) args.getSerializable("ARRAYLIST");
           if (intent.hasExtra("address") ){
               usr_address = intent.getStringExtra("address");
               sharedPreferences=getSharedPreferences("remember file",MODE_PRIVATE);
               editor=sharedPreferences.edit();
               editor.putString("address",usr_address);
               editor.apply();
            }


        }

    }


// create object from listner interface , must overrides all methods there
// here define onFetchData()  body
// inside the requestmanager class , call the function onFetchData()
    private final OnFetchDataListener<ProductApiResponse> listener = new OnFetchDataListener<ProductApiResponse>() {
        @Override
        public void onFetchData(@NonNull List<Product> list, String message) {
            if (list.isEmpty()){
                Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
            }
            else {
                showNews(list);
                dialog.dismiss();

            }

        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        }
    };




    private void showNews(List<Product> list) {

        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new CustomAdapter(this,list,this,this);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void OnNewsClick(Product headlines) {
       // startActivity(new Intent(MainActivity.this,DetailsActivity.class).putExtra("data", headlines) );
    }

    @Override
    public void onClick(View view) {

        Button button = (Button) view;
        String  btn_txt =button.getText().toString();
        //Toast.makeText(this, btn_txt, Toast.LENGTH_SHORT).show();
        if(btn_txt.equals("Logout")){
            // onlogout
            sharedPreferences=getSharedPreferences("remember file",MODE_PRIVATE);
            editor=sharedPreferences.edit();
            editor.putBoolean("login",false);
            editor.apply();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        else if(btn_txt.equals("Cart")){
            if(ShoppingCartProducts.size() > 0){
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)ShoppingCartProducts);
                intent.putExtra("BUNDLE",args);
                intent.putExtra("address",usr_address);
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this, "Looks like you ShoppingCart is Empty,\n You must add items first", Toast.LENGTH_SHORT).show();
            }


        }
        else if(btn_txt.equals("OrderHistory")){
            Intent intent = new Intent(MainActivity.this, OrderActivity.class);
            startActivity(intent);
        }

        else{
            dialog.setTitle("Fetching  Producs of  " +btn_txt);
            dialog.show();
            RequestManager manager = new RequestManager(this);
            manager.getProducts(listener,null,btn_txt,null,null);
        }


    }


    @Override
    public void addToCart(Product product) {
        product.setQuantity("1");
        int total = 0;
        int x = 0;
        for (Product oneprod : ShoppingCartProducts) {
            if (oneprod.getName().equals(product.getName())) {
                total = Integer.parseInt(oneprod.getQuantity());
                total += Integer.parseInt(product.getQuantity());
                oneprod.setQuantity(Integer.toString(total));
                x = 1;

            }
        }
        if (x != 1) {
            ShoppingCartProducts.add(product);
        }
        Toast.makeText(MainActivity.this, "Cart updated", Toast.LENGTH_SHORT).show();

    }


    private void voice_search() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        try {
            startActivityForResult(intent,113);
        }catch(ActivityNotFoundException e){
            Toast.makeText(MainActivity.this,"something is wrong",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        };




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if(resultCode==RESULT_OK && data!=null && requestCode == 113)
                {
                    ArrayList<String> text=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Toast.makeText(MainActivity.this,text.get(0),Toast.LENGTH_SHORT).show();
                    voice_string=text.get(0);
                    dialog.setTitle("Searching products of keyword : "+voice_string);
                    dialog.show();
                    RequestManager manager = new RequestManager(MainActivity.this);
                    manager.getProducts(listener,null,null,voice_string,null);
                }
    }


    private void scanBarcode() {
        ScanOptions options =new ScanOptions();
        options.setPrompt("Volume up to flash");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(ScannerBar.class);
        barcodeScanner.launch(options);

    }









}