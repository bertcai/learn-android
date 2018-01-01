package com.example.cc.loginandregistdemo.Register;

import android.content.ContentValues;
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
import com.example.cc.loginandregistdemo.Login.LoginActivity;
import com.example.cc.loginandregistdemo.R;


public class RegisterActivity extends AppCompatActivity implements
        RegisterView, View.OnClickListener {

    private DatabaseHelper databaseHelper;
    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private EditText password2;
    private Button registerBtn;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe);


        databaseHelper = new DatabaseHelper(this, "Key.db",
                null, 1);
        databaseHelper.getWritableDatabase();
        progressBar = (ProgressBar) findViewById(R.id.register_progress);
        username = (EditText) findViewById(R.id.register_account);
        password = (EditText) findViewById(R.id.register_password);
        password2 = (EditText) findViewById(R.id.register_password_2);
        registerBtn = (Button) findViewById(R.id.register);
        registerBtn.setOnClickListener(this);

        presenter = new RegisterPresenterImpl(this, new RegisterInteractorImpl());
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
    public void navigateToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void notSamePassword() {
        Toast.makeText(this, "The tow password is not same :(",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void registerSuccess() {
        Toast.makeText(this, "Register Success : )",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean saveData(String username, String password) {
//        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
//        editor.putString(username, password);
//        editor.apply();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query("Key",
                new String[]{"username"},
                "username=?",
                new String[]{username}, null, null, null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(0).equals(username)) {
                Toast.makeText(this, "The username has been registered:(",
                        Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                cursor.close();
                return false;
            }
        }
        cursor.close();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        db.insert("Key", null, values);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                presenter.validateCredentials(username.getText().toString(),
                        password.getText().toString(),
                        password2.getText().toString());
            default:
                break;
        }
    }
}
