package com.example.apiroy.Controller;


import com.example.apiroy.Model.Truyen;
import com.example.apiroy.Service.TruyenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/dang-truyen")
    public ResponseEntity<Truyen> createTruyen(@RequestBody Truyen truyen) {
        Truyen truyenMoi = truyenService.createTruyen(truyen);
        return new ResponseEntity<>(truyenMoi, HttpStatus.CREATED);
    }

    @PutMapping("{id}/cap-nhat-truyen")
    public ResponseEntity<Truyen> updateTruyen(@PathVariable(value = "id") Long id,
                                                 @Valid @RequestBody Truyen truyenDetails) throws Exception {
        return ResponseEntity.ok(truyenService.updateTruyen(id, truyenDetails));
    }

    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteTruyen(@PathVariable(value = "id") Long id)
            throws Exception {
        return truyenService.deleteTruyen(id);
    }



}
