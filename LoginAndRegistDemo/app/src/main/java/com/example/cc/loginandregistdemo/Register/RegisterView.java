package com.example.cc.loginandregistdemo.Register;

/**
 * Created by cc on 2018/1/1.
 */

public interface RegisterView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void notSamePassword();

    void registerSuccess();

    void navigateToLogin();

    boolean saveData(String username, String password);
}
