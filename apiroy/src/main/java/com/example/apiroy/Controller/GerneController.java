package com.example.apiroy.Controller;

import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.Gerne;
import com.example.apiroy.Service.GerneService;
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
public class GerneController {
    @Autowired
    private GerneService gerneService;

    @GetMapping()
    public List<Gerne> getAllGerne() {
        return gerneService.getAllGerne();
    }

    @GetMapping("{id}")
    public ResponseEntity<Gerne> getGerneById(@PathVariable(value = "id") Long id)
            throws Exception {

        return ResponseEntity.ok().body(gerneService.getGerneById(id));
    }

    @PostMapping()
    public Gerne createGerne(@Valid @RequestBody Gerne gerne) {
        return gerneService.createGerne(gerne);
    }

    @PutMapping("{id}")
    public ResponseEntity<Gerne> updateGerne(@PathVariable(value = "id") Long id,
                                               @Valid @RequestBody Gerne gerneDetails) throws Exception {
        return ResponseEntity.ok(gerneService.updateGerne(id, gerneDetails));
    }

    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteGerne(@PathVariable(value = "id") Long id)
            throws Exception {
        return gerneService.deleteGerne(id);
    }

    // id the loai
    @GetMapping("{id}/truyen/")
    public List<Book> getBookByGerne(@PathVariable(value = "id") Long id) {
        return gerneService.getBookByGerne(id);
    }
}
