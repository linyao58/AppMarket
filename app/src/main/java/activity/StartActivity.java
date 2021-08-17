package activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.appmarket.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {
    private final int PERMISSION_REQUEST_CODE = 200;
    private String[] DANGEROUS_PERMISSIONS = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE};
    private List<String> permissions = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        for (String permission : DANGEROUS_PERMISSIONS){
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                permissions.add(permission);
            }
        }

        if (!permissions.isEmpty()){
            ActivityCompat.requestPermissions(this, permissions.toArray(new String[permissions.size()]), PERMISSION_REQUEST_CODE);
        }else{
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        List<String> pers = new ArrayList<>();
        if (PERMISSION_REQUEST_CODE == requestCode){
            for (int i = 0 ; i < grantResults.length; i++){
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED){
                    pers.add(permissions[i]);
                }
            }
            if (!pers.isEmpty()){
                ActivityCompat.requestPermissions(this, pers.toArray(new String[pers.size()]), PERMISSION_REQUEST_CODE);

            }else{
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}