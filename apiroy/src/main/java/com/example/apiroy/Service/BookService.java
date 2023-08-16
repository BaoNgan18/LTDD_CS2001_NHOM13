package com.example.apiroy.Service;

import com.example.apiroy.Model.Book;

import java.util.List;
import java.util.Map;

public interface BookService {

    List<Book> getAllBook();

    Book getBookByID(Long id) throws  Exception;

    Book createBook(Book book);

    Book updateBook(Long id, Book bookDetails) throws Exception;

    Map<String, Boolean> deleteBook(Long id) throws Exception;

//    List<Book> getDSTheLoaiTheoTruyen(Long id);
}
