package com.example.EcomX.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class CartPostApiRequest  implements Serializable {


     String user_name;
     String address;
     ArrayList<CartItem> items;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<CartItem> items) {
        this.items = items;
    }
}
