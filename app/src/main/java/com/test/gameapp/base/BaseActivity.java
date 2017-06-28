package com.test.gameapp.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.test.gameapp.presentation.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Android Developer on 4/13/2017.
 */

public abstract class BaseActivity<P extends BasePresenter<? extends BaseView>> extends AppCompatActivity {

    private P mPresenter;
    private Unbinder mBinder;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        mBinder = ButterKnife.bind(this);
        mPresenter = onCreatePresenter();
        if (mPresenter != null) {
            mPresenter.onCreate();
        }
        onCreated(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        mBinder.unbind();
        super.onDestroy();
    }

    @CallSuper
    protected void onCreated(Bundle savedInstanceState) {
    }

    protected void launchActivity(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void launchActivity(Class<?> clazz) {
        startActivity(new Intent(this, clazz));
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void showProgress(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setMessage(message);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.show();
    }

    protected void hideProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public P getPresenter() {
        return mPresenter;
    }

    protected void showBack() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected abstract P onCreatePresenter();

    protected abstract int getContentView();
}
