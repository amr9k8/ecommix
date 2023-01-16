

package com.example.EcomX.Listeners;

import com.example.EcomX.Models.Product;

import java.util.List;

public interface OnRegisterLoginListener<RegisterApiResponse> {
    void onRegisterLogin(String message);
    void onError(String error);
}