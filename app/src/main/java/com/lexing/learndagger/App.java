package com.lexing.learndagger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.lexing.learndagger.component.AppComponent;
import com.lexing.learndagger.component.DaggerAppComponent;
import com.lexing.learndagger.domain.Analyticsmanager;
import com.lexing.learndagger.domain.UserDataManager;
import com.lexing.learndagger.module.AppModule;
import com.lexing.learndagger.module.DomainModule;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/3
 * TIME : 10:50
 */
public class App extends Application {
    private static final String TAG = "App";
    @Inject
    Analyticsmanager mAnalyticsmanager;
    @Inject
    UserDataManager mUserDataManager;

    private AppComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setUpAppCompenont();//手动注入依赖
        mAnalyticsmanager.registerAppEnter();//第三方初始化
        preRegisterUser();//预先注册用户
    }

    private void preRegisterUser() {
        final SharedPreferences loginSp = getSharedPreferences("login", MODE_PRIVATE);
        if (loginSp.getBoolean("First", true)){
            mUserDataManager.register("default","123456")
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean aBoolean) {
                            if (aBoolean){
                                Log.i(TAG, "first enter register default");
                                loginSp.edit().putBoolean("First",false);
                            }
                        }
                    });
        }
    }

    private void setUpAppCompenont() {
        mComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .domainModule(new DomainModule())
                .build();
        mComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return mComponent;
    }

    public static App get(Context context){
        return (App) context.getApplicationContext();
    }
}
