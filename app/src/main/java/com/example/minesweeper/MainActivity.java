package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected ArrayList<TextView> cells;
    protected Gamer game = new Gamer();
    protected boolean flagmode = true;
    protected boolean mined = false;
    private static final int NUM_MINES = 4;
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

            }
        }
        game.setmines(cells.size(),NUM_MINES);




    }


    private int findIndexOfCellTextView(TextView tv) {
        for (int n=0; n<cells.size(); n++) {
            if (cells.get(n) == tv)
                return n;
        }
        return -1;
    }
    public void onClickTV(View view){
        /*if(endgame){

        }*/

        TextView tv = (TextView) view;
        int n = findIndexOfCellTextView(tv);
        int i = n/10;
        int j = n%10;
        /*if(mines.contains(n)){
            bomb();
            endgame = true;
        }*/

        if(flagmode){
            if(game.flags.contains(n)){ //if there is already a flag, remove the flag.
                game.flags.remove((Integer) n);
                tv.setText("");
            }
            else{ //if it is not flagged yet, add a flag
                String flag_icon = getString(R.string.flag);
                tv.setText(flag_icon);
                game.flags.add(n);
            }

        }
        else{
            game.dig(n);
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
    protected void dig(int r, int c){

    }

}