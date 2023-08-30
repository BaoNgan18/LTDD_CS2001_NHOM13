package app.demo.model;

import java.io.Serializable;

public class Chapter implements Serializable {
    public Chapter(Book book, String chapterName, String content) {
        this.book = book;
        this.chapterName = chapterName;
        this.content = content;
    }

    private Long id;
    private Book book;
    private String chapterName;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
