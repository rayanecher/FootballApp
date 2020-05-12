package com.example.footballapp.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.sax.TextElementListener;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.footballapp.R;
import com.example.footballapp.Singletons;
import com.example.footballapp.presentation.model.psgTeam;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {


private TextView textDetail;
private TextView text_Number;
private TextView text_Position;
private TextView text_Age;
private TextView text_DateOfBirth;
private  TextView text_Nationality;
private ImageView image_Joueur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textDetail = findViewById(R.id.detail_txt);
        text_Number = findViewById(R.id.textView2);
        image_Joueur = findViewById(R.id.imageView);
        text_Position = findViewById(R.id.textView3);
        text_Age = findViewById(R.id.textView4);
        text_DateOfBirth = findViewById(R.id.textView5);
        text_Nationality = findViewById(R.id.textView6);
        Intent intent = getIntent();
        String psgTeamJson = intent.getStringExtra("psgTeamKey");
        psgTeam psgteam = Singletons.getGson().fromJson(psgTeamJson, psgTeam.class);
        showDetail (psgteam);

    }

    private void showDetail(psgTeam psgteam) {

        textDetail.setText(psgteam.getName());
        text_Number.setText(Integer.toString(psgteam.getNumber()));
        Picasso.get().load(psgteam.getUrl()).into(image_Joueur);
        text_Position.setText(psgteam.getPosition());
        text_Age.setText(psgteam.getAge());
        text_DateOfBirth.setText(psgteam.getDateOfBirth());
        text_Nationality.setText(psgteam.getNationality());

    }
}

