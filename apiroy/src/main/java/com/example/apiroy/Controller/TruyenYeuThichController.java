package com.example.apiroy.Controller;

import com.example.apiroy.Model.Truyen;
import com.example.apiroy.Model.TruyenYeuThich;
import com.example.apiroy.Service.TruyenYeuThichService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/truyenyeuthich")
@RequiredArgsConstructor
public class TruyenYeuThichController {
    @Autowired
    private TruyenYeuThichService truyenYeuThichService;

    @PostMapping("/{nguoiDungId}/{truyenId}")
    public ResponseEntity<String> themTruyenYeuThich(
            @PathVariable Long nguoiDungId,
            @PathVariable Long truyenId
    ) {
        boolean themThanhCong = truyenYeuThichService.themTruyenYeuThich(nguoiDungId, truyenId);

        if (themThanhCong) {
            return ResponseEntity.ok("Đã thêm truyện vào mục yêu thích.");
        } else {
            return ResponseEntity.badRequest().body("Không thể thêm truyện vào mục yêu thích.");
        }
    }
}

