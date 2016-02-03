package com.lexing.learndagger.ui.component;

import com.lexing.learndagger.application.AppModule;
import com.lexing.learndagger.ui.ActivityScope;
import com.lexing.learndagger.ui.activity.LoginActivity;
import com.lexing.learndagger.ui.module.LoginActivityModule;

import dagger.Component;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/3
 * TIME : 18:13
 */
@ActivityScope
@Component(modules = {
        LoginActivityModule.class
})
public interface LoginActivityComponent {
    LoginActivity inject(LoginActivity loginActivity);
}
