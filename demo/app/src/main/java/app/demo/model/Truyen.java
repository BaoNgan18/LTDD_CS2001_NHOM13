package app.demo.model;


import java.util.List;

public class Truyen {
    private Long id;
    private String tenTruyen;
    private List<TheLoai> dsTheLoai;
//    private String anhBia;
    private TacGia tacGia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public TacGia getTacGia() {
        return tacGia;
    }

    public void setTacGia(TacGia tacGia) {
        this.tacGia = tacGia;
    }


    public List<TheLoai> getDsTheLoai() {
        return dsTheLoai;
    }

    public void setDsTheLoai(List<TheLoai> dsTheLoai) {
        this.dsTheLoai = dsTheLoai;
    }

//    public String getAnhBia() {
//        return anhBia;
//    }

//    public void setAnhBia(String anhBia) {
//        this.anhBia = anhBia;

//

}


