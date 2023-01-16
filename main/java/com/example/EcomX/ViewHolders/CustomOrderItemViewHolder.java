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

public class CustomOrderItemViewHolder extends RecyclerView.ViewHolder {

    public TextView order_id,order_date,order_totalprice,order_address;
    public Button view_summary;
    public  CardView cardView;

    public CustomOrderItemViewHolder(@NonNull View itemView) {
        super(itemView);

        order_id = itemView.findViewById(R.id.order_id);
        order_date = itemView.findViewById(R.id.order_date);
        order_totalprice = itemView.findViewById(R.id.order_totalprice);
        order_address = itemView.findViewById(R.id.order_address);
        view_summary = itemView.findViewById(R.id.view_summary);
        cardView = itemView.findViewById(R.id.main_container3);

    }
}
