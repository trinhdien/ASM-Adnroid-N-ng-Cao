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

import dientcph27512.fpoly.asm_mob201_dientcph27512.Adapter.DanhSachNhacAdapter;
import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.DanhSachNhacDTO;
import dientcph27512.fpoly.asm_mob201_dientcph27512.R;
public class DanhSachNhacFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<DanhSachNhacDTO> list;
    private DanhSachNhacAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_danh_sach_nhac, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Danh Sách Nhạc");
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        list = new ArrayList<>();
        adapter = new DanhSachNhacAdapter(getActivity());
        list.add(new DanhSachNhacDTO("China A",R.drawable.img_1,R.raw.china_a,R.drawable.ic_baseline_play_arrow_24));
        list.add(new DanhSachNhacDTO("China B",R.drawable.img_1,R.raw.china_b,R.drawable.ic_baseline_play_arrow_24));
        list.add(new DanhSachNhacDTO("China C",R.drawable.img_1,R.raw.china_c,R.drawable.ic_baseline_play_arrow_24));
        list.add(new DanhSachNhacDTO("China D",R.drawable.img_1,R.raw.china_d,R.drawable.ic_baseline_play_arrow_24));
        list.add(new DanhSachNhacDTO("China E",R.drawable.img_1,R.raw.china_e,R.drawable.ic_baseline_play_arrow_24));
        list.add(new DanhSachNhacDTO("China F",R.drawable.img_1,R.raw.china_f,R.drawable.ic_baseline_play_arrow_24));
        list.add(new DanhSachNhacDTO("China Dragon",R.drawable.img_1,R.raw.china_dragon,R.drawable.ic_baseline_play_arrow_24));
        list.add(new DanhSachNhacDTO("China Life",R.drawable.img_1,R.raw.china_life,R.drawable.ic_baseline_play_arrow_24));
        list.add(new DanhSachNhacDTO("China G",R.drawable.img_1,R.raw.china_g,R.drawable.ic_baseline_play_arrow_24));
        list.add(new DanhSachNhacDTO("China H",R.drawable.img_1,R.raw.china_h,R.drawable.ic_baseline_play_arrow_24));
        list.add(new DanhSachNhacDTO("China Opera",R.drawable.img_1,R.raw.china_opera,R.drawable.ic_baseline_play_arrow_24));
        list.add(new DanhSachNhacDTO("China PiPa",R.drawable.img_1,R.raw.china_pipa,R.drawable.ic_baseline_play_arrow_24));
        list.add(new DanhSachNhacDTO("China Rain",R.drawable.img_1,R.raw.china_rain,R.drawable.ic_baseline_play_arrow_24));
        list.add(new DanhSachNhacDTO("China M",R.drawable.img_1,R.raw.china_m,R.drawable.ic_baseline_play_arrow_24));
        list.add(new DanhSachNhacDTO("China N",R.drawable.img_1,R.raw.china_m,R.drawable.ic_baseline_play_arrow_24));
        list.add(new DanhSachNhacDTO("China P",R.drawable.img_1,R.raw.china_p,R.drawable.ic_baseline_play_arrow_24));
        list.add(new DanhSachNhacDTO("China X",R.drawable.img_1,R.raw.china_x,R.drawable.ic_baseline_play_arrow_24));
        list.add(new DanhSachNhacDTO("China Y",R.drawable.img_1,R.raw.china_y,R.drawable.ic_baseline_play_arrow_24));
        list.add(new DanhSachNhacDTO("China Z",R.drawable.img_1,R.raw.china_z,R.drawable.ic_baseline_play_arrow_24));
        adapter.getList(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}