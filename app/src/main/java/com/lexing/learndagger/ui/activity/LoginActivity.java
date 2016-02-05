package com.lexing.learndagger.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.lexing.learndagger.R;
import com.lexing.learndagger.component.AppComponent;
import com.lexing.learndagger.component.DaggerLoginComponent;
import com.lexing.learndagger.domain.UserDataManager;
import com.lexing.learndagger.module.LoginModule;
import com.lexing.learndagger.ui.present.LoginPresenter;
import com.lexing.learndagger.ui.present.LoginView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class LoginActivity extends BaseActivity implements LoginView {
    private static final String TAG = "LoginActivity";
    @Bind(R.id.etName)
    EditText mEtName;
    @Bind(R.id.etPass)
    EditText mEtPass;
    @Bind(R.id.btnLogin)
    Button mBtnLogin;
    @Bind(R.id.loadProgress)
    ProgressBar mLoadProgress;
    @Bind(R.id.layoutLogin)
    LinearLayout mLayoutLogin;
    @Inject
    LoginPresenter mPresenter;
    @Inject
    UserDataManager mUserDataManager;
    private CompositeSubscription mSubscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mSubscription = new CompositeSubscription();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.checkButtonStatu();
        mSubscription.add(RxTextView.textChanges(mEtName).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                mPresenter.setUserName(charSequence);
                mPresenter.checkButtonStatu();
            }
        }));
        mSubscription.add(RxTextView.textChanges(mEtPass).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                mPresenter.setPassWord(charSequence);
                mPresenter.checkButtonStatu();
            }
        }));
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
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void enableLoginButton(boolean enable) {
        mBtnLogin.setEnabled(enable);
    }

    @Override
    public void showProgress() {
        mLoadProgress.setVisibility(View.VISIBLE);
        mLayoutLogin.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        mLoadProgress.setVisibility(View.GONE);
        mLayoutLogin.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnLogin)
    public void login() {
        mPresenter.onLoginClick();
    }

    @Override
    public void showToast(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
