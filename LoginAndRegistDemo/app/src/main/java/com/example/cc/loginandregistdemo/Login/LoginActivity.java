package com.example.cc.loginandregistdemo.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cc.loginandregistdemo.DatabaseHelper;
import com.example.cc.loginandregistdemo.R;
import com.example.cc.loginandregistdemo.MainActivity;
import com.example.cc.loginandregistdemo.Register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private Button loginBtn;
    private Button registerBtn;
    private LoginPresenter presenter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this, "Key.db",
                null, 1);
        databaseHelper.getWritableDatabase();
        progressBar = (ProgressBar) findViewById(R.id.login_progress);
        username = (EditText) findViewById(R.id.login_account);
        password = (EditText) findViewById(R.id.login_password);
        loginBtn = (Button) findViewById(R.id.login);
        registerBtn = (Button) findViewById(R.id.login_register);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

        presenter = new LoginPresenterImpl(this, new LoginInteractorImpl());
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        username.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void navigateToRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public boolean isRight(String username, String password) {
//        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
//        if(pref.getString(username,"").equals(password)){
//            return true;
//        }else {
//            return false;
//        }

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query("Key",
                new String[]{"password"},
                "username=?",
                new String[]{username}, null, null, null);
        if (cursor.moveToFirst()) {
            String truePassword = cursor.getString(0);
            if (truePassword.equals(password)) {
                cursor.close();
                return true;
            } else {
                cursor.close();
                return false;
            }
        }
        cursor.close();
        return false;
    }

    @Override
    public void setLoginError() {
        Toast.makeText(this, "Account or Password Error :(",
                Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                presenter.validateCredentials(username.getText().toString(),
                        password.getText().toString());
                break;
            case R.id.login_register:
                presenter.clickRegister();
                break;
            default:
                break;
        }
    }
}
