package com.example.apiroy.Service;

import com.example.apiroy.Model.NguoiDung;
import com.example.apiroy.Model.TruyenYeuThich;

import java.util.List;
import java.util.Map;

public interface NguoiDungService {
    List<NguoiDung> getAllNguoiDung();

    NguoiDung getNguoiDungById(Long id) throws Exception;

    NguoiDung createNguoiDung(NguoiDung nguoiDung);

    NguoiDung updateNguoiDung(Long id, NguoiDung nguoiDungDetails) throws Exception;

    Map<String, Boolean> deleteNguoiDung(Long id) throws Exception;

    List<TruyenYeuThich> layDsTruyenYeuThichTheoNguoiDung(Long id);
}
