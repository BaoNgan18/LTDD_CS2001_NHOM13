package app.demo.model;

import java.time.LocalDateTime;
import java.util.List;

public class User {
    private long id;

    private String userName;


    private String password;


    private String email;


    private LocalDateTime createdAt;


    private List<Book> listFavoriteBook;


    private List<Book> listBook;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Book> getListFavoriteBook() {
        return listFavoriteBook;
    }

    public void setListFavoriteBook(List<Book> listFavoriteBook) {
        this.listFavoriteBook = listFavoriteBook;
    }

    public List<Book> getListBook() {
        return listBook;
    }

    public void setListBook(List<Book> listBook) {
        this.listBook = listBook;
    }
}
