package com.test.gameapp.presenter;

import com.test.gameapp.base.BasePresenter;
import com.test.gameapp.presentation.DetailView;

/**
 * Created by Android Developer on 4/14/2017.
 */

public class DetailPresenter extends BasePresenter<DetailView> {

    public DetailPresenter(DetailView view) {
        super(view);
    }

    public void onHandleDetails(
            String gameName,
            String jackPot,
            String date,
            String playerName,
            long balance,
            String lastLoginDate) {
        getView().onUpdateViews(gameName);
    }
}
