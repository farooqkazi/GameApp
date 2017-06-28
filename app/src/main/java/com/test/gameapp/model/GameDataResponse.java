package com.test.gameapp.model;

import java.util.List;

/**
 * Created by Android Developer on 4/13/2017.
 */

public class GameDataResponse {

    private String response;
    private String currency;
    private List<GameData> data;

    public String getResponse() {
        return response;
    }

    public String getCurrency() {
        return currency;
    }

    public List<GameData> getData() {
        return data;
    }
}
