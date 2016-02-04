package com.lexing.learndagger.domain;

import com.lexing.learndagger.api.GitHubApi;
import com.lexing.learndagger.models.GitUser;

import javax.inject.Inject;

import rx.Observable;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/4
 * TIME : 11:45
 */
public class GitManagerImp implements GitManager {
    GitHubApi mApi;

    public GitManagerImp(GitHubApi api) {
        mApi = api;
    }

    @Override
    public Observable<GitUser> findUser(String gitName) {
        return mApi.fetchUserInfo(gitName);
    }
}
