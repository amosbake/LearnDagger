package com.lexing.learndagger.component;

import com.lexing.learndagger.ui.ActivityScope;
import com.lexing.learndagger.ui.activity.LoginActivity;

import dagger.Component;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/3
 * TIME : 18:13
 */
@ActivityScope
public interface LoginActivityComponent {
    LoginActivity inject(LoginActivity loginActivity);
}
