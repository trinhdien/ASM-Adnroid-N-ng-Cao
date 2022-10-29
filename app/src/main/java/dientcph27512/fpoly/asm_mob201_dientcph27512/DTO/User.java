package dientcph27512.fpoly.asm_mob201_dientcph27512.DTO;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    private String userName;
    private String passWord;
    private String avt;

    public User(String userName, String passWord, String avt) {
        this.userName = userName;
        this.passWord = passWord;
        this.avt = avt;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAvt() {
        return avt;
    }

    public void setAvt(String avt) {
        this.avt = avt;
    }
}
