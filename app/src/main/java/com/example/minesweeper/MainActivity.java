package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    protected ArrayList<TextView> cells;
    protected Gamer game = new Gamer();
    protected boolean flagmode = false;
    protected boolean endgame = false;
    private static final int NUM_MINES = 4;
    Chronometer chronometer;

    double time = 0.0;

    private int t = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cells = new ArrayList<TextView>();

        GridLayout grid = (GridLayout) findViewById(R.id.minegrid);
        LayoutInflater li = LayoutInflater.from(this);
        for (int i = 0; i<=11; i++) {
            for (int j=0; j<=9; j++) {
                TextView tv = (TextView) li.inflate(R.layout.custom_cell_layout, grid, false);
                //tv.setText(String.valueOf(i)+String.valueOf(j));
                tv.setTextColor(Color.GRAY);
                tv.setBackgroundColor(Color.GRAY);
                tv.setOnClickListener(this::onClickTV);

                GridLayout.LayoutParams lp = (GridLayout.LayoutParams) tv.getLayoutParams();
                lp.rowSpec = GridLayout.spec(i);
                lp.columnSpec = GridLayout.spec(j);

                grid.addView(tv, lp);

                cells.add(tv);
                game.addcells(cells);
            }
        }
        game.setmines(cells.size(),NUM_MINES);
        chronometer = (Chronometer) findViewById(R.id.Chronometer);
        chronometer.start();
    }


    public void changemode(View view){
        if(flagmode){
            Button button = (Button) findViewById(R.id.change_mode);
            String pick_icon = getString(R.string.pick);
            button.setText(pick_icon);
            flagmode = false;
        }
        else{
            Button button = (Button) findViewById(R.id.change_mode);
            String flag_icon = getString(R.string.flag);
            button.setText(flag_icon);
            flagmode = true;
        }
    }

    private int findIndexOfCellTextView(TextView tv) {
        for (int n=0; n<cells.size(); n++) {
            if (cells.get(n) == tv)
                return n;
        }
        return -1;
    }
    public void onClickTV(View view){
        if(endgame){
            showresult();
        }

        TextView tv = (TextView) view;
        int n = findIndexOfCellTextView(tv);

        if(flagmode){
            if(game.flags.contains(n)){ //if there is already a flag, remove the flag.
                game.flags.remove((Integer) n);
                tv.setText("");
            } else if (game.digged.contains(n)){ //if it is digged, don't place a flag
            } else { //if it is not flagged yet, add a flag
                String flag_icon = getString(R.string.flag);
                tv.setText(flag_icon);
                game.flags.add(n);
            }
        }
        else{
            //game.showmine(); for debugging
            boolean safe = game.dig(n);
            if(!safe){
                bomb();
            }
        }
        /*
        if (tv.getCurrentTextColor() == Color.GRAY) {
            tv.setTextColor(Color.GREEN);
            tv.setBackgroundColor(Color.parseColor("lime"));
        }else {
            tv.setTextColor(Color.GRAY);
            tv.setBackgroundColor(Color.LTGRAY);
        }*/
    }
    protected void bomb(){
        chronometer.stop();
        ArrayList<Integer> mines = game.getmines();
        for (int i = 0; i < mines.size(); i++){
            int idx = mines.get(i);
            cells.get(idx).setBackgroundColor(Color.LTGRAY);
            String bomb_icon = getString(R.string.mine);
            cells.get(idx).setText(bomb_icon);
            endgame = true;

        }
    }

    public void showresult(){
        Intent result = new Intent(MainActivity.this,Result_Page.class);
        result.putExtra("Time",t);
        startActivity(result);
    }

}