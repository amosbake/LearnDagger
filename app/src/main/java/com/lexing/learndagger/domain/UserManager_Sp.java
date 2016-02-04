package com.lexing.learndagger.domain;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import rx.Observable;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/3
 * TIME : 10:49
 */
public class UserManager_Sp implements UserDataManager {
    private Application mContext;
    private SharedPreferences mPreferences;

    public UserManager_Sp(Application application) {
        mContext = application;
        mPreferences = mContext.getSharedPreferences("user", Context.MODE_PRIVATE);
    }

    @Override
    public Observable<Boolean> login(String userName, String password) {
        if (mPreferences.contains(userName)) {
            return Observable.just(password.equals(mPreferences.getString(userName, "")));
        } else {
            return Observable.just(false);
        }
    }

    @Override
    public Observable<Boolean> register(@NonNull String userName, @NonNull String password) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        return Observable.just(editor.putString(userName, password).commit());
    }
}
