package com.example.apiroy.Service.Impl;

import com.example.apiroy.Model.NguoiDung;
import com.example.apiroy.Model.TruyenYeuThich;
import com.example.apiroy.Repository.NguoiDungRepos;
import com.example.apiroy.Service.NguoiDungService;
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

        nguoiDung.setUsername(nguoiDungDetails.getUsername());
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
    public List<TruyenYeuThich> layDsTruyenYeuThichTheoNguoiDung(Long id) {
        return nguoiDungRepos.layDsTruyenYeuThichTheoNguoiDung(id);
    }
}
