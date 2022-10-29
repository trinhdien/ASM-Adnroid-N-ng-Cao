package dientcph27512.fpoly.asm_mob201_dientcph27512.Adapter;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.DanhSachNhacDTO;
import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.DanhSachYeuThich;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Database.DatabaseYeuThich;
import dientcph27512.fpoly.asm_mob201_dientcph27512.R;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Services.DanhSachNhacSerVice;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Services.DanhSachNhacYeuThichSerVice;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Services.ThemYeuThichService;
import dientcph27512.fpoly.asm_mob201_dientcph27512.UI.MainActivity;

public class DanhSachNhacYeuThichAdapter extends RecyclerView.Adapter<DanhSachNhacYeuThichAdapter.DanhSachNhacViewHoler> {
    private List<DanhSachYeuThich> list;
    private Context context;
    public DanhSachNhacYeuThichAdapter(Context context) {
        this.context = context;
    }
    private BroadcastReceiver broadcastReceiverDSnhacYeuThich;
    public void getList(List<DanhSachYeuThich> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DanhSachNhacViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danh_sach_nhac, parent, false);
        return new DanhSachNhacViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhSachNhacViewHoler holder, int position) {
        DanhSachYeuThich danhSachNhacDTO = list.get(position);
        final int index = position;
        if (danhSachNhacDTO == null) {
            return;
        }
        holder.tenNhac.setText(danhSachNhacDTO.getTen());
        holder.thich.setImageResource(R.drawable.ic_baseline_favorite_24);
        holder.thich.setOnClickListener(v -> {
            DatabaseYeuThich.getInstance(context).yeuThichDAO().delete(danhSachNhacDTO);
            Toast.makeText(context, "Đã Xoá Yêu Thích", Toast.LENGTH_SHORT).show();
            list.remove(index);
            notifyDataSetChanged();
        });
        holder.trangThai.setOnClickListener(v -> {
            Log.d("zzzzz", "onBindViewHolder: " + index);
            Drawable.ConstantState play = holder.trangThai.getDrawable().getConstantState();
            Drawable.ConstantState pause = context.getDrawable(R.drawable.ic_baseline_pause_24).getConstantState();
            if(play == pause){
                holder.trangThai.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                Intent intent = new Intent(context, DanhSachNhacYeuThichSerVice.class);
                intent.putExtra("action_ad",1);
                context.startService(intent);
            }else{
                holder.trangThai.setImageResource(R.drawable.ic_baseline_pause_24);
                Intent intent = new Intent(context, DanhSachNhacYeuThichSerVice.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("song", danhSachNhacDTO);
                intent.putExtras(bundle);
                intent.putExtra("action_ad",2);
                context.startService(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class DanhSachNhacViewHoler extends RecyclerView.ViewHolder {
        private ImageView imageNhac;
        private TextView tenNhac;
        private ImageView trangThai;
        private ImageView thich;

        public DanhSachNhacViewHoler(@NonNull View itemView) {
            super(itemView);
            imageNhac = (ImageView) itemView.findViewById(R.id.image_nhac);
            tenNhac = (TextView) itemView.findViewById(R.id.ten_nhac);
            trangThai = (ImageView) itemView.findViewById(R.id.trang_thai);
            thich = (ImageView) itemView.findViewById(R.id.thich);
        }
    }
}
