package dientcph27512.fpoly.asm_mob201_dientcph27512.NhacFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dientcph27512.fpoly.asm_mob201_dientcph27512.Adapter.DanhSachNhacAdapter;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Adapter.DanhSachNhacYeuThichAdapter;
import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.DanhSachNhacDTO;
import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.DanhSachYeuThich;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Database.DatabaseYeuThich;
import dientcph27512.fpoly.asm_mob201_dientcph27512.R;
import dientcph27512.fpoly.asm_mob201_dientcph27512.UI.MainActivity;

public class DanhSachYeuThichFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<DanhSachYeuThich> list;
    private DanhSachNhacYeuThichAdapter adapter;
    private MainActivity mainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_danh_sach_yeu_thich, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Danh Sách Nhạc Yêu Thích");
        mainActivity = (MainActivity) getActivity();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        adapter = new DanhSachNhacYeuThichAdapter(getActivity());
        list = DatabaseYeuThich.getInstance(getActivity()).yeuThichDAO().getYeuThich(mainActivity.getTenAcount());
        adapter.getList(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}