package com.example.EcomX.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderInfo implements Serializable {
     List<ProductSummary> products_summary;
     String order_id;
     String order_address;
     String order_date;
     String total_cost;

    public List<ProductSummary> getProducts_summary() {
        return products_summary;
    }

    public void setProducts_summary(ArrayList<ProductSummary> products_summary) {
        this.products_summary = products_summary;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_address() {
        return order_address;
    }

    public void setOrder_address(String order_address) {
        this.order_address = order_address;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }
}
