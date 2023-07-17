package com.example.apiroy.Service;

import com.example.apiroy.Model.TacGia;
import com.example.apiroy.Model.Truyen;

import java.util.List;
import java.util.Map;

public interface TacGiaService {
    List<TacGia> getAllTacGia();

    TacGia getTacGiaById(Long id) throws Exception;

    TacGia createTacGia(TacGia tacGia);

    TacGia updateTacGia(Long id, TacGia tacGiaDetails) throws Exception;

    Map<String, Boolean> deleteTacGia(Long id) throws Exception;

    List<Truyen> getTruyenTheoTacGia(Long id);
}
