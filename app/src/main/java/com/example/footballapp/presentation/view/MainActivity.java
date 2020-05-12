package com.example.footballapp.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.footballapp.R;
import com.example.footballapp.Singletons;
import com.example.footballapp.presentation.controller.MainController;
import com.example.footballapp.presentation.model.psgTeam;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(
                this,
                Singletons.getGson(),
                Singletons.getSharedPreferences((getApplicationContext()))


        );
        controller.onStart();

    }



    public void showList(List<psgTeam> psgTeamList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(psgTeamList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(psgTeam item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }



    public void showError(){
        Toast.makeText(getApplicationContext(), "API ERROR", Toast.LENGTH_SHORT).show();

    }

    public void navigateToDetails(psgTeam psgTeam) {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
       myIntent.putExtra("psgTeamKey", Singletons.getGson().toJson(psgTeam));

     MainActivity.this.startActivity(myIntent);

    }
}

