package com.example.loginapp.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginapp.R;
import com.example.loginapp.data.LoginDataSource;
import com.example.loginapp.data.LoginRepository;
import com.example.loginapp.ui.login.LoginViewModel;
import com.example.loginapp.ui.login.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    // savedInstanceState: LoginActivity先前保存状态的 Bundle 对象。如果 Activity 此前未曾存在，
    // savedInstanceState对象的值为 null。
    // 如果您有一个生命周期感知型组件与您的 Activity 生命周期相关联，该组件将收到 ON_CREATE 事件。
    // 系统将调用带有 @OnLifecycleEvent 注释的方法，以使您的生命周期感知型组件可以执行已创建状态所需的
    // 任何设置代码。
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // 1.创建主界面
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 2.创建一个ViewModel(管理UI上的数据)
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);
        // 为什么不按以下方式调用
        // loginViewModel = new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));

        // 3.获取界面上的控件
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        // 4.LoginActivity在onCreate时观察ViewModel中的登录数据(getLoginFormState)变化状态
        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        // 5.LoginActivity在onCreate时观察ViewModel中的登录结果数据(getLoginResult)变化状态(onChanged)
        // 简而言之: 监听登录结果
        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                // View.GONE: This view is invisible, and it doesn't take any space for layout
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    showLoginSuccess(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        // 6.创建文本监听器，监听文本变化情况，定义具体的行为
        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            // 对文本变化之后这个行为做数据监听
            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        // 7.将监听器和控件进行绑定
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        // 8.监听用户在控件passwordEditText上的键盘输入行为是否结束,结束就进行登录操作
        // 点击IME键盘上的"完成"就直接登录。
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        // 9.为按钮增加监听事件
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
    }


    private void showLoginSuccess(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();

        // TODO: 登录成功可以跳转到新的Activity中去
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    // This callback is called only when there is a saved instance that is previously saved by using
    // onSaveInstanceState(). We restore some state in onCreate(), while we can optionally restore
    // other state here, possibly usable after onStart() has completed.
    // The savedInstanceState Bundle is same as the one used in onCreate().
    // 以下代码为状态的恢复代码，这次不使用，以后使用可以参考
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        textView.setText(savedInstanceState.getString(TEXT_VIEW_KEY));
//    }
//
//    // invoked when the activity may be temporarily destroyed, save the instance state here
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        outState.putString(GAME_STATE_KEY, gameState);
//        outState.putString(TEXT_VIEW_KEY, textView.getText());
//
//        // call superclass to save any view hierarchy
//        super.onSaveInstanceState(outState);
//    }
}