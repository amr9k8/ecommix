package com.example.EcomX.Retrofit;

import android.content.Context;
import android.widget.Toast;

import com.example.EcomX.Listeners.OnFetchDataListener;
import com.example.EcomX.Listeners.OnFetchOrdersListener;
import com.example.EcomX.Listeners.OnRegisterLoginListener;
import com.example.EcomX.Models.CartPostApiRequest;
import com.example.EcomX.Models.OrderApiResponse;
import com.example.EcomX.Models.ProductApiResponse;
import com.example.EcomX.Models.RegisterApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;



public class RequestManager {
// to use  in any  activity set working context here
    Context context;


        //working version
        Gson gson = new GsonBuilder().setLenient().create();

        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2/level1/Ecommerce/Api/v1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    // retrofit autofill methods here and return our required data
    //create method for everydatatype you need here , and decide get,post and add queries
    public interface RetroFitApiMethods{
        @GET("Product/getProducts.php")
        Call<ProductApiResponse> callProducts(
                @Query("prod_id") String prod_id,
                @Query("cat_id") String category,
                @Query("q")  String q,
                @Query("barcode") String barcode
        );


        @FormUrlEncoded
        @POST("Customer/register.php")
        Call<RegisterApiResponse> register(
                @Field("action") String action,
                @Field("username") String username,
                @Field("email") String email,
                @Field("password") String password,
                @Field("birthdate") String birthdate,
                @Field("job") String job,
                @Field("gender") String gender
        );


        @FormUrlEncoded
        @POST("Customer/login.php")
        Call<RegisterApiResponse> login(
                @Field("action") String action,
                @Field("username") String username,
                @Field("password") String password
        );

        @FormUrlEncoded
        @POST("Customer/forgetpassword.php")
        Call<RegisterApiResponse> forgetpass(
                @Field("action") String action,
                @Field("username") String username
        );

        @FormUrlEncoded
        @POST("Customer/updatepassword.php")
        Call<RegisterApiResponse> updatepass(
                @Field("action") String action,
                @Field("cust_name") String cust_name,
                @Field("resetcode") String resetcode,
                @Field("newpass") String newpass
        );




        //@Headers("Content-Type: application/json")
       // @GET("Order/order.php")
        @FormUrlEncoded
        @POST("Order/getorder.php")
        Call<OrderApiResponse> getOrders(
                @Field("action") String action,
                @Field("user_name") String cust_name,
                @Field("monthly") String monthly
        );



        @POST("Order/postorder.php")
        @Headers("Content-Type: application/json")
        Call<RegisterApiResponse> postOrders(@Body CartPostApiRequest cart_data);


    }

    //to make retrofit   fill the methods there inside ur interface
    RetroFitApiMethods RetroFitClass = retrofit.create(RetroFitApiMethods.class);

    // take listener as param to pass recieved data to any other activty or elsewhere

