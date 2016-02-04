package com.lexing.learndagger.domain;

import com.lexing.learndagger.models.GitUser;

import rx.Observable;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/4
 * TIME : 11:44
 */
public interface GitManager {
   public Observable<GitUser> findUser(String gitName);
}
