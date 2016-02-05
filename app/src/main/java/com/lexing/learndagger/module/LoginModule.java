package com.lexing.learndagger.module;

import com.lexing.learndagger.domain.GitManager;
import com.lexing.learndagger.domain.UserDataManager;
import com.lexing.learndagger.ui.ActivityScope;
import com.lexing.learndagger.ui.present.LoginPresenter;
import com.lexing.learndagger.ui.present.LoginPresenterImpl;
import com.lexing.learndagger.ui.present.LoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/3
 * TIME : 17:59
 */
@Module
public class LoginModule {
    private LoginView mLoginView;

    public LoginModule(LoginView loginView) {
        this.mLoginView = loginView;
    }

    @Provides
    LoginPresenter provideLoginActivityPresenter(UserDataManager manager,GitManager gitManager) {
        return new LoginPresenterImpl(mLoginView, manager,gitManager);
    }
}
