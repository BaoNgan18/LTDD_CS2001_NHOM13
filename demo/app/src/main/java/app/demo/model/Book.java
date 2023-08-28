package app.demo.model;


import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
    private Long id;
    private String nameBook;
    private List<Genre> listGenre;
    private String coverImg;
    private User user;
    private String describe;
    private String content;
    private List<User> listUserPressingLove;

    private List<Chapter> listChapter;


    public Book(String nameBook, User author, String describe, String content, String coverImg, List<Genre> listGenre){
        this.setNameBook(nameBook);
        this.setUser(author);
        this.setDescribe(describe);
        this.setContent(content);
        this.setCoverImg(coverImg);
        this.setListGenre(listGenre);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public List<Genre> getListGenre() {
        return listGenre;
    }

    public void setListGenre(List<Genre> listGenre) {
        this.listGenre = listGenre;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<User> getListUserPressingLove() {
        return listUserPressingLove;
    }

    public void setListUserPressingLove(List<User> listUserPressingLove) {
        this.listUserPressingLove = listUserPressingLove;
    }

    public List<Chapter> getListChapter() {
        return listChapter;
    }

    public void setListChapter(List<Chapter> listChapter) {
        this.listChapter = listChapter;
    }
}


