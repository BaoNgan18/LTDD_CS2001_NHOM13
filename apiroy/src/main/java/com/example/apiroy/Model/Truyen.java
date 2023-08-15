package com.example.apiroy.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "truyen")
@Getter
@Setter
@NoArgsConstructor
public class Truyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "ten_truyen", nullable = false)
    private String tenTruyen;


    @ManyToMany
    @JoinTable(
            name = "truyen_the_loai",
            joinColumns = @JoinColumn(name = "truyen_id"),
            inverseJoinColumns = @JoinColumn(name = "theloai_id")
    )
    private List<TheLoai> dsTheLoai;

    @Column (name = "anh_bia", nullable = false)
    private String anhBia;

    @ManyToOne
    @JoinColumn(name = "nguoidung_id")
    private NguoiDung nguoiDung;

    @Column(name = "mo_ta_truyen", length = 5000)
    private String moTaTruyen;

    @Column(name = "noi_dung_chuong", length = 50000)
    private String noiDungChuong;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "truyen_yeu_thich_nguoi_dung",
            joinColumns = @JoinColumn(name = "truyen_id"),
            inverseJoinColumns = @JoinColumn(name = "nguoidung_id")
    )
    private List<NguoiDung> dsNguoiDungYeuThich;

    public Truyen(String ten, NguoiDung tacGia, String moTaTruyen, String noiDungChuong, String anhBia, List<TheLoai> dsTheLoai){
        this.setTenTruyen(ten);
        this.setNguoiDung(tacGia);
        this.setMoTaTruyen(moTaTruyen);
        this.setNoiDungChuong(noiDungChuong);
        this.setAnhBia(anhBia);
        this.setDsTheLoai(dsTheLoai);
    }
}
