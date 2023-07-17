package com.example.apiroy.Service;

import com.example.apiroy.Model.TheLoai;
import com.example.apiroy.Model.Truyen;

import java.util.List;
import java.util.Map;

public interface TruyenService {

    List<Truyen> getAllTruyen();

    Truyen getTruyenByID(Long id) throws  Exception;

    Truyen createTruyen(Truyen truyen);

    Truyen updateTruyen(Long id, Truyen truyenDetails) throws Exception;

    Map<String, Boolean> deleteTruyen(Long id) throws Exception;
}
