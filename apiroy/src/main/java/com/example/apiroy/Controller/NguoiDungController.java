package com.example.apiroy.Controller;


import com.example.apiroy.Model.NguoiDung;
import com.example.apiroy.Model.Truyen;
import com.example.apiroy.Service.NguoiDungService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/nguoidung")
public class NguoiDungController {

    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping()
    public List<NguoiDung> getAllNguoiDung() {
        return nguoiDungService.getAllNguoiDung();
    }

    @GetMapping("{id}")
    public ResponseEntity<NguoiDung> getNguoiDungById(@PathVariable(value = "id") Long id)
            throws Exception {

        return ResponseEntity.ok().body(nguoiDungService.getNguoiDungById(id));
    }

    @PostMapping()
    public NguoiDung createNguoiDung(@Valid @RequestBody NguoiDung nguoiDung) {
        return nguoiDungService.createNguoiDung(nguoiDung);
    }

    @PutMapping("{id}")
    public ResponseEntity<NguoiDung> updateNguoiDung(@PathVariable(value = "id") Long id,
                                                     @Valid @RequestBody NguoiDung nguoiDungDetails) throws Exception {
        return ResponseEntity.ok(nguoiDungService.updateNguoiDung(id, nguoiDungDetails));
    }

    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteNguoiDung(@PathVariable(value = "id") Long id)
            throws Exception {
        return nguoiDungService.deleteNguoiDung(id);
    }

    @GetMapping("{id}/truyenyeuthich/")
    public List<Truyen> layDSTruyenYeuThichTheoNguoiDung(@PathVariable(value = "id") Long id) {
        return nguoiDungService.layDSTruyenYeuThichTheoNguoiDung(id);
    }

    @GetMapping("{id}/truyen/")
    public List<Truyen> getTruyenTheoNguoiDung(@PathVariable(value = "id") Long id) {
        return nguoiDungService.getTruyenTheoNguoiDung(id);
    }

    @PostMapping("{id}/dang_truyen")
    public Truyen dangTruyen(@Valid @RequestBody Truyen truyen,@PathVariable(value = "id") Long nguoiDungId){
        return nguoiDungService.dangTruyen(truyen,nguoiDungId);
    }
    @PostMapping("{id}/yeu_thich")
    public Truyen themTruyenVaoMucYeuThich(@Valid @RequestBody Truyen truyen, @PathVariable(value = "id")Long nguoiDungId){
        return nguoiDungService.themTruyenVaoMucYeuThich(truyen, nguoiDungId);
    }

    @DeleteMapping("{id}/xoa_yeu_thich")
    public Map<String, Boolean> xoaTruyenKhoiMucYeuThich(@Valid @RequestBody Truyen truyen,@PathVariable(value = "id") Long id)
            throws Exception {
        return nguoiDungService.xoaTruyenKhoiMucYeuThich(truyen, id);
    }
}
