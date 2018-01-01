package com.example.cc.loginandregistdemo.Register;

import android.widget.Toast;

import com.example.cc.loginandregistdemo.Login.LoginActivity;

/**
 * Created by cc on 2018/1/1.
 */

public class RegisterPresenterImpl implements RegisterPresenter,
        RegisterInteractor.OnRegisterFinishedListener {
    private RegisterView registerView;
    private RegisterInteractor registerInteractor;

    public RegisterPresenterImpl(RegisterView registerView, RegisterInteractorImpl registerInteractor) {
        this.registerView = registerView;
        this.registerInteractor = registerInteractor;
    }

    @Override
    public void validateCredentials(String username, String password, String password2) {
        if (registerView != null) {
            registerView.showProgress();
        }
        registerInteractor.register(username, password, password2, this);
    }

    @Override
    public void onDestroy() {
        registerView = null;
    }

    @Override
    public void onUsernameError() {
        if (registerView != null) {
            registerView.setUsernameError();
            registerView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (registerView != null) {
            registerView.setPasswordError();
            registerView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (registerView != null) {
            registerView.registerSuccess();
            registerView.navigateToLogin();
        }
    }

    @Override
    public void notSamePasswordError() {
        if (registerView != null) {
            registerView.notSamePassword();
            registerView.hideProgress();
        }
    }

    @Override
    public boolean saveData(String username, String password) {
        return registerView.saveData(username, password);
    }
}
