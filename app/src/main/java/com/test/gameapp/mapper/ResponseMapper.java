package com.test.gameapp.mapper;

import com.test.gameapp.event.ResponseEvent;
import com.test.gameapp.model.GameDataResponse;
import com.test.gameapp.model.PlayerInfo;

/**
 * Created by Android Developer on 4/13/2017.
 */

public class ResponseMapper {

    public ResponseEvent mapData(GameDataResponse gameDataResponse, PlayerInfo playerInfo) {
        ResponseEvent event = new ResponseEvent();
        event.setGameData(gameDataResponse.getData());
        event.setAvatarLink(playerInfo.getAvatarLink());
        event.setBalance(playerInfo.getBalance());
        event.setLastLogindate(playerInfo.getLastLogindate());
        event.setPlayerName(playerInfo.getName());
        return event;
    }
}
