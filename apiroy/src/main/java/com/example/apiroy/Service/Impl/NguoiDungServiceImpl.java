package com.example.apiroy.Service.Impl;

import com.example.apiroy.Model.NguoiDung;
import com.example.apiroy.Model.Truyen;
import com.example.apiroy.Repository.NguoiDungRepos;
import com.example.apiroy.Repository.TruyenRepos;
import com.example.apiroy.Service.NguoiDungService;
import com.example.apiroy.Service.TruyenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class NguoiDungServiceImpl implements NguoiDungService {

    private NguoiDungRepos nguoiDungRepos;

    private TruyenService truyenService;

    private TruyenRepos truyenRepos;

    @Override
    public List<NguoiDung> getAllNguoiDung() {
        return nguoiDungRepos.findAll();
    }

    @Override
    public NguoiDung getNguoiDungById(Long id) throws Exception {
        NguoiDung nguoiDung = nguoiDungRepos.findById(id).orElseThrow(() -> new Exception("Người dùng này không tồn tại: " + id));
        return nguoiDung;
    }

    @Override
    public NguoiDung createNguoiDung(NguoiDung nguoiDung) {
        return nguoiDungRepos.save(nguoiDung);
    }

    @Override
    public NguoiDung updateNguoiDung(Long id, NguoiDung nguoiDungDetails) throws Exception {
        NguoiDung nguoiDung = nguoiDungRepos.findById(id)
                .orElseThrow(() -> new Exception("Người dùng này không tồn tại: " + id));

        nguoiDung.setUserName(nguoiDungDetails.getUserName());
        nguoiDung.setPassword(nguoiDungDetails.getPassword());
        return nguoiDungRepos.save(nguoiDung);
    }

    @Override
    public Map<String, Boolean> deleteNguoiDung(Long id) throws Exception{
        NguoiDung nguoiDung = nguoiDungRepos.findById(id)
                .orElseThrow(() -> new Exception("Người dùng này không tồn tại: " + id));

        nguoiDungRepos.delete(nguoiDung);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    @Override
    public List<Truyen> getTruyenTheoNguoiDung(Long id) {
        return nguoiDungRepos.getTruyenTheoNguoiDung(id);
    }

    @Override
    public Truyen dangTruyen(Truyen truyen, Long nguoiDungId) {
        NguoiDung tacGia = nguoiDungRepos.findById(nguoiDungId).get();
        truyen.setNguoiDung(tacGia);
        return truyenService.createTruyen(truyen);
    }

    @Override
    public List<Truyen> layDSTruyenYeuThichTheoNguoiDung(Long id) {
        return nguoiDungRepos.layDsTruyenYeuThichTheoNguoiDung(id);
    }

    @Override
    public Truyen themTruyenVaoMucYeuThich(Truyen truyen, Long nguoiDungId) {
        NguoiDung nguoiDung = nguoiDungRepos.findById(nguoiDungId).get();
        nguoiDung.getDsTruyen().add(truyen);
        truyen.getDsNguoiDungYeuThich().add(nguoiDung);
        nguoiDungRepos.save(nguoiDung);
        truyenRepos.save(truyen);
        return truyen;
    }

    public Map<String, Boolean> xoaTruyenKhoiMucYeuThich(Truyen truyen, Long nguoiDungId) throws Exception{
        NguoiDung nguoiDung = nguoiDungRepos.findById(nguoiDungId)
                .orElseThrow(() -> new Exception("Người dùng này không tồn tại: " + nguoiDungId));

        nguoiDung.getDsTruyenYeuThich().remove(truyen);
        truyen.getDsNguoiDungYeuThich().remove(nguoiDung);
        nguoiDungRepos.save(nguoiDung);
        truyenRepos.save(truyen);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
