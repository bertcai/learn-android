package com.example.cc.loginandregistdemo.Register;

/**
 * Created by cc on 2018/1/1.
 */

public interface RegisterInteractor {
    interface OnRegisterFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onSuccess();

        boolean saveData(String username, String password);

        void notSamePasswordError();
    }

    void register(String username, String password, String password2,
                  OnRegisterFinishedListener listener);
}
