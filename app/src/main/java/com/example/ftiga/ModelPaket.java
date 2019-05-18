package com.example.ftiga;

public class ModelPaket {
    private int icon;
    private String paket,harga,feedback;

    public ModelPaket(int icon, String paket, String harga, String feedback) {
        this.icon = icon;
        this.paket = paket;
        this.harga = harga;
        this.feedback = feedback;
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
}
