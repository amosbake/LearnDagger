package com.lexing.learndagger.ui.present;

import android.text.TextUtils;
import android.util.Log;

import com.lexing.learndagger.domain.UserDataManager;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/3
 * TIME : 10:39
 */
public class LoginPresenterImpl implements LoginPresenter {
    private static final String TAG = "LoginPresenterImpl";
    private LoginView mLoginView;
    private UserDataManager mUserDataManager;
    private CharSequence userName;
    private CharSequence passWord;

    public LoginPresenterImpl(LoginView loginView, UserDataManager userDataManager) {
        mLoginView = loginView;
        mUserDataManager = userDataManager;
    }

    @Override
    public void setUserName(CharSequence userName) {
        Log.i(TAG, "setUserName: " + userName);
        this.userName = userName;
    }

    @Override
    public void setPassWord(CharSequence passWord) {
        Log.i(TAG, "setPassWord: " + passWord);
        this.passWord = passWord;
    }

    @Override
    public void checkButtonStatu() {
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(passWord)) {
            mLoginView.enableLoginButton(true);
        } else {
            mLoginView.enableLoginButton(false);
        }
    }

    @Override
    public void onLoginClick() {
        mLoginView.showProgress();
        mUserDataManager.login(userName.toString(), passWord.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        mLoginView.hideProgress();
                        mLoginView.showToast(aBoolean ? "登录成功" : "登录失败");
                    }
                });
    }
}
