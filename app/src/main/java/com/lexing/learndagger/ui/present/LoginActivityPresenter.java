package com.lexing.learndagger.ui.present;

import android.text.TextUtils;
import android.util.Log;

import com.lexing.learndagger.ui.activity.LoginActivity;
import com.lexing.learndagger.manager.UserDataManager;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/3
 * TIME : 10:39
 */
public class LoginActivityPresenter {
    private static final String TAG = "LoginActivityPresenter";
    private LoginActivity mLoginActivity;
    private UserDataManager mUserDataManager;
    public CharSequence userName;
    private CharSequence passWord;

    public LoginActivityPresenter(LoginActivity loginActivity) {
        mLoginActivity = loginActivity;
    }

    public void setUserName(CharSequence userName) {
        Log.i(TAG, "setUserName: " + userName);
        this.userName = userName;
    }

    public void setPassWord(CharSequence passWord) {
        Log.i(TAG, "setPassWord: " + passWord);
        this.passWord = passWord;
    }

    public void onLoginClick() {
        Log.i(TAG, "onLoginClick: ");
    }
}
