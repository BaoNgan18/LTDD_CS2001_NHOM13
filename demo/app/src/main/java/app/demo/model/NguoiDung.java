package app.demo.model;

import java.time.LocalDateTime;
import java.util.List;

public class NguoiDung {
    private long id;

    private String userName;


    private String password;


    private String email;


    private LocalDateTime createdAt;


    private List<Truyen> dsTruyenYeuThich;


    private List<Truyen> dsTruyen;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Truyen> getDsTruyenYeuThich() {
        return dsTruyenYeuThich;
    }

    public void setDsTruyenYeuThich(List<Truyen> dsTruyenYeuThich) {
        this.dsTruyenYeuThich = dsTruyenYeuThich;
    }

    public List<Truyen> getDsTruyen() {
        return dsTruyen;
    }

    public void setDsTruyen(List<Truyen> dsTruyen) {
        this.dsTruyen = dsTruyen;
    }
}
