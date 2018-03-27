package com.jaimes.nelson.chatedx.login.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jaimes.nelson.chatedx.R;
import com.jaimes.nelson.chatedx.login.LoginPresenter;
import com.jaimes.nelson.chatedx.login.LoginPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter loginPresenter;
    @BindView(R.id.editTxtEmail)
    EditText edtEmail;
    @BindView(R.id.editTxtPassword)
    EditText edtPassword;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.btnSignin)
    Button btnSignIn;
    @BindView(R.id.btnSignup)
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.onCreate();
        loginPresenter.checkAuthenticatedUser();
    }

    @OnClick(R.id.btnSignup)
    void signUp() {

    }

    @OnClick(R.id.btnSignin)
    void signIn() {

    }

    @Override
    public void showUser(String message) {
        Toast.makeText(this, "Message:" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void enableInputs() {
        setInput(true);
    }

    @Override
    public void disableInputs() {
        setInput(false);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    private void setInput(boolean state) {
        edtEmail.setEnabled(state);
        edtPassword.setEnabled(state);
        btnSignIn.setEnabled(state);
        btnSignUp.setEnabled(state);
    }
}
