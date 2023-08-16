package app.demo.model;


import java.util.List;

public class Book {
    private Long id;
    private String nameBook;
    private List<Gerne> listGerne;

    private String coverImg;
    private User user;

    private List<User> listUserPressingLove;

    private String describe;

    private String content;

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

    public List<Gerne> getListGerne() {
        return listGerne;
    }

    public void setListGerne(List<Gerne> listGerne) {
        this.listGerne = listGerne;
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

    public List<User> getListUserPressingLove() {
        return listUserPressingLove;
    }

    public void setListUserPressingLove(List<User> listUserPressingLove) {
        this.listUserPressingLove = listUserPressingLove;
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
}


