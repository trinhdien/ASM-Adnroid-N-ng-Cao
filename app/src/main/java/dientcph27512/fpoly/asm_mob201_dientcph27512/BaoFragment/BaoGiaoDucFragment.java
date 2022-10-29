package dientcph27512.fpoly.asm_mob201_dientcph27512.BaoFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dientcph27512.fpoly.asm_mob201_dientcph27512.DownloadTin.TinTuc;
import dientcph27512.fpoly.asm_mob201_dientcph27512.R;
public class BaoGiaoDucFragment extends Fragment {
    private RecyclerView recyclerView2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bao_giao_duc, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Báo Giáo Dục");
        recyclerView2 = (RecyclerView) view.findViewById(R.id.recyclerView2);
        TinTuc tinTuc = new TinTuc(getActivity());
        tinTuc.execute("https://vnexpress.net/rss/giao-duc.rss");
    }
}