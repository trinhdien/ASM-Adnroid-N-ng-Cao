package dientcph27512.fpoly.asm_mob201_dientcph27512.DTO;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class DanhSachNhacDTO implements Serializable {
    private String ten;
    private int anh;
    private int nhac;
    private int trangThai;

    public DanhSachNhacDTO(String ten, int anh, int nhac,int trangThai) {
        this.ten = ten;
        this.anh = anh;
        this.nhac = nhac;
        this.trangThai = trangThai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
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
}
