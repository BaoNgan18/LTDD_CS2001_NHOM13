package com.example.apiroy.Service.Impl;

import com.example.apiroy.Model.TacGia;
import com.example.apiroy.Model.TheLoai;
import com.example.apiroy.Model.Truyen;
import com.example.apiroy.Repository.TacGiaRepos;
import com.example.apiroy.Repository.TheLoaiRepos;
import com.example.apiroy.Service.TacGiaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class TacGiaServiceImpl implements TacGiaService {
    @Autowired
    private TacGiaRepos tacGiaRepos;

    @Override
    public List<TacGia> getAllTacGia() {
        return tacGiaRepos.findAll();
    }

    @Override
    public TacGia getTacGiaById(Long id) throws Exception {
        TacGia tacGia = tacGiaRepos.findById(id).orElseThrow(() -> new Exception("Tác giả này không tồn tại: " + id));
        return tacGia;
    }

    @Override
    public TacGia createTacGia(TacGia tacGia) {
        return tacGiaRepos.save(tacGia);
    }

    @Override
    public TacGia updateTacGia(Long id, TacGia tacGiaDetails) throws Exception {
        TacGia tacGia = tacGiaRepos.findById(id)
                .orElseThrow(() -> new Exception("Tác giả này không tồn tại: " + id));

        tacGia.setId(tacGiaDetails.getId());
        tacGia.setTenTacGia(tacGiaDetails.getTenTacGia());

        return tacGiaRepos.save(tacGia);
    }

    @Override
    public Map<String, Boolean> deleteTacGia(Long id) throws Exception {

        TacGia tacGia = tacGiaRepos.findById(id)
                .orElseThrow(() -> new Exception("Tác giả này không tồn tại: " + id));

        tacGiaRepos.delete(tacGia);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @Override
    public List<Truyen> getTruyenTheoTacGia(Long id) {
        return tacGiaRepos.getTruyenTheoTacGia(id);
    }
}
