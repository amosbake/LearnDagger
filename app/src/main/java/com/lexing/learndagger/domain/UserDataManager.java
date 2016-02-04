package com.lexing.learndagger.domain;

import rx.Observable;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/3
 * TIME : 10:43
 */
public interface UserDataManager {
    Observable<Boolean> login(String userName, String password);
    Observable<Boolean> register(String userName, String password);
}
