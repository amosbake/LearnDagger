package com.lexing.learndagger.ui.present;

import android.text.TextUtils;
import android.util.Log;

import com.lexing.learndagger.domain.GitManager;
import com.lexing.learndagger.domain.UserDataManager;
import com.lexing.learndagger.models.GitUser;

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
    private GitManager mGitManager;
    private CharSequence userName;
    private CharSequence passWord;

    public LoginPresenterImpl(LoginView loginView, UserDataManager userDataManager, GitManager gitManager) {
        mLoginView = loginView;
        mUserDataManager = userDataManager;
        mGitManager = gitManager;
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
                        loginGithub();
                        mLoginView.showToast(aBoolean ? "登录成功" : "登录失败");
                    }
                });
    }

    @Override
    public void loginGithub() {
        mGitManager.findUser("amosbake")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GitUser>() {
                    @Override
                    public void call(GitUser gitUser) {
                        mLoginView.hideProgress();
                        mLoginView.showInfo(gitUser.getNickName(), gitUser.getAvatarUrl());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mLoginView.hideProgress();
                        Log.e(TAG, "登录失败: ", throwable);
                    }
                });
    }
}
