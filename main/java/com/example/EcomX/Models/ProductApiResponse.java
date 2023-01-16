package com.example.EcomX.Models;

import java.io.Serializable;
import java.util.List;

public class ProductApiResponse implements Serializable {
    String status;
    String totalproducts;
    List<Product> products;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalproducts() {
        return totalproducts;
    }

    public void setTotalproducts(String totalproducts) {
        this.totalproducts = totalproducts;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
