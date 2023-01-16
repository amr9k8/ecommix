package com.example.EcomX.Adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.EcomX.ViewHolders.CustomCartItemViewHolder;
import com.example.EcomX.Listeners.OnCartItemActionsListener;
import com.example.EcomX.Listeners.SelectListener;
import com.example.EcomX.Models.Product;
import com.example.EcomX.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomCartItemAdapter extends RecyclerView.Adapter<CustomCartItemViewHolder> {


    private Context context;
    ArrayList<Product> products;
    SelectListener listener;
    OnCartItemActionsListener action_prod_listener;
    public CustomCartItemAdapter(Context context, ArrayList<Product> products,SelectListener listener , OnCartItemActionsListener action_prod_listener ){
        this.context = context;
        this.products = products;
        this.listener = listener;
        this.action_prod_listener = action_prod_listener;
    }




    @NonNull
    @Override
    public CustomCartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomCartItemViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_product_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomCartItemViewHolder holder, int position) {
       // products.get(position).setQuantity("1"); //set item no to 1
        holder.quantity.setText(products.get(position).getQuantity()); //display item no.
        holder.text_title.setText(products.get(position).getName());
        holder.text_price.setText("price: "+products.get(position).getPrice() + " LE");
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

        holder.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value = products.get(position).getQuantity();//return no. of item in shopping cart
                int newval= Integer.parseInt(value);
                newval+=1;
                if(newval <=0)
                    newval=1;
                products.get(position).setQuantity(Integer.toString(newval));
                holder.quantity.setText( products.get(position).getQuantity());
                action_prod_listener.onUpdate(newval,position);
            }
        });
        holder.decbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value =  products.get(position).getQuantity();//return no. of item in shopping cart
                int newval= Integer.parseInt(value);
                newval-=1;
                if(newval <=0)
                    newval=1;
                products.get(position).setQuantity(Integer.toString(newval));
                holder.quantity.setText( products.get(position).getQuantity());

                action_prod_listener.onUpdate(newval,position);
                //action_prod_listener.onDec(position);

            }
        });

        holder.removeprod.setOnClickListener(new View.OnClickListener() {
            String value =  holder.quantity.getText().toString();
            int newval= Integer.parseInt(value);
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, products.get(position).getName(), Toast.LENGTH_SHORT).show() ;
                action_prod_listener.onRemove(newval,position);
            }
        });


        holder.quantity.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                String value =  s.toString();

                if(value.length() != 0){
                    int newval= Integer.parseInt(value);
                    if(newval <=0)
                        newval=1;
                    Toast.makeText(context,value, Toast.LENGTH_SHORT).show();
                    products.get(position).setQuantity(Integer.toString(newval));
                    action_prod_listener.onUpdate(1,position);
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
