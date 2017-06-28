package com.test.gameapp.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.gameapp.R;
import com.test.gameapp.adapter.GameListAdapter;
import com.test.gameapp.base.BaseActivity;
import com.test.gameapp.model.GameData;
import com.test.gameapp.presentation.MainView;
import com.test.gameapp.presenter.MainPresenter;
import com.test.gameapp.service.ApiNetworkData;
import com.test.gameapp.service.MainService;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @BindView(R.id.gameList) protected RecyclerView mGameList;
    @BindView(R.id.avatarImage) protected ImageView mAvatarImage;
    @BindView(R.id.playerName) protected TextView mPlayerName;
    @BindView(R.id.playerBalance) protected TextView mPlayerBalance;
    @BindView(R.id.playerLastLoginDate) protected TextView mPlayerLastLoginDate;
    @BindView(R.id.headerLayout) protected LinearLayout mHeaderLayout;

    private GameListAdapter mListAdapter;

    @Override
    protected void onCreated(Bundle savedInstanceState) {
        super.onCreated(savedInstanceState);
        MainPresenter presenter = getPresenter();
        if (presenter != null) {
            showProgress("Loading games...");
            presenter.onLoadGameList();
        }
        mGameList.setHasFixedSize(true);
        mGameList.setLayoutManager(new LinearLayoutManager(this));
        mListAdapter = new GameListAdapter(getLayoutInflater());
        mListAdapter.setItemClickListener(new GameListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(GameData gameData) {
                showToast("Clicked Data -> " + gameData.getName());
                Bundle bundle = new Bundle();
                bundle.putString("game_name", gameData.getName());
                bundle.putString("jackpot", gameData.getJackpot());
                bundle.putString("date", gameData.getDate());
                MainPresenter mainPresenter = getPresenter();
                if (mainPresenter != null) {
                    bundle.putString("player_name", mainPresenter.getPlayerName());
                    bundle.putLong("balance", mainPresenter.getBalance());
                    bundle.putString("last_login_date", mainPresenter.getLastLogindate());
                }
                launchActivity(DetailActivity.class, bundle);
            }
        });
        mGameList.setAdapter(mListAdapter);
    }

    @Override
    protected MainPresenter onCreatePresenter() {
        return new MainPresenter(this, new MainService(new Retrofit.Builder()
                .baseUrl(ApiNetworkData.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient.Builder()
                        .retryOnConnectionFailure(true)
                        .cache(new Cache(getCacheDir(), 10 * 1024 * 1024))
                        .build())
                .build()
                .create(ApiNetworkData.class)));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onLoadError(String message) {
        showToast(message);
    }

    @Override
    public void onLoadSuccess(List<GameData> gameData, String avatarLink) {
        mListAdapter.setGameDataList(gameData);
        mHeaderLayout.setVisibility(View.VISIBLE);
        Picasso.with(this).load(avatarLink).into(mAvatarImage);
        MainPresenter presenter = getPresenter();
        mPlayerName.setText(String.format("Player Name: %s", presenter.getPlayerName()));
        mPlayerBalance.setText(String.format("Balance: %d", presenter.getBalance()));
        mPlayerLastLoginDate.setText(String.format(Locale.UK, "Last login date: %s", presenter.getLastLogindate()));
        hideProgress();
    }
}
