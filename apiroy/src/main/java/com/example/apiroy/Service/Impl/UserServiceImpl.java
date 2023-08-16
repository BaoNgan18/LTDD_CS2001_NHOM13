package com.example.apiroy.Service.Impl;

import com.example.apiroy.Model.User;
import com.example.apiroy.Model.Book;
import com.example.apiroy.Repository.UserRepository;
import com.example.apiroy.Repository.BookRepository;
import com.example.apiroy.Service.UserService;
import com.example.apiroy.Service.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private BookService bookService;

    private BookRepository bookRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("Người dùng này không tồn tại: " + id));
        return user;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("Người dùng này không tồn tại: " + id));

        user.setUserName(userDetails.getUserName());
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    @Override
    public Map<String, Boolean> deleteUser(Long id) throws Exception{
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("Người dùng này không tồn tại: " + id));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    @Override
    public List<Book> getBookByUser(Long id) {
        return userRepository.getBookByUser(id);
    }

    @Override
    public Book postBook(Book book, Long userId) {
        User author = userRepository.findById(userId).get();
        book.setUser(author);
        return bookService.createBook(book);
    }

    @Override
    public List<Book> getListFavoriteBookByUser(Long id) {
        return userRepository.getListFavoriteBookByUser(id);
    }

    @Override
    public Book addBookInFavorites(Book book, Long userId) {
        User user = userRepository.findById(userId).get();
        user.getListBooks().add(book);
        book.getListUserPressingLove().add(user);
        userRepository.save(user);
        bookRepository.save(book);
        return book;
    }

    public Map<String, Boolean> removeBookFromFavorites(Book book, Long userId) throws Exception{
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Người dùng này không tồn tại: " + userId));

        user.getListFavoriteBook().remove(book);
        book.getListUserPressingLove().remove(user);
        userRepository.save(user);
        bookRepository.save(book);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
