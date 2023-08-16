package com.example.apiroy.Controller;


import com.example.apiroy.Model.Book;
import com.example.apiroy.Service.BookService;
import com.example.apiroy.Service.CoverImgService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/truyen")
@RequiredArgsConstructor
public class BookController {
    @Autowired
    private BookService bookService;
    private CoverImgService coverImgService;

    @GetMapping()
    public List<Book> getAllBook() {
        return bookService.getAllBook();
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBookByID(@PathVariable(value = "id") Long id)
            throws Exception {

        return ResponseEntity.ok().body(bookService.getBookByID(id));
    }

    @PostMapping("/dang-book")
    public ResponseEntity<Book> createBook(Book book, @RequestPart("coverImg")MultipartFile coverImg) throws IOException {
        Book bookMoi = bookService.createBook(book);
        String coverImgUrl = coverImgService.uploadImage(coverImg);
        return new ResponseEntity<>(bookMoi, HttpStatus.CREATED);
    }

    @PutMapping("{id}/cap-nhat-truyen")
    public ResponseEntity<Book> updateBook(@PathVariable(value = "id") Long id,
                                             @Valid @RequestBody Book bookDetails) throws Exception {
        return ResponseEntity.ok(bookService.updateBook(id, bookDetails));
    }

    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteBook(@PathVariable(value = "id") Long id)
            throws Exception {
        return bookService.deleteBook(id);
    }



}
