package com.example.cc.loginandregistdemo.Login;

import android.os.Handler;
import android.text.TextUtils;

/**
 * Created by cc on 2018/1/1.
 */

public class LoginInteractorImpl implements LoginInteractor {
    @Override
    public void login(final String username, final String password,
                      final OnLoginFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
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
                if (!listener.isCorrect(username, password)) {
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
