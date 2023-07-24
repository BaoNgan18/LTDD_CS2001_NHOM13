package com.example.apiroy.Controller;

import com.example.apiroy.Model.TheLoai;
import com.example.apiroy.Model.Truyen;
import com.example.apiroy.Repository.TruyenRepos;
import com.example.apiroy.Service.TruyenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/truyen")
@RequiredArgsConstructor
public class TruyenController {
    @Autowired
    private TruyenService truyenService;

    @GetMapping()
    public List<Truyen> getAllTruyen() {
        return truyenService.getAllTruyen();
    }

    @GetMapping("{id}")
    public ResponseEntity<Truyen> getTruyenById(@PathVariable(value = "id") Long id)
            throws Exception {

        return ResponseEntity.ok().body(truyenService.getTruyenByID(id));
    }

    @PostMapping()
    public Truyen createTruyen(@Valid @RequestBody Truyen truyen) {
        return truyenService.createTruyen(truyen);
    }

    @PutMapping("{id}")
    public ResponseEntity<Truyen> updateTruyen(@PathVariable(value = "id") Long id,
                                                 @Valid @RequestBody Truyen truyenDetails) throws Exception {
        return ResponseEntity.ok(truyenService.updateTruyen(id, truyenDetails));
    }

    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteTruyen(@PathVariable(value = "id") Long id)
            throws Exception {
        return truyenService.deleteTruyen(id);
    }

//    @GetMapping("{id}/theloai/")
//    public List<Truyen> getTruyenTheoTheLoai(@PathVariable(value = "id") Long id) {
//        return truyenService.getDSTheLoaiTheoTruyen(id);
//    }

}
