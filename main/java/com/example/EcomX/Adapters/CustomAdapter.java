package com.example.EcomX.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.EcomX.ViewHolders.CustomViewHolder;
import com.example.EcomX.Listeners.OnAddCartListener;
import com.example.EcomX.Listeners.SelectListener;
import com.example.EcomX.Models.Product;
import com.example.EcomX.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private Context context;
    List<Product> products;
    SelectListener listener;
    OnAddCartListener prod_listener;

    public CustomAdapter(Context context, List<Product> products, SelectListener listener, OnAddCartListener prod_listener) {
        this.context = context;
        this.products = products;
        this.listener = listener;
        this.prod_listener = prod_listener;
    }

    @NonNull
    @Override
    //to generate view items
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_list_items,parent,false));
    }

    @Override
    //to insert each arrayitem into the  viewitem
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.text_price.setText("price: "+products.get(position).getPrice() + " LE");
        holder.text_title.setText(products.get(position).getName());
        holder.itemsleft.setText("items left: "+products.get(position).getQuantity());
        holder.text_id.setText(products.get(position).getId());
        if (products.get(position).getImg()!=null){
            String baseDir = "http://192.168.1.2/level1/Ecommerce/images/";
            String imgUrl = baseDir + products.get(position).getImg();
            Picasso.get().load(imgUrl).into(holder.img_headline);
        }



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnNewsClick(products.get(position));
            }
        });

        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, products.get(position).getName(), Toast.LENGTH_SHORT).show() ;

                prod_listener.addToCart(products.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}