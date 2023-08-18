package com.example.apiroy.Service.Impl;

import com.example.apiroy.Model.User;
import com.example.apiroy.Model.Book;
import com.example.apiroy.Repository.UserRepository;
import com.example.apiroy.Repository.BookRepository;
import com.example.apiroy.Service.UserService;
import com.example.apiroy.Service.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    @Autowired
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
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("Người dùng này không tồn tại: " + id));

        user.setUserName(userDetails.getUserName());
        user.setEmail(userDetails.getEmail());
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
    public List<Book> getListFavoriteBookByUser(Long id) {
        return userRepository.getListFavoriteBookByUser(id);
    }


    public Book addBookInFavorites(Long userId, Long bookId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found: " + userId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new Exception("Book not found: " + bookId));
        if(!user.getListFavoriteBook().contains(book)){
            System.out.println("[DEBUG] - User: " + user);
            System.out.println("[DEBUG] - Book: " + book);
            user.getListFavoriteBook().add(book);
            book.getListUserPressingLove().add(user);
            userRepository.save(user);
        }
        return book;
    }


    public Book removeBookFromFavorites(Long bookId, Long userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found: " + userId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new Exception("Book not found: " + bookId));

        if (user.getListFavoriteBook().contains(book)) {
            System.out.println("[DEBUG] - User: " + user);
            System.out.println("[DEBUG] - Book: " + book);
            user.getListFavoriteBook().remove(book);
            book.getListUserPressingLove().remove(user);
            userRepository.save(user);
        } else{
            throw new Exception();
        }
        return book;
    }

}
