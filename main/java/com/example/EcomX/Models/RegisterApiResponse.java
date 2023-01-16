package com.example.EcomX.Models;

import java.io.Serializable;

public class RegisterApiResponse implements Serializable {


    String error;
    String message;
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
