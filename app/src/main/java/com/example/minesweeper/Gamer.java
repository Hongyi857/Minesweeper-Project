package com.example.minesweeper;

import android.graphics.Color;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gamer {

    protected ArrayList<TextView> cells;
    protected void addcells(ArrayList<TextView> l){
        cells = l;
    }
    protected ArrayList<Integer> mines = new ArrayList<>();
    protected ArrayList<Integer> flags = new ArrayList<>();
    protected ArrayList<Integer> digged = new ArrayList<>();
    protected void setmines(int size,int num){
        Random random = new Random();

        while(mines.size() < num) {
            int n = random.nextInt(size);
            if(!mines.contains(n)){
                mines.add(n);
            }
        }
    }
    protected void showmine(){ //debugging function
        for (int i = 0; i < mines.size(); i++){
            int idx = mines.get(i);
            cells.get(idx).setBackgroundColor(Color.LTGRAY);
            cells.get(idx).setText("B");
        }
    }
    protected boolean dig(int n){
        if(n < 0 || n >= 120){
            return true;
        }
        if(digged.contains(n)){
            return true;
        }
        if(mines.contains(n)){
            return false;
        }
        digged.add(n);
        int count = checkmine(n);
        if (count == 0){
            cells.get(n).setBackgroundColor(Color.LTGRAY);
            if(n % 10 < 9){
                dig(n+1);
            }
            if(n % 10 > 0){
                dig(n-1);
            }
            if(n / 10 < 11){
                dig(n+10);
            }
            if(n % 10 > 0){
                dig(n-10);
            }
            if(n % 10 < 9 && n / 10 > 0){
                dig(n-9);
            }
            if(n % 10 >0 && n / 10 > 0){
                dig(n-11);
            }
            if(n % 10 > 0 && n / 10 < 11){
                dig(n+9);
            }
            if(n % 10 < 9 && n / 10 < 11){
                dig(n+11);
            }
            dig(n-1);
            dig(n-10);
        }
        else{
            cells.get(n).setBackgroundColor(Color.LTGRAY);
            cells.get(n).setText(""+count);
        }
        return true;
    }

    protected int checkmine(int index){
        int count = 0;
        if(index % 10 < 9 && mines.contains(index+1)){
            count++;
        }
        if(index % 10 > 0 && mines.contains(index-1)){
            count++;
        }
        if(index / 10 > 0 && mines.contains(index-10)){
            count++;
        }
        if(index / 10 < 11 && mines.contains(index+10)){
            count++;
        }
        if(index % 10 < 9 && index / 10 > 0 && mines.contains(index-9)){
            count++;
        }
        if(index % 10 > 0 && index / 10 > 0 && mines.contains(index-11)){
            count++;
        }
        if(index % 10 > 0 && index / 10 < 11 && mines.contains(index+9)){
            count++;
        }
        if(index % 10 < 9 && index / 10 < 11 && mines.contains(index+11)){
            count++;
        }
        return count;
    }

}
