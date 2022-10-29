package dientcph27512.fpoly.asm_mob201_dientcph27512.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.DanhSachYeuThich;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Database.DatabaseYeuThich;

public class ThemYeuThichService extends Service {
    public ThemYeuThichService() {
    }
    public void themYeuThich(String user, String ten, int anh, int nhac, Context context){
        DanhSachYeuThich danhSachYeuThich = new DanhSachYeuThich(user,ten,anh,nhac);
        DatabaseYeuThich.getInstance(context).yeuThichDAO().add(danhSachYeuThich);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public class ThemYeuThichBinder extends Binder{
        ThemYeuThichBinder getThemYeuThichBinder(){
            return ThemYeuThichBinder.this;
        }
    }
}