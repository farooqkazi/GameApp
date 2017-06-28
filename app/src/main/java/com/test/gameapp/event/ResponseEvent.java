package com.test.gameapp.event;

import com.test.gameapp.model.GameData;

import java.util.List;

/**
 * Created by Android Developer on 4/13/2017.
 */

public class ResponseEvent {

    private List<GameData> gameData;
    private String avatarLink;
    private long balance;
    private String lastLogindate;
    private String playerName;

    public void setGameData(List<GameData> gameData) {
        this.gameData = gameData;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void setLastLogindate(String lastLogindate) {
        this.lastLogindate = lastLogindate;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<GameData> getGameData() {
        return gameData;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public long getBalance() {
        return balance;
    }

    public String getLastLogindate() {
        return lastLogindate;
    }

    public String getPlayerName() {
        return playerName;
    }
}
