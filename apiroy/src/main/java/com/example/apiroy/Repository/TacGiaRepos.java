package com.example.apiroy.Repository;

import com.example.apiroy.Model.TacGia;
import com.example.apiroy.Model.Truyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TacGiaRepos extends JpaRepository<TacGia, Long> {
    @Query("SELECT t FROM Truyen t JOIN t.tacGia tg ON tg.id = ?1")
    List<Truyen> getTruyenTheoTacGia(Long id);
}
