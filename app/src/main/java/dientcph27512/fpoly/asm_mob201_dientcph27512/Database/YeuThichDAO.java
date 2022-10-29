package dientcph27512.fpoly.asm_mob201_dientcph27512.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.DanhSachYeuThich;

@Dao
public interface YeuThichDAO {
    @Insert
    void add(DanhSachYeuThich danhSachYeuThich);
    @Delete
    void delete(DanhSachYeuThich danhSachYeuThich);
    @Query("SELECT * FROM DANHSACHYEUTHICH WHERE userYeuThich =:user")
    List<DanhSachYeuThich> getYeuThich(String user);
}
