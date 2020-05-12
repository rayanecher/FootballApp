package com.example.footballapp.presentation.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.footballapp.Constants;
import com.example.footballapp.Singletons;
import com.example.footballapp.data.PsgApi;
import com.example.footballapp.presentation.model.RestFootballResponse;
import com.example.footballapp.presentation.model.psgTeam;
import com.example.footballapp.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {


    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;


    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;


        }
        public void onStart(){

            List<psgTeam> psgTeamList = getDataFromCache();
            if (psgTeamList != null) {
                view.showList(psgTeamList);
            } else {
                makeApiCall();

            }


        }


    public void makeApiCall(){
        Call<RestFootballResponse> call = Singletons.getPsgApi().getFootballResponse();
        call.enqueue(new Callback<RestFootballResponse>() {
            @Override
            public void onResponse(Call<RestFootballResponse> call, Response<RestFootballResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<psgTeam> psgTeamList = response.body().getSquad();
                    saveList(psgTeamList);
                    view.showList(psgTeamList);
                } else {
                    view.showError();

                }
            }

            @Override
            public void onFailure(Call<RestFootballResponse> call, Throwable t) {

                view.showError();
            }
        });
    }

    private void saveList(List<psgTeam> psgTeamList) {
        String jsonString = gson.toJson(psgTeamList);

        sharedPreferences
                .edit()
                .putString(Constants.KEY_psgTEAM_LIST , jsonString)
                .apply();

    }

    private List<psgTeam> getDataFromCache() {
        String jsonpsgTeamList = sharedPreferences.getString( Constants.KEY_psgTEAM_LIST, null);

        if (jsonpsgTeamList == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<psgTeam>>() {
            }.getType();
            return gson.fromJson(jsonpsgTeamList, listType);
        }
    }


    public void onItemClick(psgTeam psgTeam){

        view.navigateToDetails(psgTeam);


        }


}
