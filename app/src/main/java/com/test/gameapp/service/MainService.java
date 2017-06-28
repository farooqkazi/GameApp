package com.test.gameapp.service;

import com.test.gameapp.base.BaseService;
import com.test.gameapp.event.ErrorEvent;
import com.test.gameapp.event.ResponseEvent;
import com.test.gameapp.mapper.ResponseMapper;
import com.test.gameapp.model.GameDataResponse;
import com.test.gameapp.model.PlayerInfo;

import org.greenrobot.eventbus.EventBus;

import rx.Observable;
import rx.Observer;
import rx.functions.Func2;

/**
 * Created by Android Developer on 4/13/2017.
 */

public class MainService extends BaseService {

    private final ApiNetworkData mApi;

    public MainService(ApiNetworkData apiNetworkData) {
        mApi = apiNetworkData;
    }

    public void onLoadGameList() {
        Observable<GameDataResponse> gameData = mApi.getGameData();
        Observable<PlayerInfo> playerInfo = mApi.getPlayerInfo();
        subscribe(zip(gameData, playerInfo, new Func2<GameDataResponse, PlayerInfo, ResponseEvent>() {
            @Override
            public ResponseEvent call(GameDataResponse gameDataResponse, PlayerInfo playerInfo) {
                return new ResponseMapper().mapData(gameDataResponse, playerInfo);
            }
        }), new Observer<ResponseEvent>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
            }

            @Override
            public void onNext(ResponseEvent responseEvent) {
                EventBus.getDefault().post(responseEvent);
            }
        });
    }
}
