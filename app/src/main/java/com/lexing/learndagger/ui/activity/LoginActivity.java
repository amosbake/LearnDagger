package com.lexing.learndagger.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.lexing.learndagger.R;
import com.lexing.learndagger.domain.UserDataManager;
import com.lexing.learndagger.ui.present.LoginActivityPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    @Bind(R.id.etName)
    EditText mEtName;
    @Bind(R.id.etPass)
    EditText mEtPass;
    @Bind(R.id.btnLogin)
    Button mBtnLogin;
//    @Inject
//    LoginActivityPresenter mPresenter;
//    @Inject
//    UserDataManager mUserDataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void setupActivityComponent() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @OnClick(R.id.btnLogin)
    public void onClick() {

    }


}
