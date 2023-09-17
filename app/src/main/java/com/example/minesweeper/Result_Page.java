package com.example.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Result_Page extends AppCompatActivity {

    double time;
    int timetoshow;
    Button button;

    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        time = getIntent().getDoubleExtra("Time",1);
        timetoshow = (int) time/1000;
        button = findViewById(R.id.returnbutton);
        button.setOnClickListener(
                v -> {
                    Intent intent = new Intent(Result_Page.this,MainActivity.class);
                    startActivity(intent);
                }
        );

        result = findViewById(R.id.results);
        result.setText(""+timetoshow);

    }


}
