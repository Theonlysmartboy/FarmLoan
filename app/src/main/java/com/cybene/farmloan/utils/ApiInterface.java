package com.cybene.farmloan.utils;

import com.cybene.farmloan.utils.items.DetailsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class ApiInterface {
    @POST("controller/retrieve.php")
    Call<DetailsResponse> saveDetails(@Body DetailsResponse save) {
        return null;
    }
}
