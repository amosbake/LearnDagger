package com.lexing.learndagger.application;

import android.app.Application;
import android.content.Context;

import com.lexing.learndagger.manager.UserDataManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/3
 * TIME : 16:20
 */
public interface AppContextComponent {
    CustomerAppication application();
    Context applcationContext();
    UserDataManager userManager();
}
