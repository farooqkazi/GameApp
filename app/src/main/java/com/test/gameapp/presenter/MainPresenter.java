package com.test.gameapp.presenter;

import android.support.annotation.NonNull;

import com.test.gameapp.base.BasePresenter;
import com.test.gameapp.event.ErrorEvent;
import com.test.gameapp.event.ResponseEvent;
import com.test.gameapp.presentation.MainView;
import com.test.gameapp.service.MainService;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Android Developer on 4/13/2017.
 */

public class MainPresenter extends BasePresenter<MainView> {

    private final MainService mService;
    private long mBalance;
    private String mPlayerName;
    private String mLastLogindate;

    public MainPresenter(MainView view, @NonNull MainService mainService) {
        super(view);
        mService = mainService;
    }

    public void onLoadGameList() {
        mService.onLoadGameList();
    }

    public long getBalance() {
        return mBalance;
    }

    public String getLastLogindate() {
        return mLastLogindate;
    }

    public String getPlayerName() {
        return mPlayerName;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ResponseEvent event) {
        MainView view = getView();
        if (view != null) {
            mBalance = event.getBalance();
            mLastLogindate = event.getLastLogindate();
            mPlayerName = event.getPlayerName();
            view.onLoadSuccess(event.getGameData(), event.getAvatarLink());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ErrorEvent event) {
        MainView view = getView();
        if (view != null) {
            view.onLoadError(event.getErrorMessage());
        }
    }
}
