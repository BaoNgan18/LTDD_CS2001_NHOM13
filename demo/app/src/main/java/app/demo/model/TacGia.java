package app.demo.model;

public class TacGia {
    private Long id;
    private String tenTacGia;

    public String getTen() {
        return tenTacGia;
    }

    public void setTen(String ten) {
        this.tenTacGia = ten;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
