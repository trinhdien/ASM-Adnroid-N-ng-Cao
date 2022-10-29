package dientcph27512.fpoly.asm_mob201_dientcph27512.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.User;

@Database(entities = {User.class}, version = 1)
public abstract class DatabaseUser extends RoomDatabase {
    private static final String DB_NAME = "user.db";
    private static DatabaseUser instance;
    public static synchronized DatabaseUser getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),DatabaseUser.class,DB_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract UserDAO userDAO();
}
