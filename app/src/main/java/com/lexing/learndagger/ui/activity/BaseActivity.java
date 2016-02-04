package com.lexing.learndagger.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.lexing.learndagger.App;
import com.lexing.learndagger.component.AppComponent;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/3
 * TIME : 17:04
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(App.get(this).getAppComponent());
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);
}
