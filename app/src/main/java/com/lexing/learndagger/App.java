package com.lexing.learndagger;

import android.app.Application;

import com.lexing.learndagger.component.DaggerAppComponent;
import com.lexing.learndagger.domain.Analyticsmanager;
import com.lexing.learndagger.module.AppModule;
import com.lexing.learndagger.module.DomainModule;

import javax.inject.Inject;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/3
 * TIME : 10:50
 */
public class App extends Application {
    @Inject
    Analyticsmanager mAnalyticsmanager;

    @Override
    public void onCreate() {
        super.onCreate();
        setUpAppCompenont();
        mAnalyticsmanager.registerAppEnter();
    }

    private void setUpAppCompenont() {
        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .domainModule(new DomainModule())
                .build().inject(this);
    }

}
