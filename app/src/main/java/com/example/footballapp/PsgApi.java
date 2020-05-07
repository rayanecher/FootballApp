package com.example.footballapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface  PsgApi {

    @GET("myPsgApi.json")
    Call<RestFootballResponse> getFootballResponse();
}
