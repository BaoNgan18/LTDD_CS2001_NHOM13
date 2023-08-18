package com.example.apiroy.Controller;


import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.User;
import com.example.apiroy.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
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

//    @PostMapping("{id}/yeu_thich")
//    public Book addBookInFavorites(@Valid @RequestBody Book book, @PathVariable(value = "id") Long userId) {
//        return userService.addBookInFavorites(book, userId);
//    }

    @PostMapping("/{nguoidung_id}/yeuthich/{truyen_id}")
    public ResponseEntity<String> addBookInFavorites(
            @PathVariable(value = "nguoidung_id") Long userId,
            @PathVariable(value = "truyen_id") Long bookId) {
        try {
            userService.addBookInFavorites(userId, bookId);
            return ResponseEntity.ok("Đã thêm truyện vào mục yêu thích.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Thêm truyện vào mục yêu thích thất bại.");
        }
    }


    @DeleteMapping("{userId}/xoayeuthich/{bookId}")
    public ResponseEntity<String> removeBookFromFavorites(@PathVariable(value = "bookId") Long bookId,
                                                                       @PathVariable(value = "userId") Long userId) {
        try {
            userService.removeBookFromFavorites(bookId, userId);
            return ResponseEntity.ok("Đã xóa truyện khỏi mục yêu thích.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Xóa truyện khỏi mục yêu thích thất bại.");
        }
    }

}
