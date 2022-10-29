package dientcph27512.fpoly.asm_mob201_dientcph27512.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.List;

import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.User;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Database.DatabaseUser;

public class CheckLoginService extends Service {
    public CheckLoginService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public String checkLogin(String user, String pass, Context context){
        List<User> list = DatabaseUser.getInstance(context).userDAO().checkLogin(user,pass);
        if(list.size() == 0){
            return null;
        }
        return list.get(0).getAvt();
    }
    public class CheckLoginBinder extends Binder{
        CheckLoginBinder getCheckLoginBinder(){
            return CheckLoginBinder.this;
        }
    }
}