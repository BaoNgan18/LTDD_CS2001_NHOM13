package com.example.apiroy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tac_gia")
@Getter
@Setter
@NoArgsConstructor
public class TacGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "ten_tac_gia", nullable = false)
    private String tenTacGia;

    @JsonIgnore
    @OneToMany(mappedBy = "tacGia")
    private List<Truyen> dsTruyen;

    public TacGia (Long id, String ten){
        this.id = id;
        this.tenTacGia = ten;
    }

}
