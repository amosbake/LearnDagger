package com.lexing.learndagger.ui.present;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/4
 * TIME : 16:14
 */
public interface LoginView {
   void enableLoginButton(boolean enable);

    void showProgress();

    void hideProgress();

    void showToast(String msg);

    void showInfo(String nickName,String url);
}
