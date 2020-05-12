package com.example.footballapp.data;

import com.example.footballapp.presentation.model.RestFootballResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface  PsgApi {

    @GET("myPsgApi.json")
    Call<RestFootballResponse> getFootballResponse();
}
