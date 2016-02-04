package com.lexing.learndagger.component;

import android.content.Context;

import com.lexing.learndagger.App;
import com.lexing.learndagger.domain.GitManager;
import com.lexing.learndagger.domain.UserDataManager;
import com.lexing.learndagger.module.AppModule;
import com.lexing.learndagger.module.DomainModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/3
 * TIME : 16:20
 */
@Singleton
@Component(
        modules = {
                DomainModule.class
        }
)
public interface AppComponent {
    void inject(App app);

    UserDataManager getUserDataManager();

    GitManager getGitManager();
}
