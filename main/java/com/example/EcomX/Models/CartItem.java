package com.example.EcomX.Models;

import java.io.Serializable;

public class CartItem implements Serializable {

     String prod_id;
     String prod_amount;

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_amount() {
        return prod_amount;
    }

    public void setProd_amount(String prod_amount) {
        this.prod_amount = prod_amount;
    }
}
