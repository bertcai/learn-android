package com.example.cc.loginandregistdemo.Login;

/**
 * Created by cc on 2018/1/1.
 */

public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();

    void navigateToRegister();

    boolean isRight(String username, String password);

    void setLoginError();
}
