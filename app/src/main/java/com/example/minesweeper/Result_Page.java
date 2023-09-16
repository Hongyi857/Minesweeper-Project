package com.example.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Result_Page extends AppCompatActivity {

    int time;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        time = getIntent().getIntExtra("Time",1);
        button = findViewById(R.id.returnbutton);
        button.setOnClickListener(
                v -> {
                    Intent intent = new Intent(Result_Page.this,MainActivity.class);
                    startActivity(intent);
                }
        );
    }


}
