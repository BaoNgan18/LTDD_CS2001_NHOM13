package com.example.apiroy.Repository;

import com.example.apiroy.Model.NguoiDung;
import com.example.apiroy.Model.Truyen;
import com.example.apiroy.Model.TruyenYeuThich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NguoiDungRepos extends JpaRepository<NguoiDung, Long> {
    @Query("SELECT t FROM TruyenYeuThich t JOIN t.nguoiDung nd ON nd.id = ?1")
    List<TruyenYeuThich> layDsTruyenYeuThichTheoNguoiDung(Long id);
}
