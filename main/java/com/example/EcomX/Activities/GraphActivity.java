package com.example.EcomX.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.EcomX.Models.OrderApiResponse;
import com.example.EcomX.Models.OrderInfo;
import com.example.EcomX.Models.Product;
import com.example.EcomX.Models.ProductSummary;
import com.example.EcomX.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphActivity extends AppCompatActivity {
    private BarChart chart;
    List<OrderInfo> orderslist;
    OrderApiResponse orderresponse;
    List<ProductSummary> all_prod_summaries = new ArrayList<>();
    List<ProductSummary> uni_prod_summaries = new ArrayList<>();
    TextView order_made, total_purchase_monthly;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Intent intent = getIntent();
        if (intent.hasExtra("BUNDLE")) {
            Bundle args = intent.getBundleExtra("BUNDLE");
            orderresponse = (OrderApiResponse) args.getSerializable("OrderApiResponse");
            Toast.makeText(this, orderresponse.getTotalpurchasescost(), Toast.LENGTH_SHORT).show();

            ArrayList<BarEntry> information = new ArrayList<>();
            order_made = findViewById(R.id.monthly_orders);
            order_made.setText(orderresponse.getTotalorders());
            total_purchase_monthly = findViewById(R.id.monthly_cost);
            total_purchase_monthly.setText(orderresponse.getTotalpurchasescost());
            chart = findViewById(R.id.chart1);
            orderslist = orderresponse.getOrdersinfo_list();
            int x, y;
            filterproduct(orderslist);
            Toast.makeText(this, Integer.toString(uni_prod_summaries.size()), Toast.LENGTH_SHORT).show();
            for (ProductSummary one_prod : uni_prod_summaries) {
                x = Integer.parseInt(one_prod.getProduct_id());
                y = Integer.parseInt(one_prod.getProduct_amount());

                information.add(new BarEntry(x, y));
            }


            BarDataSet barDataSet = new BarDataSet(information, "Groceries");
            barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
            barDataSet.setValueTextColor(Color.BLACK);
            barDataSet.setValueTextSize(12f);

            BarData barData = new BarData(barDataSet);

            chart.setFitBars(true);
            chart.setData(barData);
            chart.getDescription().setText("Order History");
            chart.animateY(2000);
        }

    }

    protected void filterproduct(List<OrderInfo> OrderInfo) {

        boolean found = false;
        //add all products_summary items into one array
        for (OrderInfo oneorder : orderslist) {
            List<ProductSummary> products = oneorder.getProducts_summary();
            for (ProductSummary one_prod : products) {
                all_prod_summaries.add(one_prod);
            }

        }

        //search duplicates and removing them
        int total = all_prod_summaries.size()-1;
        int x = 0; //iterator for uni array
        String id1="",id2="";
        boolean flag = false;
        int quantity1,quantity2;
        for ( int i = 0; i<=total;i++){
            if (!uni_prod_summaries.isEmpty()){
                id1= all_prod_summaries.get(i).getProduct_id();
                for (int j =0; j<x;j++)
                {
                    if(id1.equals(uni_prod_summaries.get(j).getProduct_id())){
                        quantity2 = Integer.parseInt(uni_prod_summaries.get(j).getProduct_amount());
                        quantity1 = Integer.parseInt(all_prod_summaries.get(i).getProduct_amount());
                        uni_prod_summaries.get(j).setProduct_amount(Integer.toString(quantity1+quantity2));
                        flag = true; //id duplicates
                        break;
                    }
                }
                if (flag != true){

                        uni_prod_summaries.add(all_prod_summaries.get(i));
                        x++;
                    }
                else{
                    flag = false;
                }

            }else
            {
                uni_prod_summaries.add(all_prod_summaries.get(i));
                x++;
            }


        }


    }

}

