package dientcph27512.fpoly.asm_mob201_dientcph27512.BroadcastReciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import dientcph27512.fpoly.asm_mob201_dientcph27512.Services.DanhSachNhacSerVice;

public class MyReciverDanhSachNhac extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int action = intent.getIntExtra("action",0);
        Intent intent1 = new Intent(context, DanhSachNhacSerVice.class);
        intent1.putExtra("action_br",action);
        context.startService(intent1);
    }
}
