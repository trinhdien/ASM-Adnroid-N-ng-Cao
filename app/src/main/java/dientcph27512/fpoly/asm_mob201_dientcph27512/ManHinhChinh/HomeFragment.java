package dientcph27512.fpoly.asm_mob201_dientcph27512.ManHinhChinh;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import dientcph27512.fpoly.asm_mob201_dientcph27512.BaoFragment.DocBaoFragment;
import dientcph27512.fpoly.asm_mob201_dientcph27512.NhacFragment.DanhSachYeuThichFragment;
import dientcph27512.fpoly.asm_mob201_dientcph27512.NhacFragment.NgheNhacFragment;
import dientcph27512.fpoly.asm_mob201_dientcph27512.QuanLiTaiKhoan.QuanLiTaiKhoanFragment;
import dientcph27512.fpoly.asm_mob201_dientcph27512.R;
import dientcph27512.fpoly.asm_mob201_dientcph27512.UI.LoginActivity;
import dientcph27512.fpoly.asm_mob201_dientcph27512.UI.MainActivity;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private LinearLayout docbao;
    private LinearLayout nhac;
    private LinearLayout qltk;
    private LinearLayout login;
    private LinearLayout logout;
    private LinearLayout exit;
    private FrameLayout frameLayout;
    private ImageView slideShow;
    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("HOME");
        docbao = (LinearLayout) view.findViewById(R.id.docbao);
        nhac = (LinearLayout) view.findViewById(R.id.nhac);
        qltk = (LinearLayout) view.findViewById(R.id.qltk);
        login = (LinearLayout) view.findViewById(R.id.login);
        exit = (LinearLayout) view.findViewById(R.id.exit);
        slideShow = (ImageView) view.findViewById(R.id.slideShow);
        AnimationDrawable animationDrawable = (AnimationDrawable) slideShow.getDrawable();
        animationDrawable.start();
        frameLayout = (FrameLayout) view.findViewById(R.id.frameLayout);
        mainActivity = (MainActivity) getActivity();
        String user = mainActivity.getTenAcount();
        if (user != null) {
            login.setVisibility(View.INVISIBLE);
            qltk.setGravity(View.TEXT_ALIGNMENT_CENTER);
        }
        docbao.setOnClickListener(this::onClick);
        nhac.setOnClickListener(this::onClick);
        qltk.setOnClickListener(this::onClick);
        login.setOnClickListener(this::onClick);
        exit.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.docbao:
                replace(new DocBaoFragment());
                break;
            case R.id.nhac:
                replace(new NgheNhacFragment());
                break;
            case R.id.qltk:
                if (mainActivity.getTenAcount() == null) {
                    Toast.makeText(getActivity(), "Bạn Cần Đăng Nhập Để Dùng Chức Năng Này", Toast.LENGTH_SHORT).show();
                } else {
                    replace(new QuanLiTaiKhoanFragment());
                }
                break;
            case R.id.login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.exit:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setTitle("Thoát?");
                builder1.setMessage("Bạn Có Muốn Thoát?");
                builder1.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        getActivity().finish();
                    }
                });
                builder1.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Đã Ở Lại", Toast.LENGTH_SHORT).show();
                    }
                });
                builder1.show();
                break;
        }
    }

    public void replace(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }
}