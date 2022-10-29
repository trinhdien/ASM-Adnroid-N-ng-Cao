package dientcph27512.fpoly.asm_mob201_dientcph27512.DTO;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class DanhSachYeuThich implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String userYeuThich;
    private String ten;
    private int anh;
    private int nhac;

    public DanhSachYeuThich(String userYeuThich, String ten, int anh, int nhac) {
        this.userYeuThich = userYeuThich;
        this.ten = ten;
        this.anh = anh;
        this.nhac = nhac;
    }

    public String getUserYeuThich() {
        return userYeuThich;
    }

    public void setUserYeuThich(String userYeuThich) {
        this.userYeuThich = userYeuThich;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public int getNhac() {
        return nhac;
    }

    public void setNhac(int nhac) {
        this.nhac = nhac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
