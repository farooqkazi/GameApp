package com.test.gameapp.base;

import com.test.gameapp.presentation.BaseView;
import com.test.gameapp.presenter.IBasicLifecycle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Android Developer on 4/13/2017.
 */

public abstract class BasePresenter<V extends BaseView> implements IBasicLifecycle {

    private final V mView;

    public BasePresenter(V view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Object event) {/* Do something */};

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
    }

    protected V getView() {
        return mView;
    }
}
