package com.lexing.learndagger.api;

import com.lexing.learndagger.models.GitUser;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/4
 * TIME : 11:11
 */
public interface GitHubApi {
    @GET("/users/{user}")
    Observable<GitUser> fetchUserInfo(@Path("user") String loginName);
}
