package app.demo.model;


import java.util.List;

public class Truyen {
    private Long id;
    private String tenTruyen;
    private List<TheLoai> dsTheLoai;

    private String anhBia;
    private NguoiDung nguoiDung;

    private List<NguoiDung> dsNguoiDungYeuThich;

    private String moTaTruyen;

    private String noiDungChuong;

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

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setTacGia(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }


    public List<TheLoai> getDsTheLoai() {
        return dsTheLoai;
    }

    public void setDsTheLoai(List<TheLoai> dsTheLoai) {
        this.dsTheLoai = dsTheLoai;
    }

    public String getAnhBia() {
        return anhBia;
    }

    public void setAnhBia(String anhBia) {
        this.anhBia = anhBia;
    }

    public List<NguoiDung> getDsNguoiDungYeuThich() {
        return dsNguoiDungYeuThich;
    }

    public void setDsNguoiDungYeuThich(List<NguoiDung> dsNguoiDungYeuThich) {
        this.dsNguoiDungYeuThich = dsNguoiDungYeuThich;
    }

    public String getMoTaTruyen() {
        return moTaTruyen;
    }

    public void setMoTaTruyen(String moTaTruyen) {
        this.moTaTruyen = moTaTruyen;
    }

    public String getNoiDungChuong() {
        return noiDungChuong;
    }

    public void setNoiDungChuong(String noiDungChuong) {
        this.noiDungChuong = noiDungChuong;
    }


}


