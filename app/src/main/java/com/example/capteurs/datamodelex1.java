package com.example.capteurs;

public class datamodelex1 {

    int image ;
    String top , bottom ;

    public datamodelex1(int image, String top, String bottom) {
        this.image = image;
        this.top = top;
        this.bottom = bottom;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }
}
