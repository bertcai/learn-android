package com.example.cc.loginandregistdemo.Login;

/**
 * Created by cc on 2018/1/1.
 */

public interface LoginPresenter {
    void validateCredentials(String username, String password);

    void onDestroy();

    void clickRegister();
}
