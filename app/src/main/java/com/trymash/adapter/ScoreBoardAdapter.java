package com.trymash.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trymash.R;
import com.trymash.model.ScoreBoard;

import java.util.List;


/**
 * Created by desktop on 04-04-2017.
 */

public class ScoreBoardAdapter extends RecyclerView.Adapter<ScoreBoardAdapter.CustomViewHolder> {
    private List<ScoreBoard> scoreBoards;

    public ScoreBoardAdapter(List<ScoreBoard> scoreBoards) {
        this.scoreBoards = scoreBoards;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_score_board, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        ScoreBoard scoreBoard = scoreBoards.get(position);
        if(0==position)
        {
            holder.tvScore.setTextColor(Color.RED);
            holder.tvTime.setTextColor(Color.RED);
        }
        else {
            holder.tvScore.setTextColor(Color.BLACK);
            holder.tvTime.setTextColor(Color.BLACK);
        }
        holder.tvScore.setText("" + scoreBoard.getScore());
        holder.tvTime.setText(scoreBoard.getTime());
    }

    @Override
    public int getItemCount() {
        return scoreBoards.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView tvScore;
        private TextView tvTime;

        public CustomViewHolder(View itemView) {
            super(itemView);
            tvScore = (TextView) itemView.findViewById(R.id.tv_score);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
}
