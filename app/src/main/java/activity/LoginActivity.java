package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmarket.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private TextView tvRegister;
    private CheckBox cbRememberMe;
    private Button btnLogin;
    private SharedPreferences userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        initViews();
        initEvents();
    }

    private void initEvents() {
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                BmobUser bmobUser = new BmobUser();
                bmobUser.setPassword(password);
                bmobUser.setUsername(username);
                bmobUser.login(new SaveListener<BmobUser>() {

                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if (e == null){
                            if (cbRememberMe.isChecked()){
                                SharedPreferences.Editor editor = userInfo.edit();
                                editor.putBoolean("isRememberMe", true);
                                editor.putString("username", username);
                                editor.putString("password", password);
                                editor.commit();
                            }else{
                                SharedPreferences.Editor editor = userInfo.edit();
                                editor.putBoolean("isRememberMe", false);
                                editor.putString("username", null);
                                editor.putString("password", null);
                                editor.commit();
                            }

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("username", username);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initViews() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        tvRegister = findViewById(R.id.tv_register);
        cbRememberMe = findViewById(R.id.cb_remeber_me);
        btnLogin = findViewById(R.id.btn_login);

        boolean isRemember = userInfo.getBoolean("isRemember", false);
        if (isRemember){
            etUsername.setText(userInfo.getString("username",""));
            etPassword.setText(userInfo.getString("password",""));
            cbRememberMe.setChecked(true);
        }
    }
}