package com.example.apiroy.Controller;

import com.example.apiroy.Model.TheLoai;
import com.example.apiroy.Model.Truyen;
import com.example.apiroy.Service.TheLoaiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/theloai")
@RequiredArgsConstructor
public class TheLoaiController {
    @Autowired
    private TheLoaiService theLoaiService;

    @GetMapping()
    public List<TheLoai> getAllTheLoai() {
        return theLoaiService.getAllTheLoai();
    }

    @GetMapping("{id}")
    public ResponseEntity<TheLoai> getTheLoaiById(@PathVariable(value = "id") Long id)
            throws Exception {

        return ResponseEntity.ok().body(theLoaiService.getTheLoaiById(id));
    }

    @PostMapping()
    public TheLoai createTheLoai(@Valid @RequestBody TheLoai theLoai) {
        return theLoaiService.createTheLoai(theLoai);
    }

    @PutMapping("{id}")
    public ResponseEntity<TheLoai> updateTheLoai(@PathVariable(value = "id") Long id,
                                               @Valid @RequestBody TheLoai theLoaiDetails) throws Exception {
        return ResponseEntity.ok(theLoaiService.updateTheLoai(id, theLoaiDetails));
    }

    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteTheLoai(@PathVariable(value = "id") Long id)
            throws Exception {
        return theLoaiService.deleteTheLoai(id);
    }

    // id the loai
    @GetMapping("{id}/truyen/")
    public List<Truyen> getTruyenTheoTheLoai(@PathVariable(value = "id") Long id) {
        return theLoaiService.getTruyenTheoTheLoai(id);
    }
}
