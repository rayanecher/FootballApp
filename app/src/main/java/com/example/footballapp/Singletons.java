package com.example.footballapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.footballapp.data.PsgApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static PsgApi psgApiInstance;
    private static SharedPreferences sharedPreferencesInstance;


    public static Gson getGson(){
        if(gsonInstance == null ){
            gsonInstance =new GsonBuilder()
                    .setLenient()
                    .create();
        }

        return  gsonInstance;
    }

    public static PsgApi getPsgApi(){
        if(psgApiInstance== null){


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

             psgApiInstance = retrofit.create(PsgApi.class);
        }

        return psgApiInstance;
    }


    public static SharedPreferences getSharedPreferences(Context context){
        if(sharedPreferencesInstance == null ){
            sharedPreferencesInstance = context.getSharedPreferences("application_esiea", Context.MODE_PRIVATE);
        }
        return  sharedPreferencesInstance;
    }
}
