package com.example.cc.loginandregistdemo.Login;

/**
 * Created by cc on 2018/1/1.
 */

public interface LoginInteractor {
    interface OnLoginFinishedListener {
        void onUsernameError();

        void onPasswordError();

        boolean isCorrect(String username, String password);

        void onSuccess();
    }


    void login(String username, String password, OnLoginFinishedListener listener);
}
