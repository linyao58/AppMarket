package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appmarket.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        initEvents();
    }

    private void initEvents() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                BmobUser bmobUser = new BmobUser();
                bmobUser.setUsername(username);
                bmobUser.setPassword(password);
                bmobUser.signUp(new SaveListener<BmobUser>() {

                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if (e == null){
                            Toast.makeText(RegisterActivity.this, "注册成功！请登录！", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(RegisterActivity.this, "注册失败！" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    private void initViews() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnRegister = findViewById(R.id.btn_register);
    }
}