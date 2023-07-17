package com.example.apiroy.Controller;


import com.example.apiroy.Model.NguoiDung;
import com.example.apiroy.Repository.NguoiDungRepos;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/nguoidung")
public class NguoiDungController {

    @Autowired
    private NguoiDungRepos nguoiDungRepos;

    @GetMapping()
    public List<NguoiDung> getAllUser() {
        return nguoiDungRepos.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<NguoiDung> getUserById(@PathVariable(value = "id") Long id)
            throws Exception {

        NguoiDung nguoiDung = nguoiDungRepos.findById(id).orElseThrow(() -> new Exception("Người dùng này không tồn tại: " + id));

        return ResponseEntity.ok().body(nguoiDung);
    }

    @PostMapping()
    public NguoiDung createUser(@Valid @RequestBody NguoiDung user) {
        return nguoiDungRepos.save(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<NguoiDung> updateUser(@PathVariable(value = "id") Long id,
                                                @Valid @RequestBody NguoiDung nguoiDungDetails) throws Exception {
        NguoiDung nguoiDung = nguoiDungRepos.findById(id)
                .orElseThrow(() -> new Exception("Người dùng này không tồn tại: " + id));

        nguoiDung.setId(nguoiDungDetails.getId());
        nguoiDung.setUsername(nguoiDungDetails.getUsername());
        nguoiDung.setPassword(nguoiDungDetails.getPassword());
        nguoiDung.setEmail(nguoiDungDetails.getEmail());
//        user.setDsTruyen(nguoiDungDetails.getDsTruyen());

        final NguoiDung updatedNguoiDung = nguoiDungRepos.save(nguoiDung);

        return ResponseEntity.ok(updatedNguoiDung);
    }

    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id)
            throws Exception {

        NguoiDung nguoiDung = nguoiDungRepos.findById(id)
                .orElseThrow(() -> new Exception("Người dùng này không tồn tại: " + id));

        nguoiDungRepos.delete(nguoiDung);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
