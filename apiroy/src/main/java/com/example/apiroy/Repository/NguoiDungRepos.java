package com.example.apiroy.Repository;

import com.example.apiroy.Model.NguoiDung;
import com.example.apiroy.Model.Truyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NguoiDungRepos extends JpaRepository<NguoiDung, Long> {
    @Query("SELECT t FROM Truyen t JOIN t.dsNguoiDungYeuThich nd ON nd.id = ?1")
    List<Truyen> layDsTruyenYeuThichTheoNguoiDung(Long id);

    @Query("SELECT t FROM Truyen t JOIN t.nguoiDung tg ON tg.id = ?1")
    List<Truyen> getTruyenTheoNguoiDung(Long id);

}
