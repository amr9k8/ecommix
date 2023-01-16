package com.example.EcomX.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.EcomX.ViewHolders.CustomOrderItemViewHolder;
import com.example.EcomX.Models.OrderInfo;
import com.example.EcomX.Models.ProductSummary;
import com.example.EcomX.R;

import java.util.List;

public class CustomOrderItemAdapter extends RecyclerView.Adapter<CustomOrderItemViewHolder>{


    private Context context;
    public List<OrderInfo> ordersinfo_list;
    public CustomOrderItemAdapter(Context context, List<OrderInfo> ordersinfo_list ){
        this.context = context;
        this.ordersinfo_list = ordersinfo_list;
    }




    @NonNull
    @Override
    public CustomOrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomOrderItemViewHolder(LayoutInflater.from(context).inflate(R.layout.order_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomOrderItemViewHolder holder, int position) {
        holder.order_id.setText(ordersinfo_list.get(position).getOrder_id());
        holder.order_address.setText(ordersinfo_list.get(position).getOrder_address());
        holder.order_date.setText(ordersinfo_list.get(position).getOrder_date());
        holder.order_totalprice.setText(ordersinfo_list.get(position).getTotal_cost());
        holder.view_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add all products_summary items into one array
               String order_id =  ordersinfo_list.get(position).getOrder_id();
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("ORDER ID :"+order_id+"\n");
               String prod_name="",prod_amount="",prod_price="";
               String dash = "\n________________________________________\n";
               String msg="";
              //  StringBuilder msg = new StringBuilder();
                List<ProductSummary> prodsummrList =  ordersinfo_list.get(position).getProducts_summary();
                msg = (dash);
                for (ProductSummary prod:prodsummrList)
                {
                    prod_name=prod.getProduct_name();
                    prod_price=prod.getProduct_price();
                    prod_amount=prod.getProduct_amount();
                    msg += "ProductName :"+prod_name+"\n"+"Amount :"+prod_amount+"\n"+"Product Price :"+prod_price+"\n";
                    msg+=dash;
                }
                    alertDialog.setMessage(msg);

                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                    alertDialog.show();
                msg = "";
            }
        });

    }

    @Override
    public int getItemCount() {
        return ordersinfo_list.size();
    }
}
