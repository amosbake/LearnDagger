package com.lexing.learndagger.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.lexing.learndagger.R;
import com.lexing.learndagger.manager.UserDataManager;
import com.lexing.learndagger.ui.component.DaggerLoginActivityComponent;
import com.lexing.learndagger.ui.component.LoginActivityComponent;
import com.lexing.learndagger.ui.module.LoginActivityModule;
import com.lexing.learndagger.ui.module.LoginActivityModule_ProvideLoginActivityFactory;
import com.lexing.learndagger.ui.present.LoginActivityPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    @Bind(R.id.etName)
    EditText mEtName;
    @Bind(R.id.etPass)
    EditText mEtPass;
    @Bind(R.id.btnLogin)
    Button mBtnLogin;
    @Inject
    LoginActivityPresenter mPresenter;

    UserDataManager mUserDataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerLoginActivityComponent.builder()
                .loginActivityModule(new LoginActivityModule(this))
                .build().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @OnClick(R.id.btnLogin)
    public void onClick() {
        mPresenter.onLoginClick();
//        mUserDataManager.login("amosbake", "123456").subscribe(new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                if (aBoolean) {
//                    Log.i(TAG, "call: " + "success");
//                } else {
//                    Log.i(TAG, "call: " + "fail");
//                }
//            }
//        });
    }


}
