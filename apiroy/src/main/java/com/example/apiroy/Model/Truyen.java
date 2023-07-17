package com.example.apiroy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "truyen_the_loai",
            joinColumns = @JoinColumn(name = "truyen_id"),
            inverseJoinColumns = @JoinColumn(name = "theloai_id")
    )
    private List<TheLoai> dsTheLoai;
//    private int soChuong;
    @ManyToOne
    @JoinColumn(name = "tacgia_id")
    private TacGia tacGia;

    public Truyen(Long id, String ten){
        this.setId(id);
        this.setTenTruyen(ten);
    }
}
