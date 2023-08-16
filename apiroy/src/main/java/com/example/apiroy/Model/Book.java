package com.example.apiroy.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Entity
@Table(name = "truyen")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "ten_truyen", nullable = false)
    private String nameBook;


    @ManyToMany
    @JoinTable(
            name = "truyen_the_loai",
            joinColumns = @JoinColumn(name = "truyen_id"),
            inverseJoinColumns = @JoinColumn(name = "theloai_id")
    )
    private List<Gerne> listGerne;

    @Column (name = "anh_bia", nullable = true)
    private String coverImg;

    @ManyToOne
    @JoinColumn(name = "nguoidung_id")
    private User user;

    @Column(name = "mo_ta_truyen", length = 5000)
    private String describe;

    @Column(name = "noi_dung_chuong", length = 50000)
    private String content;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "truyen_yeu_thich_nguoi_dung",
            joinColumns = @JoinColumn(name = "truyen_id"),
            inverseJoinColumns = @JoinColumn(name = "nguoidung_id")
    )
    private List<User> listUserPressingLove;

//    @Transient
//    private MultipartFile file;

    public Book(String nameBook, User author, String describe, String content, String coverImg, List<Gerne> listGerne){
        this.setNameBook(nameBook);
        this.setUser(author);
        this.setDescribe(describe);
        this.setContent(content);
        this.setCoverImg(coverImg);
        this.setListGerne(listGerne);
    }
}
