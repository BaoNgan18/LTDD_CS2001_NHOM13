package com.example.apiroy.Service;

import com.example.apiroy.Model.TheLoai;
import com.example.apiroy.Model.Truyen;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface TheLoaiService {
    List<TheLoai> getAllTheLoai();
    TheLoai getTheLoaiById(Long id) throws Exception;
    TheLoai createTheLoai(TheLoai theLoai);
    TheLoai updateTheLoai(Long id, TheLoai theLoaiDetails) throws Exception;
    Map<String, Boolean> deleteTheLoai(Long id) throws Exception;
    List<Truyen> getTruyenTheoTheLoai(Long id);
}
