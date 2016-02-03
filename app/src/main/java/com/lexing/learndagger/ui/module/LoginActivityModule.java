package com.lexing.learndagger.ui.module;

import com.lexing.learndagger.ui.ActivityScope;
import com.lexing.learndagger.ui.activity.LoginActivity;
import com.lexing.learndagger.ui.present.LoginActivityPresenter;

import javax.inject.Scope;

import dagger.Module;
import dagger.Provides;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/3
 * TIME : 17:59
 */
@Module
public class LoginActivityModule {
    private LoginActivity loginActivity;

    public LoginActivityModule(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    @Provides
    @ActivityScope
    LoginActivity provideLoginActivity() {
        return loginActivity;
    }

    @Provides
    @ActivityScope
    LoginActivityPresenter provideLoginActivityPresenter() {
        return new LoginActivityPresenter(loginActivity);
    }
}
