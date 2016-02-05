package com.lexing.learndagger.ui.present;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/4
 * TIME : 16:12
 */
public interface LoginPresenter {
    void setUserName(CharSequence userName);
    void setPassWord(CharSequence passWord);
    void checkButtonStatu();
    void onLoginClick();
    void loginGithub();
}
