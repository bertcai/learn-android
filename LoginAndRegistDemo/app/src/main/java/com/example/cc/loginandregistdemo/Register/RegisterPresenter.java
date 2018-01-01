package com.example.cc.loginandregistdemo.Register;

/**
 * Created by cc on 2018/1/1.
 */

public interface RegisterPresenter {
    void validateCredentials(String username, String password, String password2);

    void onDestroy();
}
