package com.rainhowchan.nestscroll;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/17.
 */
public class MyNestedScrollView extends AppCompatActivity {

    @Bind(R.id.backdrop)
    ImageView backdrop;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.usernameWrapper)
    TextInputLayout usernameWrapper;
    @Bind(R.id.passwordWrapper)
    TextInputLayout passwordWrapper;
    private EditText passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynestedscrollview);
        ButterKnife.bind(this);
        usernameWrapper.setHint("username");
        passwordWrapper.setHint("password");
        initToolBar();

        passwordEditText = passwordWrapper.getEditText();
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>3&&s.length()<6) {
                    passwordWrapper.setErrorEnabled(true);
                    passwordWrapper.setError("用户名不得少于6个字符");
                } else if (s.length() >= 6) {
                    passwordWrapper.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbar.setTitle("RainhowChan");

    }

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password) {
        return password.length() > 5;
    }

    @OnClick(R.id.bt)
    public void onClick() {
        hideKeyBoard();
        String email = usernameWrapper.getEditText().getText().toString();

        String password = passwordEditText.getText().toString();
        if (!validateEmail(email)) {
            usernameWrapper.setError("Not a correct email!");
        } else if (!validatePassword(password)) {
            usernameWrapper.setErrorEnabled(false);
            passwordWrapper.setError("Not a correct password!");
        } else {
            passwordWrapper.setErrorEnabled(false);
            usernameWrapper.setErrorEnabled(false);
            Toast.makeText(MyNestedScrollView.this, "验证通过!", Toast.LENGTH_SHORT).show();
        }
    }

    private void hideKeyBoard() {
        View view = getCurrentFocus();
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }


    }
}
