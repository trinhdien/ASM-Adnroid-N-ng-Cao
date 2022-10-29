package dientcph27512.fpoly.asm_mob201_dientcph27512.Adapter;

import static dientcph27512.fpoly.asm_mob201_dientcph27512.Services.DanhSachNhacSerVice.CLEAR;
import static dientcph27512.fpoly.asm_mob201_dientcph27512.Services.DanhSachNhacSerVice.PAUSE;
import static dientcph27512.fpoly.asm_mob201_dientcph27512.Services.DanhSachNhacSerVice.RESUME;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.DanhSachNhacDTO;
import dientcph27512.fpoly.asm_mob201_dientcph27512.R;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Services.DanhSachNhacSerVice;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Services.ThemYeuThichService;
import dientcph27512.fpoly.asm_mob201_dientcph27512.UI.MainActivity;

public class DanhSachNhacAdapter extends RecyclerView.Adapter<DanhSachNhacAdapter.DanhSachNhacViewHoler> {
    private ArrayList<DanhSachNhacDTO> list;
    private Context context;
    private MainActivity mainActivity;
    private ThemYeuThichService themYeuThichService;
    private DanhSachNhacDTO song;
    private boolean status;
    private int action = 0;
    private BroadcastReceiver broadcastReceiverDSnhac;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ThemYeuThichService.ThemYeuThichBinder binder = (ThemYeuThichService.ThemYeuThichBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public DanhSachNhacAdapter() {
    }

    public DanhSachNhacAdapter(Context context) {
        this.context = context;
    }

    public void getList(ArrayList<DanhSachNhacDTO> list) {
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
        DanhSachNhacDTO danhSachNhacDTO = list.get(position);
        final int index = position;
        if (danhSachNhacDTO == null) {
            return;
        }
        holder.tenNhac.setText(danhSachNhacDTO.getTen());
        holder.trangThai.setImageResource(danhSachNhacDTO.getTrangThai());
        holder.thich.setOnClickListener(v -> {
            mainActivity = (MainActivity) context;
            themYeuThichService = new ThemYeuThichService();
            Intent intent = new Intent(context, ThemYeuThichService.class);
            if (mainActivity.getTenAcount() == null) {
                Toast.makeText(context, "Bạn Cần Đăng Nhập Để Dùng Chức Năng Này", Toast.LENGTH_SHORT).show();
            } else {
                context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
                themYeuThichService.themYeuThich(mainActivity.getTenAcount(), danhSachNhacDTO.getTen(), danhSachNhacDTO.getAnh(), danhSachNhacDTO.getNhac(), context);
                Toast.makeText(context, "Đã Thêm Yêu Thích", Toast.LENGTH_SHORT).show();
            }
        });
        broadcastReceiverDSnhac = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getExtras();
                if (bundle == null) {
                    return;
                }
                song = (DanhSachNhacDTO) bundle.get("obj_song_ds");
                status = bundle.getBoolean("staus_ds_nhac");
                action = bundle.getInt("action_ds_nhac");
                if (action == 1 || action == 3) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getTen().equalsIgnoreCase(song.getTen())) {
                            list.get(i).setTrangThai(R.drawable.ic_baseline_play_arrow_24);
                            list.set(i, list.get(i));
                            notifyDataSetChanged();
                            break;
                        }
                    }
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getTen().equalsIgnoreCase(song.getTen())) {
                            list.get(i).setTrangThai(R.drawable.ic_baseline_pause_24);
                            list.set(i, list.get(i));
                            notifyDataSetChanged();
                            break;
                        }
                    }
                }
            }
        };
        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiverDSnhac, new IntentFilter("send_data_to_danh_sach_nhac"));
        holder.trangThai.setOnClickListener(v -> {
//            Log.d("zzzz", "onBindViewHolder: " + ind_ds +" " + index);
//            if (ind_ds == -1) {
//                Intent intent = new Intent(context, DanhSachNhacSerVice.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("song", danhSachNhacDTO);
//                intent.putExtras(bundle);
//                intent.putExtra("action_ad", 2);
//                intent.putExtra("index_ds", index);
//                context.startService(intent);
//                danhSachNhacDTO.setTrangThai(R.drawable.ic_baseline_pause_24);
//                list.set(index, danhSachNhacDTO);
//                notifyDataSetChanged();
//                ind_ds = index;
//                return;
//            }
//            if (ind_ds == index) {
//                if (danhSachNhacDTO.getTrangThai() == R.drawable.ic_baseline_pause_24) {
//                    Intent intent = new Intent(context, DanhSachNhacSerVice.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("song", danhSachNhacDTO);
//                    intent.putExtras(bundle);
//                    intent.putExtra("action_ad", 1);
//                    intent.putExtra("index_ds", index);
//                    context.startService(intent);
//                    Log.d("zzzzz", "onBindViewHolder: " + index);
//                    danhSachNhacDTO.setTrangThai(R.drawable.ic_baseline_play_arrow_24);
//                    list.set(index, danhSachNhacDTO);
//                    notifyDataSetChanged();
//                } else {
//                    Intent intent = new Intent(context, DanhSachNhacSerVice.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("song", danhSachNhacDTO);
//                    intent.putExtras(bundle);
//                    intent.putExtra("action_ad", 2);
//                    intent.putExtra("index_ds", index);
//                    context.startService(intent);
//                    danhSachNhacDTO.setTrangThai(R.drawable.ic_baseline_pause_24);
//                    list.set(index, danhSachNhacDTO);
//                    notifyDataSetChanged();
//                    return;
//                }
//            }
//            if(ind_ds != index){
//                Intent intent = new Intent(context, DanhSachNhacSerVice.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("song", danhSachNhacDTO);
//                intent.putExtras(bundle);
//                intent.putExtra("action_ad", 2);
//                intent.putExtra("index_ds", index);
//                context.stopService(intent);
//                danhSachNhacDTO.setTrangThai(R.drawable.ic_baseline_play_arrow_24);
//                list.set(ind_ds, danhSachNhacDTO);
//                notifyDataSetChanged();
//                ind_ds = index;
//                if (danhSachNhacDTO.getTrangThai() == R.drawable.ic_baseline_pause_24) {
//                    context.startService(intent);
//                    Log.d("zzzzz", "onBindViewHolder: " + index);
//                    danhSachNhacDTO.setTrangThai(R.drawable.ic_baseline_play_arrow_24);
//                    list.set(index, danhSachNhacDTO);
//                    notifyDataSetChanged();
//                } else {
//                    context.startService(intent);
//                    danhSachNhacDTO.setTrangThai(R.drawable.ic_baseline_pause_24);
//                    list.set(index, danhSachNhacDTO);
//                    notifyDataSetChanged();
//                }
//                return;
//            }
            if (danhSachNhacDTO.getTrangThai() == R.drawable.ic_baseline_pause_24) {
                Intent intent = new Intent(context, DanhSachNhacSerVice.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("song", danhSachNhacDTO);
                intent.putExtras(bundle);
                intent.putExtra("action_ad", 1);
                intent.putExtra("index_ds", index);
                context.startService(intent);
                Log.d("zzzzz", "onBindViewHolder: " + index);
                danhSachNhacDTO.setTrangThai(R.drawable.ic_baseline_play_arrow_24);
                list.set(index, danhSachNhacDTO);
                notifyDataSetChanged();
            } else {
                Intent intent = new Intent(context, DanhSachNhacSerVice.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("song", danhSachNhacDTO);
                intent.putExtras(bundle);
                intent.putExtra("action_ad", 2);
                intent.putExtra("index_ds", index);
                context.startService(intent);
                danhSachNhacDTO.setTrangThai(R.drawable.ic_baseline_pause_24);
                list.set(index, danhSachNhacDTO);
                notifyDataSetChanged();
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
