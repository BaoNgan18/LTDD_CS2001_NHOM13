package com.example.apiroy.Service.Impl;

import com.example.apiroy.Model.NguoiDung;
import com.example.apiroy.Model.Truyen;
import com.example.apiroy.Model.TruyenYeuThich;
import com.example.apiroy.Repository.NguoiDungRepos;
import com.example.apiroy.Repository.TruyenRepos;
import com.example.apiroy.Repository.TruyenYeuThichRepos;
import com.example.apiroy.Service.TruyenYeuThichService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class TruyenYeuThichServiceImpl implements TruyenYeuThichService {

    @Autowired
    private TruyenYeuThichRepos truyenYeuThichRepos;

    @Autowired
    private NguoiDungRepos nguoiDungRepos;

    @Autowired
    private TruyenRepos truyenRepos;
    @Override
    public boolean themTruyenYeuThich(Long nguoiDungId, Long truyenId) {
        Optional<NguoiDung> nguoiDungOptional = nguoiDungRepos.findById(nguoiDungId);
        Optional<Truyen> truyenOptional = truyenRepos.findById(truyenId);

        if (nguoiDungOptional.isPresent() && truyenOptional.isPresent()) {
            NguoiDung nguoiDung = nguoiDungOptional.get();
            Truyen truyen = truyenOptional.get();

            TruyenYeuThich truyenYeuThich = new TruyenYeuThich();
            truyenYeuThich.setNguoiDung(nguoiDung);
            truyenYeuThich.setTruyen(truyen);

            truyenYeuThichRepos.save(truyenYeuThich);
            return true;
        }
        return false;
    }
}
