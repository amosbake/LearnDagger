package com.lexing.learndagger.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.lexing.learndagger.R;
import com.lexing.learndagger.component.AppComponent;
import com.lexing.learndagger.component.DaggerLoginComponent;
import com.lexing.learndagger.domain.UserDataManager;
import com.lexing.learndagger.module.LoginModule;
import com.lexing.learndagger.ui.present.LoginPresenterImpl;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements com.lexing.learndagger.ui.present.LoginView {
    private static final String TAG = "LoginActivity";
    @Bind(R.id.etName)
    EditText mEtName;
    @Bind(R.id.etPass)
    EditText mEtPass;
    @Bind(R.id.btnLogin)
    Button mBtnLogin;
    @Inject
    LoginPresenterImpl mPresenter;
    @Inject
    UserDataManager mUserDataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void showProgress(){

    }

    @Override
    public void hideProgress(){

    }

    @OnClick(R.id.btnLogin)
    void checkInject(){
        Log.i(TAG, String.valueOf("checkInject: "+mPresenter==null));
        Log.i(TAG, String.valueOf("checkInject: "+mUserDataManager==null));
    }
}
