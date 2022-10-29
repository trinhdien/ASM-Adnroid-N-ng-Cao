package dientcph27512.fpoly.asm_mob201_dientcph27512.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.User;

@Dao
public interface UserDAO {
    @Insert
    void add(User user);
    @Update
    void update(User user);
    @Query("SELECT * FROM User")
    List<User> getAll();
    @Query("SELECT * FROM User WHERE userName =:user AND passWord =:pass")
    List<User> checkLogin(String user, String pass);
    @Query("SELECT * FROM User WHERE userName =:user")
    List<User> checkUser(String user);
}
