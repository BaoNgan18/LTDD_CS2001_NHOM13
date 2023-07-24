package com.example.apiroy.Service.Impl;

import com.example.apiroy.Model.Truyen;
import com.example.apiroy.Repository.TruyenRepos;
import com.example.apiroy.Service.TruyenService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class TruyenServiceImpl implements TruyenService {
    @Autowired
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

        // So sánh và cập nhật tên truyện nếu có thay đổi
        if (!Objects.equals(truyen.getTenTruyen(), truyenDetails.getTenTruyen())) {
            truyen.setTenTruyen(truyenDetails.getTenTruyen());
        }
        // So sánh và cập nhật mô tả truyện nếu có thay đổi
        if (!Objects.equals(truyen.getMoTaTruyen(), truyenDetails.getMoTaTruyen())) {
            truyen.setMoTaTruyen(truyenDetails.getMoTaTruyen());
        }
        // So sánh và cập nhật nội dung chương nếu có thay đổi
        if (!Objects.equals(truyen.getNoiDungChuong(), truyenDetails.getNoiDungChuong())) {
            truyen.setNoiDungChuong(truyenDetails.getNoiDungChuong());
        }
        // So sánh và cập nhật tên tác giả truyện nếu có thay đổi
        if (!Objects.equals(truyen.getNguoiDung(), truyenDetails.getNguoiDung())) {
            truyen.setNguoiDung(truyenDetails.getNguoiDung());
        }
        // So sánh và cập nhật thể loại nếu có thay đổi
        if (!Objects.equals(truyen.getDsTheLoai(), truyenDetails.getDsTheLoai())) {
            truyen.setDsTheLoai(truyenDetails.getDsTheLoai());
        }
        // So sánh và cập nhật ảnh bìa nếu có thay đổi
        if (!Objects.equals(truyen.getAnhBia(), truyenDetails.getAnhBia())) {
            truyen.setAnhBia(truyenDetails.getAnhBia());
        }

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
