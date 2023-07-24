package com.example.apiroy.Service;

import com.example.apiroy.Model.NguoiDung;
import com.example.apiroy.Model.Truyen;

import java.util.List;
import java.util.Map;

public interface NguoiDungService {
    List<NguoiDung> getAllNguoiDung();

    NguoiDung getNguoiDungById(Long id) throws Exception;

    NguoiDung createNguoiDung(NguoiDung nguoiDung);

    NguoiDung updateNguoiDung(Long id, NguoiDung nguoiDungDetails) throws Exception;

    Map<String, Boolean> deleteNguoiDung(Long id) throws Exception;


    public List<Truyen> getTruyenTheoNguoiDung(Long id);

    Truyen dangTruyen(Truyen truyen, Long nguoiDungId);

    List<Truyen> layDSTruyenYeuThichTheoNguoiDung(Long id);

    Truyen themTruyenVaoMucYeuThich(Truyen truyen, Long nguoiDungId);

    Map<String, Boolean> xoaTruyenKhoiMucYeuThich(Truyen truyen, Long id) throws Exception;
}
