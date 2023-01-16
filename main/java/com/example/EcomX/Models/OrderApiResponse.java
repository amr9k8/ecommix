package com.example.EcomX.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderApiResponse  implements Serializable {

     String statues;
     String cust_id;
     String cust_name;
     String totalpurchasescost;
     String totalorders;
     List<OrderInfo> allordersinfo;


    public String getStatues() {
        return statues;
    }

    public void setStatues(String statues) {
        this.statues = statues;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getTotalpurchasescost() {
        return totalpurchasescost;
    }

    public void setTotalpurchasescost(String totalpurchasescost) {
        this.totalpurchasescost = totalpurchasescost;
    }

    public String getTotalorders() {
        return totalorders;
    }

    public void setTotalorders(String totalorders) {
        this.totalorders = totalorders;
    }

    public List<OrderInfo> getOrdersinfo_list() {
        return allordersinfo;
    }

    public void setOrdersinfo_list(ArrayList<OrderInfo> ordersinfo_list) {
        this.allordersinfo = ordersinfo_list;
    }
}
