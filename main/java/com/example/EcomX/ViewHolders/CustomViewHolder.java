package com.example.EcomX.ViewHolders;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.EcomX.R;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    public TextView text_title;
    public TextView text_price;
    public TextView text_id;
    public TextView text_source;
    public TextView itemsleft ;
    public  ImageView img_headline;
    public  Button btnadd;
    public CardView cardView;



    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        text_title = itemView.findViewById(R.id.text_title);
        text_price = itemView.findViewById(R.id.text_source);
        itemsleft = itemView.findViewById(R.id.itemleft);
        btnadd = itemView.findViewById(R.id.addCartt);
        img_headline = itemView.findViewById(R.id.img_headline);
        cardView = itemView.findViewById(R.id.main_container);
        text_id= itemView.findViewById(R.id.prod_id);



    }

}
