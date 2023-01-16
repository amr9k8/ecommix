package com.example.EcomX.Listeners;



import java.util.List;
import com.example.EcomX.Models.Product;



public interface OnFetchDataListener<ProductApiResponse> {
    void onFetchData(List<Product> list, String message);
    void onError(String message);
}
