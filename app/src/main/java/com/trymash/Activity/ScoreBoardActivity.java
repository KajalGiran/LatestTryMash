package com.trymash.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.trymash.R;
import com.trymash.adapter.ScoreBoardAdapter;
import com.trymash.helper.DatabaseHelper;
import com.trymash.model.ScoreBoard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * Created by desktop on 04-04-2017.
 */

public class ScoreBoardActivity extends AppCompatActivity {
    private List<ScoreBoard> scoreBoards= new ArrayList<>();
    private RecyclerView recyclerView;
    private ScoreBoardAdapter scoreBoardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        DatabaseHelper db = new DatabaseHelper(this);
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        scoreBoards = db.getAllScores();
        Collections.reverse(scoreBoards);
        //scoreBoards = ScoreBoard.listAll(ScoreBoard.class);
        if(scoreBoards.size()>0)
        {
            recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            scoreBoardAdapter = new ScoreBoardAdapter(scoreBoards);
            recyclerView.setAdapter(scoreBoardAdapter);
        }

    }
}
