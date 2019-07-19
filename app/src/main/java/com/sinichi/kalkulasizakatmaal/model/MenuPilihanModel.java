package com.sinichi.kalkulasizakatmaal.model;

public class MenuPilihanModel {

    String nama;
    String deskripsi;
    Class<?> klass;
    int image;

    public MenuPilihanModel(int image, String nama, String deskripsi, Class<?> klass) {
        this.image = image;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.klass = klass;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Class<?> getKlass() {
        return klass;
    }

    public void setKlass(Class<?> klass) {
        this.klass = klass;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
