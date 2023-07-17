package com.example.apiroy.Controller;

import com.example.apiroy.Model.TacGia;
import com.example.apiroy.Model.TheLoai;
import com.example.apiroy.Model.Truyen;
import com.example.apiroy.Repository.TacGiaRepos;
import com.example.apiroy.Service.TacGiaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/tacgia")
@RequiredArgsConstructor
public class TacGiaController {
    @Autowired
    private TacGiaService tacGiaService;

    @GetMapping()
    public List<TacGia> getAllTacGia() {
        return tacGiaService.getAllTacGia();
    }

    @GetMapping("{id}")
    public ResponseEntity<TacGia> getTacGiaById(@PathVariable(value = "id") Long id)
            throws Exception {

        return ResponseEntity.ok().body(tacGiaService.getTacGiaById(id));
    }

    @PostMapping()
    public TacGia createTacGia(@Valid @RequestBody TacGia tacGia) {
        return tacGiaService.createTacGia(tacGia);
    }

    @PutMapping("{id}")
    public ResponseEntity<TacGia> updateTacGia(@PathVariable(value = "id") Long id,
                                                 @Valid @RequestBody TacGia tacGiaDetails) throws Exception {
        return ResponseEntity.ok(tacGiaService.updateTacGia(id, tacGiaDetails));
    }

    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteTacGia(@PathVariable(value = "id") Long id)
            throws Exception {
        return tacGiaService.deleteTacGia(id);
    }

    //    id the loai
    @GetMapping("{id}/truyen/")
    public List<Truyen> getTruyenTheoTacGia(@PathVariable(value = "id") Long id) {
        return tacGiaService.getTruyenTheoTacGia(id);
    }

}
