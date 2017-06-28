package com.test.gameapp.service;

import com.test.gameapp.model.GameDataResponse;
import com.test.gameapp.model.PlayerInfo;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Android Developer on 4/13/2017.
 */

public interface ApiNetworkData {

    String BASE_URL = "https://dl.dropboxusercontent.com";

    @GET("/s/2ewt6r22zo4qwgx/gameData.json")
    Observable<GameDataResponse> getGameData();

    @GET("/s/5zz3hibrxpspoe5/playerInfo.json")
    Observable<PlayerInfo> getPlayerInfo();
}
