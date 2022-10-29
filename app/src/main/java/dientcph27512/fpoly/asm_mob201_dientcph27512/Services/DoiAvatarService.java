package dientcph27512.fpoly.asm_mob201_dientcph27512.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.List;

import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.User;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Database.DatabaseUser;

public class DoiAvatarService extends Service {
    public DoiAvatarService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void doiAvatar(String user, String img, Context context){
        List<User> list = DatabaseUser.getInstance(context).userDAO().checkUser(user);
        User user1 = list.get(0);
        user1.setAvt(img);
        DatabaseUser.getInstance(context).userDAO().update(user1);
    }
    public class DoiAvatarBinder extends Binder{
        DoiAvatarBinder getAvatarBinder(){
            return DoiAvatarBinder.this;
        }
    }
}