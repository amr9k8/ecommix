package com.example.EcomX.ViewHolders;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.EcomX.R;

public class CustomCartItemViewHolder extends RecyclerView.ViewHolder {

    public TextView text_title, text_price, text_id;
    public EditText quantity;
    public ImageView img_headline;
    public Button removeprod,addbtn,decbtn;
    public CardView cardView;




    public CustomCartItemViewHolder(@NonNull View itemView) {
        super(itemView);


        text_title = itemView.findViewById(R.id.text_title2);//prodname
        text_price = itemView.findViewById(R.id.text_source2);//prodprice
        addbtn = itemView.findViewById(R.id.incitem);
        decbtn = itemView.findViewById(R.id.decitem);
        quantity = itemView.findViewById(R.id.quantity);
        removeprod = itemView.findViewById(R.id.removeprod);
        img_headline = itemView.findViewById(R.id.img_headline2);
        cardView = itemView.findViewById(R.id.main_container2);


    }
}
