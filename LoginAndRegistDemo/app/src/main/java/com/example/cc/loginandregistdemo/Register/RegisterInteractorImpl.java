package com.example.cc.loginandregistdemo.Register;

import android.text.TextUtils;

/**
 * Created by cc on 2018/1/1.
 */

public class RegisterInteractorImpl implements RegisterInteractor {
    @Override
    public void register(final String username, final String password, final String password2,
                         final OnRegisterFinishedListener listener) {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isUsername(username)) {
                    listener.onUsernameError();
                    return;
                }
                if (password.length() < 6) {
                    listener.onPasswordError();
                    return;
                }
                if (!password.equals(password2)) {
                    listener.notSamePasswordError();
                    return;
                }
                if (!listener.saveData(username, password)) {
                    return;
                }
                listener.onSuccess();
            }
        }, 2000);
    }

    private boolean isUsername(String username) {
        String telRegex = "[A-Za-z][A-Za-z0-9_]+";
        if (TextUtils.isEmpty(username))
            return false;
        else
            return username.matches(telRegex);
    }
}
