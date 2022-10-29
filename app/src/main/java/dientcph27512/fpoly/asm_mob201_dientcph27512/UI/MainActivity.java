package dientcph27512.fpoly.asm_mob201_dientcph27512.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import dientcph27512.fpoly.asm_mob201_dientcph27512.BaoFragment.DocBaoFragment;
import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.User;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Database.DatabaseUser;
import dientcph27512.fpoly.asm_mob201_dientcph27512.ManHinhChinh.HomeFragment;
import dientcph27512.fpoly.asm_mob201_dientcph27512.NhacFragment.NgheNhacFragment;
import dientcph27512.fpoly.asm_mob201_dientcph27512.QuanLiTaiKhoan.QuanLiTaiKhoanFragment;
import dientcph27512.fpoly.asm_mob201_dientcph27512.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private androidx.appcompat.widget.Toolbar toolbar;
    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private LinearLayout linearLayout;
    private String tenAcount;
    private ImageView avtAcuont;
    private TextView ten;
    private String avt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.manHinhChao);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        View header = navigationView.inflateHeaderView(R.layout.header_nav);
        ten = (TextView) header.findViewById(R.id.account);
        avtAcuont = (ImageView) header.findViewById(R.id.img_avt_main);
        tenAcount = getIntent().getStringExtra("name");
        avt = getIntent().getStringExtra("avt");
        Log.d("zzzzz", "onCreate: " + avt);
        if (tenAcount != null) {
            setPic(avt);
            ten.setText("Xin Chào: " + tenAcount);
            navigationView.getMenu().findItem(R.id.id_dangNhap).setVisible(false);
        } else {
            ten.setText("Xin Chào");
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                linearLayout.setVisibility(View.INVISIBLE);
            }
        }, 3000);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        replace(new HomeFragment());
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_home:
                replace(new HomeFragment());
                break;
            case R.id.id_docBao:
                replace(new DocBaoFragment());
                break;
            case R.id.id_nhac:
                replace(new NgheNhacFragment());
                break;
            case R.id.id_quanLi:
                if (tenAcount == null) {
                    Toast.makeText(this, "Bạn Cần Đăng Nhập Để Dùng Chức Năng Này", Toast.LENGTH_SHORT).show();
                } else {
                    replace(new QuanLiTaiKhoanFragment());
                }
                break;
            case R.id.id_dangNhap:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
//                finish();
                break;
            case R.id.id_thoat:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("Thoát?");
                builder1.setMessage("Bạn Có Muốn Thoát?");
                builder1.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder1.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Đã Ở Lại", Toast.LENGTH_SHORT).show();
                    }
                });
                builder1.show();
                break;
        }
        drawerLayout.close();
        return false;
    }

    public void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

    public String getTenAcount() {
        if (tenAcount != null) {
            return tenAcount;
        }
        return null;
    }

    public String getAvt() {
        return avt;
    }

    public void setPic(String img) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(img, bmOptions);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeFile(img, bmOptions);
        avtAcuont.setImageBitmap(bitmap);
    }
}