    public  void postOrderRequest(OnRegisterLoginListener listener ,CartPostApiRequest cart_data ){
        Call<RegisterApiResponse> call = RetroFitClass.postOrders(cart_data);
        try{
            //finally check  if call is ok , fill data inside the listner obj
            call.enqueue(new Callback<RegisterApiResponse>() {
                @Override
                public void onResponse(Call<RegisterApiResponse> call, Response<RegisterApiResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }
                       Toast.makeText(context, response.body().toString(), Toast.LENGTH_SHORT).show();
                    listener.onRegisterLogin(response.body().getMessage());
                }

                @Override
                public void onFailure(Call<RegisterApiResponse> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    listener.onError(t.getMessage());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }



    }


    public void getCustomerOrders(OnFetchOrdersListener listener, String cust_name){



//get call object of requried data which is ProductApiResponse [its a pojo class represent data in json response]
        Call<OrderApiResponse> call = RetroFitClass.getOrders("getOrders",cust_name,"true");
        try{
            //finally check  if call is ok , fill data inside the listner obj
            call.enqueue(new Callback<OrderApiResponse>() {
                @Override
                public void onResponse(Call<OrderApiResponse> call, Response<OrderApiResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(context,response.body().getStatues()+"ax" , Toast.LENGTH_SHORT).show();
                    //   Toast.makeText(context, response.body().getProducts().toString(), Toast.LENGTH_SHORT).show();
                   listener.onFetchOrdersData(response.body(), response.message());
                }

                @Override
                public void onFailure(Call<OrderApiResponse> call, Throwable t) {
                    listener.onError(t.getMessage());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public  void registerUser(OnRegisterLoginListener listener , String username , String email , String password , String birthdate , String job , String gender){

        Call<RegisterApiResponse> call = RetroFitClass.register("register",username,email,password,birthdate,job,gender);


        try{
            //finally check  if call is ok , fill data inside the listner obj
            call.enqueue(new Callback<RegisterApiResponse>() {
                @Override
                public void onResponse(Call<RegisterApiResponse> call, Response<RegisterApiResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }
                    //   Toast.makeText(context, response.body().getProducts().toString(), Toast.LENGTH_SHORT).show();
                    listener.onRegisterLogin(response.body().getMessage());
                }

                @Override
                public void onFailure(Call<RegisterApiResponse> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    listener.onError(t.getMessage());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    public  void loginUser(OnRegisterLoginListener listener ,String username , String password ){
        Call<RegisterApiResponse> call = RetroFitClass.login("login",username,password);


        try{
            //finally check  if call is ok , fill data inside the listner obj
            call.enqueue(new Callback<RegisterApiResponse>() {
                @Override
                public void onResponse(Call<RegisterApiResponse> call, Response<RegisterApiResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }
                    //   Toast.makeText(context, response.body().getProducts().toString(), Toast.LENGTH_SHORT).show();
                    listener.onRegisterLogin(response.body().getMessage());
                }

                @Override
                public void onFailure(Call<RegisterApiResponse> call, Throwable t) {
                    listener.onError(t.getMessage());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    public  void forgetUserPass(OnRegisterLoginListener listener ,String username ){
        Call<RegisterApiResponse> call = RetroFitClass.forgetpass("forget",username);


        try{
            //finally check  if call is ok , fill data inside the listner obj
            call.enqueue(new Callback<RegisterApiResponse>() {
                @Override
                public void onResponse(Call<RegisterApiResponse> call, Response<RegisterApiResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }
                    //   Toast.makeText(context, response.body().getProducts().toString(), Toast.LENGTH_SHORT).show();
                    listener.onRegisterLogin(response.body().getMessage());
                }

                @Override
                public void onFailure(Call<RegisterApiResponse> call, Throwable t) {
                    listener.onError(t.getMessage());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }



    }


    public  void updateUserPass(OnRegisterLoginListener listener ,String cust_name ,String resetcode ,String newpass  ){


        Call<RegisterApiResponse> call = RetroFitClass.updatepass("updatepass",cust_name,resetcode,newpass);


        try{
            //finally check  if call is ok , fill data inside the listner obj
            call.enqueue(new Callback<RegisterApiResponse>() {
                @Override
                public void onResponse(Call<RegisterApiResponse> call, Response<RegisterApiResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }
                    //   Toast.makeText(context, response.body().getProducts().toString(), Toast.LENGTH_SHORT).show();
                    listener.onRegisterLogin(response.body().getMessage());
                }

                @Override
                public void onFailure(Call<RegisterApiResponse> call, Throwable t) {
                    listener.onError(t.getMessage());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    public void getProducts(OnFetchDataListener listener, String  prod_id, String  category, String q, String barcode){

//get call object of requried data which is ProductApiResponse [its a pojo class represent data in json response]
        Call<ProductApiResponse> call = RetroFitClass.callProducts(prod_id,category,q,barcode);
        try{
            //finally check  if call is ok , fill data inside the listner obj
            call.enqueue(new Callback<ProductApiResponse>() {
                @Override
                public void onResponse(Call<ProductApiResponse> call, Response<ProductApiResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(context, Integer.toString(response.body().getProducts().size()), Toast.LENGTH_SHORT).show();
                    listener.onFetchData(response.body().getProducts(), response.message());

                }

                @Override
                public void onFailure(Call<ProductApiResponse> call, Throwable t) {
                    listener.onError(t.getMessage());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }






}
