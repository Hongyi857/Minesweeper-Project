package com.example.minesweeper;

import java.util.ArrayList;
import java.util.Random;

public class Gamer {
    protected ArrayList<Integer> mines = new ArrayList<>();
    protected ArrayList<Integer> flags = new ArrayList<>();
    protected void setmines(int size,int num){
        Random random = new Random();

        while(mines.size() < num) {
            int n = random.nextInt(size);
            if(!mines.contains(n)){
                mines.add(n);
            }
        }
    }

    protected void dig(int n){

    }

}
