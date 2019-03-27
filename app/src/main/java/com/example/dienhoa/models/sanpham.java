package com.example.dienhoa.models;

import java.io.Serializable;

public class sanpham implements Serializable {
    public int flowerid;
    public String flowername;
    public String flowerimage;
    public double price;
    public int idloaisp;

    public sanpham(int flowerid, String flowername, String flowerimage, double price, int idloaisp) {
        this.flowerid = flowerid;
        this.flowername = flowername;
        this.flowerimage = flowerimage;
        this.price = price;
        this.idloaisp = idloaisp;
    }

    public int getFlowerid() {
        return flowerid;
    }

    public void setFlowerid(int flowerid) {
        this.flowerid = flowerid;
    }

    public String getFlowername() {
        return flowername;
    }

    public void setFlowername(String flowername) {
        this.flowername = flowername;
    }

    public String getFlowerimage() {
        return flowerimage;
    }

    public void setFlowerimage(String flowerimage) {
        this.flowerimage = flowerimage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIdloaisp() {
        return idloaisp;
    }

    public void setIdloaisp(int idloaisp) {
        this.idloaisp = idloaisp;
    }

}
