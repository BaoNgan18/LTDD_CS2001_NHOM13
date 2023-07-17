package com.example.apiroy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "the_loai")
@Getter
@Setter
@NoArgsConstructor
public class TheLoai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "ten_the_loai", nullable = false)
    private String tenTheLoai;

    @JsonIgnore
    @ManyToMany(mappedBy = "dsTheLoai")
    private List<Truyen> dsTruyen;

    public TheLoai (Long id, String type) {
        this.setTenTheLoai(type);
        this.id = id;
    }
}
