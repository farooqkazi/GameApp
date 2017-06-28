package com.test.gameapp.presentation;

import com.test.gameapp.model.GameData;

import java.util.List;

/**
 * Created by Android Developer on 4/13/2017.
 */

public interface MainView extends BaseView {

    void onLoadError(String message);

    void onLoadSuccess(List<GameData> gameData, String avatarLink);
}
