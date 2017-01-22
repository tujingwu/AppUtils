package com.example.apputil.appsplash.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.apputil.MainActivity;
import com.example.apputil.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tujingwu on 2016/11/15.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.btLogin)
    Button mLogin;
    @Bind(R.id.btRegister)
    Button mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    private void initListener() {
        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btLogin:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.btRegister:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }
}
