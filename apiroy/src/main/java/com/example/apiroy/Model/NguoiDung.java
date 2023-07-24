package com.example.apiroy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "nguoi_dung")
@Getter
@Setter
@NoArgsConstructor
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "ten_nguoi_dung", nullable = false)
    private String userName;

    @Column (name = "mat_khau", nullable = false)
    private String password;

    @Column (name = "email", nullable = false)
    private String email;

    @Column (name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;


    @ManyToMany(mappedBy = "dsNguoiDungYeuThich")
    private List<Truyen> dsTruyenYeuThich;


    @OneToMany(mappedBy = "nguoiDung")
    private List<Truyen> dsTruyen;

    public NguoiDung(String username, String password, String email) {
        this.userName = username;
        this.password = password;
        this.email = email;
    }
}
