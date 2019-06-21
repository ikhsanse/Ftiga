package com.example.ftiga;

public class ItemPaket {
    private int icon;
    private String id,paket,harga,feedback,id_ide;

    public ItemPaket() {
        this.id = id;
        this.icon = icon;
        this.paket = paket;
        this.harga = harga;
        this.feedback = feedback;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getId_ide() {
        return id_ide;
    }

    public void setId_ide(String id_ide) {
        this.id_ide = id_ide;
    }
}
