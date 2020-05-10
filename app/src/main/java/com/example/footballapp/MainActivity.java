package com.example.footballapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    static final String BASE_URL = "https://raw.githubusercontent.com/rayanecher/FootballApp/master/";
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        makeApiCall();
    }

    private void showList(List<psgTeam> psgTeamList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new ListAdapter(psgTeamList);
        recyclerView.setAdapter(mAdapter);
    }




    public void makeApiCall(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PsgApi psgApi = retrofit.create(PsgApi.class);

        Call<RestFootballResponse> call = psgApi.getFootballResponse();
        call.enqueue(new Callback<RestFootballResponse>() {
            @Override
            public void onResponse(Call<RestFootballResponse> call, Response<RestFootballResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<psgTeam> psgTeamList = response.body().getSquad();
                    showList(psgTeamList);
                } else {
                    showError();

                }
            }

                @Override
                public void onFailure(Call<RestFootballResponse> call, Throwable t) {
                    showError();
                }
        });
    }
    public void showError(){
        Toast.makeText(getApplicationContext(), "API ERROR", Toast.LENGTH_SHORT).show();


    }
}
