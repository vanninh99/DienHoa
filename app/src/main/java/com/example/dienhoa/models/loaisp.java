package com.example.dienhoa.models;

public class loaisp {
    public int Idloaisp;
    public String ten;
    public String img;



    public loaisp(int id, String ten, String img) {
        this.Idloaisp = id;
        this.ten = ten;
        this.img = img;
    }

    public int getIdloaisp() {
        return Idloaisp;
    }

    public void setIdloaisp(int id) {
        this.Idloaisp = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
