package dientcph27512.fpoly.asm_mob201_dientcph27512.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import dientcph27512.fpoly.asm_mob201_dientcph27512.R;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Services.DangKiService;

public class DangKiActivity extends AppCompatActivity {
    private EditText userDk;
    private EditText passDk1;
    private ImageView showPassMoi2;
    private EditText passMoi2;
    private ImageView showPass2;
    private Button doimatkhau;
    private DangKiService dangKiService;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DangKiService.CreateAcoutBinder binder = (DangKiService.CreateAcoutBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        userDk = (EditText) findViewById(R.id.user_dk);
        passDk1 = (EditText) findViewById(R.id.pass_dk1);
        showPassMoi2 = (ImageView) findViewById(R.id.showPassMoi2);
        passMoi2 = (EditText) findViewById(R.id.passMoi2);
        showPass2 = (ImageView) findViewById(R.id.showPass2);
        doimatkhau = (Button) findViewById(R.id.doimatkhau);
        dangKiService = new DangKiService();
        Intent intent = new Intent(DangKiActivity.this,DangKiService.class);
        showPass();
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
        doimatkhau.setOnClickListener(v -> {
            if(!"".equals(userDk.getText().toString().trim()) && passDk1.getText().toString().equals(passMoi2.getText().toString())){
                dangKiService.creatAcount(userDk.getText().toString(),passDk1.getText().toString(),"/storage/emulated/0/Android/data/dientcph27512.fpoly.asm_mob201_dientcph27512/files/Pictures/JPEG_20221018_165238_4236784854427856885.jpg",DangKiActivity.this);
                Toast.makeText(this, "Đã Đăng Kí", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this, "Đăng Kí Thất Bại", Toast.LENGTH_SHORT).show();
                userDk.setText("");
                passDk1.setText("");
                passMoi2.setText("");
            }
        });
    }
    public void showPass() {
        showPassMoi2.setOnClickListener(v -> {
            if (passDk1.getInputType() == 129) {
                passDk1.setInputType(1);
            } else {
                passDk1.setInputType(129);
            }
        });
        showPass2.setOnClickListener(v -> {
            if (passMoi2.getInputType() == 129) {
                passMoi2.setInputType(1);
            } else {
                passMoi2.setInputType(129);
            }
        });
    }
}