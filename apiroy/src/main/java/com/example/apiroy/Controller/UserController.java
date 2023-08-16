package com.example.apiroy.Controller;


import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.User;
import com.example.apiroy.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/nguoidung")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id)
            throws Exception {

        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping()
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id,
                                                @Valid @RequestBody User userDetails) throws Exception {
        return ResponseEntity.ok(userService.updateUser(id, userDetails));
    }

    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id)
            throws Exception {
        return userService.deleteUser(id);
    }

    @GetMapping("{id}/truyenyeuthich/")
    public List<Book> getListFavoriteBookByUser(@PathVariable(value = "id") Long id) {
        return userService.getListFavoriteBookByUser(id);
    }

    @GetMapping("{id}/truyen/")
    public List<Book> getBookByUser(@PathVariable(value = "id") Long id) {
        return userService.getBookByUser(id);
    }

    @PostMapping("{id}/dang_truyen")
    public Book postBook(@Valid @RequestBody Book book, @PathVariable(value = "id") Long userId){
        return userService.postBook(book,userId);
    }
    @PostMapping("{id}/yeu_thich")
    public Book addBookInFavorites(@Valid @RequestBody Book book, @PathVariable(value = "id")Long userId){
        return userService.addBookInFavorites(book, userId);
    }

    @DeleteMapping("{id}/xoa_yeu_thich")
    public Map<String, Boolean> removeBookFromFavorites(@Valid @RequestBody Book book, @PathVariable(value = "id") Long id)
            throws Exception {
        return userService.removeBookFromFavorites(book, id);
    }
}
