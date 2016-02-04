package com.lexing.learndagger.module;

import com.lexing.learndagger.App;
import com.lexing.learndagger.domain.UserDataManager;
import com.lexing.learndagger.domain.UserManager_Sp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/3
 * TIME : 16:10
 */
@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public App provideApplication() {
        return this.application;
    }

}
