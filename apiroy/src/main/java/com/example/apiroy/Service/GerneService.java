package com.example.apiroy.Service;

import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.Gerne;

import java.util.List;
import java.util.Map;

public interface GerneService {
    List<Gerne> getAllGerne();
    Gerne getGerneById(Long id) throws Exception;
    Gerne createGerne(Gerne gerne);
    Gerne updateGerne(Long id, Gerne gerneDetails) throws Exception;
    Map<String, Boolean> deleteGerne(Long id) throws Exception;
    List<Book> getBookByGerne(Long id);
}
