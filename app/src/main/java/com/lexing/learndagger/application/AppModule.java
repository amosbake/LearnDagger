package com.lexing.learndagger.application;

import android.app.Application;

import com.lexing.learndagger.manager.UserDataManager;
import com.lexing.learndagger.manager.UserManager_Sp;
import com.lexing.learndagger.ui.ActivityScope;

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
    private final CustomerAppication application;

    public AppModule(CustomerAppication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public CustomerAppication provideApplication() {
        return this.application;
    }

    @Provides
    @Singleton
    public UserDataManager provideUserDataManager() {
        return new UserManager_Sp(application);
    }
}
