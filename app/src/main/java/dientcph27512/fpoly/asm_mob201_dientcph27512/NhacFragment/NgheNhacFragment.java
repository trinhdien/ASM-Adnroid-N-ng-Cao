package dientcph27512.fpoly.asm_mob201_dientcph27512.NhacFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import dientcph27512.fpoly.asm_mob201_dientcph27512.BaoFragment.BaoGiaoDucFragment;
import dientcph27512.fpoly.asm_mob201_dientcph27512.BaoFragment.BaoPhapLuatFragment;
import dientcph27512.fpoly.asm_mob201_dientcph27512.BaoFragment.BaoTheThaoFragment;
import dientcph27512.fpoly.asm_mob201_dientcph27512.R;
import dientcph27512.fpoly.asm_mob201_dientcph27512.UI.MainActivity;

public class NgheNhacFragment extends Fragment {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayoutNhac;
    private MainActivity mainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nghe_nhac, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottomNavigationView);
        frameLayoutNhac = (FrameLayout) view.findViewById(R.id.frameLayoutNhac);
        mainActivity = (MainActivity) getActivity();
        replace(new DanhSachNhacFragment());
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.danhsachnhac:
                        replace(new DanhSachNhacFragment());
                        break;
                    case R.id.danhsachyeuthich:
                        if(mainActivity.getTenAcount() == null){
                            Toast.makeText(getActivity(), "Bạn Cần Đăng Nhập Để Dùng Chức Năng Này", Toast.LENGTH_SHORT).show();
                            return false;
                        }else{
                            replace(new DanhSachYeuThichFragment());
                        }
                        break;
                }
                return true;
            }
        });
    }
    public void replace(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayoutNhac, fragment);
        transaction.commit();
    }
}