package com.trymash.model;


/**
 * Created by desktop on 04-04-2017.
 */
public class ScoreBoard {
    int id;
    int score;
    String time;
    public ScoreBoard(){

    }
    public ScoreBoard(int score, String time){
        this.score = score;

        this.time = time;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
