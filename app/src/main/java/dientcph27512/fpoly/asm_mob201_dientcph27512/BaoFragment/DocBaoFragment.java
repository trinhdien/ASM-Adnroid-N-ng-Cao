package dientcph27512.fpoly.asm_mob201_dientcph27512.BaoFragment;

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

import dientcph27512.fpoly.asm_mob201_dientcph27512.R;
public class DocBaoFragment extends Fragment {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doc_bao, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottomNavigationView);
        frameLayout = (FrameLayout) view.findViewById(R.id.frameLayout2);
        replace(new BaoTheThaoFragment());
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.baothethao:
                        replace(new BaoTheThaoFragment());
                        break;
                    case R.id.baophapluat:
                        replace(new BaoPhapLuatFragment());
                        break;
                    case R.id.giaoduc:
                        replace(new BaoGiaoDucFragment());
                        break;
                }
                return true;
            }
        });
    }
    public void replace(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout2, fragment);
        transaction.commit();
    }
}