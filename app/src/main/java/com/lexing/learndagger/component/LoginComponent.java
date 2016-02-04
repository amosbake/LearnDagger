package com.lexing.learndagger.component;

import com.lexing.learndagger.module.DomainModule;
import com.lexing.learndagger.module.LoginModule;
import com.lexing.learndagger.ui.ActivityScope;
import com.lexing.learndagger.ui.present.LoginPresenter;
import com.lexing.learndagger.ui.present.LoginView;

import dagger.Component;
import dagger.Provides;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/3
 * TIME : 18:13
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = LoginModule.class
)
public interface LoginComponent {
    void inject(LoginView loginView);
    LoginPresenter getLoginPresenter();
}
