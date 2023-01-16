package com.example.EcomX.Listeners;

import com.example.EcomX.Models.Product;

import java.util.ArrayList;
import java.util.List;

public interface OnFetchOrdersListener <OrderApiResponse>{

    void onFetchOrdersData(OrderApiResponse ordersResponse, String message);
    void onError(String message);
}
//package com.example.EcomX;
//
//        import com.example.EcomX.Models.Product;
//
//        import java.util.List;
//
//public interface OnRegisterLoginListener<RegisterApiResponse>  {
//    void onRegisterLogin(String message);
//    void onError(String error);
//}
