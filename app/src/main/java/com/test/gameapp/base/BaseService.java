package com.test.gameapp.base;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by Android Developer on 4/13/2017.
 */

public abstract class BaseService {

    protected <F> void subscribe(Observable<F> observable, Observer<F> observer) {
        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .subscribe(observer);
    }

    protected <O1, O2, R> Observable<R> zip(Observable<O1> observable1, Observable<O2> observable2, Func2<O1, O2, R> observer) {
        return Observable.zip(observable1, observable2, observer);
    }
}
