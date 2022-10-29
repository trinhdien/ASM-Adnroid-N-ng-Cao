package dientcph27512.fpoly.asm_mob201_dientcph27512.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.User;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Database.DatabaseUser;

public class DangKiService extends Service {
    public DangKiService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void creatAcount(String user, String pass, String img ,Context context){
        User user1 = new User(user,pass,img);
        DatabaseUser.getInstance(context).userDAO().add(user1);
    }
    public class CreateAcoutBinder extends Binder {
        CreateAcoutBinder getCreateAcoutBinder(){
            return CreateAcoutBinder.this;
        }
    }
}