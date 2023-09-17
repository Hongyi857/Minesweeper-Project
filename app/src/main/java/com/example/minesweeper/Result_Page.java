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
    boolean win;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        time = getIntent().getDoubleExtra("Time",1);
        timetoshow = (int) time/1000;
        win = getIntent().getBooleanExtra("Result",true);
        button = findViewById(R.id.returnbutton);
        button.setOnClickListener(
                v -> {
                    Intent intent = new Intent(Result_Page.this,MainActivity.class);
                    startActivity(intent);
                }
        );
        String resulttext;
        if(win){
             resulttext = "Used " + timetoshow + " seconds.\n" +
                    "You won.\n" +
                    "Good job!";
        }else{
             resulttext = "Used " + timetoshow + " seconds.\n" +
                    "You lost.\n" +
                    "Try again!";
        }


        result = findViewById(R.id.results);
        result.setText(resulttext);

    }


}
