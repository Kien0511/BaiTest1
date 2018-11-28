package com.example.nguyentrungkien.baitest1.DTO;

public class SinhVienDTO {
    String MSSV, hoTen, gioiTinh, diemTongKet;

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiemTongKet() {
        return diemTongKet;
    }

    public void setDiemTongKet(String diemTongKet) {
        this.diemTongKet = diemTongKet;
    }

    public SinhVienDTO() {

    }

    public SinhVienDTO(String MSSV, String hoTen, String gioiTinh, String diemTongKet) {

        this.MSSV = MSSV;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.diemTongKet = diemTongKet;
    }
}
