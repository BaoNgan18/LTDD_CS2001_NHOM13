package com.example.apiroy.Service.Impl;

import com.example.apiroy.Model.TheLoai;
import com.example.apiroy.Model.Truyen;
import com.example.apiroy.Repository.TheLoaiRepos;
import com.example.apiroy.Service.TheLoaiService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class TheLoaiServiceImpl implements TheLoaiService {
    @Autowired
    private TheLoaiRepos theLoaiRepos;

    @Override
    public List<TheLoai> getAllTheLoai() {
        return theLoaiRepos.findAll();
    }

    @Override
    public TheLoai getTheLoaiById(Long id) throws Exception {
        TheLoai theLoai = theLoaiRepos.findById(id).orElseThrow(() -> new Exception("Thể loại này không tồn tại: " + id));
        return theLoai;
    }

    @Override
    public TheLoai createTheLoai(TheLoai theLoai) {
        return theLoaiRepos.save(theLoai);
    }

    @Override
    public TheLoai updateTheLoai(Long id, TheLoai theLoaiDetails) throws Exception {
        TheLoai theLoai = theLoaiRepos.findById(id)
                .orElseThrow(() -> new Exception("Thể loại này không tồn tại: " + id));

        theLoai.setId(theLoaiDetails.getId());
        theLoai.setTenTheLoai(theLoaiDetails.getTenTheLoai());

        return theLoaiRepos.save(theLoai);
    }

    @Override
    public Map<String, Boolean> deleteTheLoai(Long id) throws Exception {

        TheLoai theLoai = theLoaiRepos.findById(id)
                .orElseThrow(() -> new Exception("Thể loại này không tồn tại: " + id));

        theLoaiRepos.delete(theLoai);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @Override
    public List<Truyen> getTruyenTheoTheLoai(Long id) {
        return theLoaiRepos.getTruyenTheoTheLoai(id);
    }
}
