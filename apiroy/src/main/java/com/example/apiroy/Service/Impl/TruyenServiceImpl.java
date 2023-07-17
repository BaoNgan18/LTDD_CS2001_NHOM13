package com.example.apiroy.Service.Impl;

import com.example.apiroy.Model.TheLoai;
import com.example.apiroy.Model.Truyen;
import com.example.apiroy.Repository.TruyenRepos;
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
public class TruyenServiceImpl implements TruyenService {
    private TruyenRepos truyenRepos;

    public List<Truyen> getAllTruyen(){
        return truyenRepos.findAll();
    }


    @Override
    public Truyen getTruyenByID(Long id) throws  Exception{
        Truyen truyen = truyenRepos.findById(id).orElseThrow(() -> new Exception("Truyện này không tồn tại: " + id));
        return truyen;
    }

    @Override
    public Truyen createTruyen(Truyen truyen) {
        return truyenRepos.save(truyen);
    }

    @Override
    public Truyen updateTruyen(Long id, Truyen truyenDetails) throws Exception {
        Truyen truyen = truyenRepos.findById(id)
                .orElseThrow(() -> new Exception("Truyện này không tồn tại: " + id));

        truyen.setId(truyenDetails.getId());
        truyen.setTenTruyen(truyenDetails.getTenTruyen());

        return truyenRepos.save(truyen);
    }

    @Override
    public Map<String, Boolean> deleteTruyen(Long id) throws Exception {

        Truyen truyen = truyenRepos.findById(id)
                .orElseThrow(() -> new Exception("Truyện này không tồn tại: " + id));

        truyenRepos.delete(truyen);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
