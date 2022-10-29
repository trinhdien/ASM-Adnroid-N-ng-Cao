package dientcph27512.fpoly.asm_mob201_dientcph27512.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import dientcph27512.fpoly.asm_mob201_dientcph27512.R;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Services.CheckLoginService;

public class LoginActivity extends AppCompatActivity {
    private EditText user;
    private EditText pass;
    private ImageView showPass;
    private CheckBox luuPass;
    private Button dangNhap;
    private Button dangKi;
    private CheckLoginService checkLoginService;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CheckLoginService.CheckLoginBinder binder = (CheckLoginService.CheckLoginBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        showPass = (ImageView) findViewById(R.id.showPass);
        luuPass = (CheckBox) findViewById(R.id.luuPass);
        dangNhap = (Button) findViewById(R.id.dang_nhap);
        dangKi = (Button) findViewById(R.id.dang_ki);
        setTitle("Đăng Nhập");
        checkLoginService = new CheckLoginService();
        Intent intent = new Intent(LoginActivity.this, CheckLoginService.class);
        LoginActivity.this.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        showPass();
        dangNhap.setOnClickListener(v -> {
            String check = checkLoginService.checkLogin(user.getText().toString(), pass.getText().toString(),LoginActivity.this);
            if (check == null) {
                Toast.makeText(LoginActivity.this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                pass.setText("");
                user.setText("");
            }else {
                Toast.makeText(LoginActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(LoginActivity.this,MainActivity.class);
                intent1.putExtra("name",user.getText().toString());
                intent1.putExtra("avt",check);
                Log.d("zzzzz", "onCreate: " + check);
                startActivity(intent1);
                finish();
            }
        });
        dangKi.setOnClickListener(v -> {
            Intent intent1 = new Intent(this,DangKiActivity.class);
            startActivity(intent1);
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void showPass() {
        showPass.setOnClickListener(v -> {
            if (pass.getInputType() == 129) {
                pass.setInputType(1);
            } else {
                pass.setInputType(129);
            }
        });
    }
}