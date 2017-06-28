package com.test.gameapp.activities;

import android.content.Intent;
import android.os.Bundle;

import com.test.gameapp.R;
import com.test.gameapp.base.BaseActivity;
import com.test.gameapp.presentation.DetailView;
import com.test.gameapp.presenter.DetailPresenter;

/**
 * Created by Android Developer on 4/14/2017.
 */

public class DetailActivity extends BaseActivity<DetailPresenter> implements DetailView {

    @Override
    protected void onCreated(Bundle savedInstanceState) {
        super.onCreated(savedInstanceState);
        showBack();
        DetailPresenter presenter = getPresenter();
        if (presenter != null) {
            Intent intent = getIntent();
            String gameName = intent.getStringExtra("game_name");
            String jackPot = intent.getStringExtra("jackpot");
            String date = intent.getStringExtra("date");
            String playerName = intent.getStringExtra("game_name");
            long balance = intent.getLongExtra("balance", -1L);
            String lastLoginDate = intent.getStringExtra("last_login_date");

            presenter.onHandleDetails(
                    gameName,
                    jackPot,
                    date,
                    playerName,
                    balance,
                    lastLoginDate
            );
        }
    }

    @Override
    protected DetailPresenter onCreatePresenter() {
        return new DetailPresenter(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.detail_activity;
    }

    @Override
    public void onUpdateViews(String gameName) {
        showToast(gameName);
    }
}
