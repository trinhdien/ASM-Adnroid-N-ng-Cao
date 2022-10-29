package dientcph27512.fpoly.asm_mob201_dientcph27512.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.DanhSachYeuThich;

@Database(entities = {DanhSachYeuThich.class},version = 2)
public abstract class DatabaseYeuThich extends RoomDatabase {
    private static final String DB_NAME = "danh_sach_yeu_thich.db";
    private static DatabaseYeuThich instance;

    public static synchronized DatabaseYeuThich getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),DatabaseYeuThich.class,DB_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract YeuThichDAO yeuThichDAO();
}
