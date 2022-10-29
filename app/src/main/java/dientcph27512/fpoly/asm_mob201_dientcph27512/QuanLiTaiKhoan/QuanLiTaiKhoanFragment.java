package dientcph27512.fpoly.asm_mob201_dientcph27512.QuanLiTaiKhoan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import dientcph27512.fpoly.asm_mob201_dientcph27512.NhacFragment.DanhSachNhacFragment;
import dientcph27512.fpoly.asm_mob201_dientcph27512.NhacFragment.DanhSachYeuThichFragment;
import dientcph27512.fpoly.asm_mob201_dientcph27512.R;
public class QuanLiTaiKhoanFragment extends Fragment {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayoutNhac;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quan_li_tai_khoan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottomNavigationView);
        frameLayoutNhac = (FrameLayout) view.findViewById(R.id.frameLayoutQLTK);
        replace(new DoiAvatarFragment());
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.doiavatar:
                        replace(new DoiAvatarFragment());
                        break;
                    case R.id.doimatkhau:
                        replace(new DoiMatKhauFragment());
                        break;
                }
                return true;
            }
        });
    }
    public void replace(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayoutQLTK, fragment);
        transaction.commit();
    }
}