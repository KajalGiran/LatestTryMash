package com.trymash.model;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by desktop on 04-04-2017.
 */
@Table(name= "scoreboard")
public class ScoreBoard extends SugarRecord {
    private int score;
    private String time;

    public ScoreBoard() {

    }

    public ScoreBoard(int score, String time){
        this.score = score;
        this.time = time;
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
