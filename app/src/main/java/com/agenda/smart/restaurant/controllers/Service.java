package com.agenda.smart.restaurant.controllers;

import com.agenda.smart.restaurant.model.CashClose;
import com.agenda.smart.restaurant.model.CashCloseDetails;
import com.agenda.smart.restaurant.model.Categories;
import com.agenda.smart.restaurant.model.CheckOut;
import com.agenda.smart.restaurant.model.City;
import com.agenda.smart.restaurant.model.Details;
import com.agenda.smart.restaurant.model.GeneralResponse;
import com.agenda.smart.restaurant.model.Meal;
import com.agenda.smart.restaurant.model.Meals;
import com.agenda.smart.restaurant.model.Order;
import com.agenda.smart.restaurant.model.Region;
import com.agenda.smart.restaurant.model.ReportsClient;
import com.agenda.smart.restaurant.model.ReportsEarning;
import com.agenda.smart.restaurant.model.ReportsEmployees;
import com.agenda.smart.restaurant.model.ReportsMeals;
import com.agenda.smart.restaurant.model.ReportsOrderDaily;
import com.agenda.smart.restaurant.model.ReportsTaxes;
import com.agenda.smart.restaurant.model.Settings;
import com.agenda.smart.restaurant.model.Tables;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Abdelrahman on 7/8/2018.
 */

public interface Service {

    @GET("Registration/SelectCity")
    Call<List<City>> getCities();

    @GET("Registration/SelectDistric/{CityID}")
    Call<List<Region>> getRegions(@Path("CityID") int city_id);

    @GET("meals")
    Call<Meals> getMeals();

    @FormUrlEncoded
    @POST("waiter/login")
    Call<GeneralResponse> loginWaiter(@Field("phone") String phone,
                                      @Field("password") String password);

    @FormUrlEncoded
    @POST("admin/login")
    Call<GeneralResponse> loginAdmin(@Field("phone") String phone,
                                     @Field("password") String password);

    @FormUrlEncoded
    @POST("admin/shift/{cashier_id}/update")
    Call<GeneralResponse> updateCashClose(@Path("cashier_id") int cashier_id,
                                          @Field("api_token") String api_token);

    @FormUrlEncoded
    @POST("waiter/order")
    Call<GeneralResponse> postOrder(@Field("api_token") String api_token,
                                    @Field("table_id") int table_id,
                                    @Field("meal_id") int meal_id,
                                    @Field("quantity") String quantity,
                                    @Field("size_id") int item_size);

    @GET("tables")
    Call<Tables> getTables();

    @GET("setting")
    Call<Settings> getSetting();

    @GET("waiter/order/checkout")
    Call<CheckOut> getOrder(@Query("api_token") String api_token);

    @GET("waiter/order/checkout-details/{order_id}")
    Call<Details> getDetails(@Path("order_id") int order_id, @Query("api_token") String api_token);

    @FormUrlEncoded
    @POST("waiter/order/checkout/{order_id}")
    Call<GeneralResponse> checkOut(@Path("order_id") int order_id, @Field("api_token") String api_token
    );

    @FormUrlEncoded
    @POST("waiter/order/item/{item_id}/delete")
    Call<GeneralResponse> deleteItem(@Path("item_id") int item_id, @Field("api_token") String api_token
    );

    @GET("categories")
    Call<Categories> getCategories();

    @GET("admin/reports/meals")
    Call<ReportsMeals> getReportsMeal(@Query("api_token") String api_token,
                                      @Query("meal_id") int meal_id,
                                      @Query("date_from") String date_from,
                                      @Query("date_to") String date_to);

    @GET("admin/reports/tax")
    Call<ReportsTaxes> getReportTax(@Query("api_token") String api_token,
                                    @Query("date_from") String date_from,
                                    @Query("date_to") String date_to);

    @GET("admin/reports/orders/daily")
    Call<ReportsOrderDaily> getReportOrderDaily(@Query("api_token") String api_token);

    @GET("admin/shift")
    Call<CashClose> getCashClose(@Query("api_token") String api_token);

    @GET("admin/shift/{cashier_id}")
    Call<CashCloseDetails> getCashCloseDetails(@Path("cashier_id") int cashier_id, @Query("api_token") String api_token);

    @GET("admin/reports/orders/interval")
    Call<ReportsOrderDaily> getReportOrderInterval(@Query("api_token") String api_token,
                                                   @Query("date_from") String date_from,
                                                   @Query("date_to") String date_to);

    @GET("admin/reports/earnings/interval")
    Call<ReportsEarning> getReportEarningInterval(@Query("api_token") String api_token);

    @GET("admin/reports/employees")
    Call<ReportsEmployees> getReportEmployees(@Query("api_token") String api_token,
                                              @Query("date_from") String date_from,
                                              @Query("date_to") String date_to);

    @GET("admin/reports/customers")
    Call<ReportsClient> getReportClient(@Query("api_token") String api_token,
                                        @Query("date_from") String date_from,
                                        @Query("date_to") String date_to);


    @GET("meals/{category_id}")
    Call<Meals> getMeals(@Path("category_id") int category_id);

    @FormUrlEncoded
    @POST("Registration/CreateClient")
    Call<ResponseBody> registerUser(@Field("UserName") String UserName,
                                    @Field("ClintPassword") String password,
                                    @Field("Mail") String Mail,
                                    @Field("Mobile") String Mobile,
                                    @Field("DistrictID") int regionID,
                                    @Field("OtherAddress") String OtherAddress,
                                    @Field("IsActive") int userActive,
                                    @Field("IsDeleted") int userDeleted,
                                    @Field("Latitude") double Latitude,
                                    @Field("Longitude") double Longitude
    );

    // String BASE_URL = "http://192.168.1.22:8080/resturant/api/";
    String BASE_URL = "http://resturant.support-smartagenda.com/api/";

    class Fetcher {
        private static Service service = null;

        public static Service getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                service = retrofit.create(Service.class);
            }
            return service;
        }
    }
}
