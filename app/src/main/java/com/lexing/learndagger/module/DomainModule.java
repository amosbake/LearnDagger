package com.lexing.learndagger.module;

import android.app.Application;

import com.lexing.learndagger.App;
import com.lexing.learndagger.adapter.RestApiAdapter;
import com.lexing.learndagger.api.GitHubApi;
import com.lexing.learndagger.domain.Analyticsmanager;
import com.lexing.learndagger.domain.GitManager;
import com.lexing.learndagger.domain.GitManagerImp;
import com.lexing.learndagger.domain.UserDataManager;
import com.lexing.learndagger.domain.UserManager_Sp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/4
 * TIME : 11:37
 */
@Module(includes = AppModule.class)
public class DomainModule {

    @Provides
    @Singleton
    public Analyticsmanager provideAnalyticsmanager(App app) {
        return new Analyticsmanager(app);
    }

    @Provides
    @Singleton
    public UserDataManager provideUserDatamanager(App app) {
        return new UserManager_Sp(app);
    }

    @Provides
    public GitHubApi provideGitApi() {
        return RestApiAdapter.getClient();
    }

    @Provides
    public GitManager provideGitManager(GitHubApi api) {
        return new GitManagerImp(api);
    }
}
