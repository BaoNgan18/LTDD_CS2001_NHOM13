package com.example.apiroy.Repository;

import com.example.apiroy.Model.TheLoai;
import com.example.apiroy.Model.Truyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TheLoaiRepos extends JpaRepository<TheLoai,Long> {
    @Query("SELECT t FROM Truyen t JOIN t.dsTheLoai tl ON tl.id = ?1")
    List<Truyen> getTruyenTheoTheLoai(Long id);
}
