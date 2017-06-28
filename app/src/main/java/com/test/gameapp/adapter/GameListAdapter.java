package com.test.gameapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.gameapp.R;
import com.test.gameapp.model.GameData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Developer on 4/13/2017.
 */

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameViewHolder> {

    private final List<GameData> mGameDataList = new ArrayList<>();
    private final LayoutInflater mInflater;

    public GameListAdapter(LayoutInflater inflater) {
        mInflater = inflater;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GameViewHolder(mInflater.inflate(R.layout.layout_game_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        GameData gameData = mGameDataList.get(position);
        holder.mGameName.setText(gameData.getName());
    }

    @Override
    public int getItemCount() {
        return mGameDataList.size();
    }

    public void setGameDataList(List<GameData> gameDataList) {
        if (gameDataList != null) {
            mGameDataList.clear();
            mGameDataList.addAll(gameDataList);
            notifyDataSetChanged();
        }
    }

    public class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mGameName;

        public GameViewHolder(View itemView) {
            super(itemView);
            mGameName = (TextView) itemView.findViewById(R.id.gameName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(mGameDataList.get(getAdapterPosition()));
            }
        }
    }

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {

        void onItemClick(GameData gameData);
    }
}
