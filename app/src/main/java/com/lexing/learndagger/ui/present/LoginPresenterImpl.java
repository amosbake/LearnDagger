package com.lexing.learndagger.ui.present;

import android.util.Log;

import com.lexing.learndagger.domain.UserDataManager;

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
    public void onLoginClick() {
        Log.i(TAG, "onLoginClick: ");
    }
}
