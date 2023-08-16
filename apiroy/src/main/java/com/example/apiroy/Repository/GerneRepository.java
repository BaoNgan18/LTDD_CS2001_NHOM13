package com.example.apiroy.Repository;

import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.Gerne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GerneRepository extends JpaRepository<Gerne,Long> {
    @Query("SELECT t FROM Book t JOIN t.listGerne tl ON tl.id = ?1")
    List<Book> getBookByGerne(Long id);
}
