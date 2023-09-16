package com.example.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Result_Page extends AppCompatActivity {

    int time;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        time = Integer.parseInt(getIntent().getStringExtra("Time"));
    }

    public void newgame(View view){
        Intent playagain = new Intent(Result_Page.this,MainActivity.class);
        startActivity(playagain);
    }
}
