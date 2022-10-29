package dientcph27512.fpoly.asm_mob201_dientcph27512.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.List;

import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.User;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Database.DatabaseUser;

public class DoiMatService extends Service {
    public DoiMatService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public boolean doiMatKhau(String user, String pass,String passMoi,Context context){
        List<User> list = DatabaseUser.getInstance(context).userDAO().checkLogin(user,pass);
        if(list.size() == 0){
            return false;
        }
        User user1 = list.get(0);
        user1.setPassWord(passMoi);
        DatabaseUser.getInstance(context).userDAO().update(user1);
        return true;
    }
    public class DoiMatKhauBinder extends Binder{
        DoiMatKhauBinder getDoiMatKhauBinder(){
            return DoiMatKhauBinder.this;
        }
    }
}