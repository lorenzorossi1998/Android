package com.classes.objects;

import java.util.ArrayList;

public class Capo {
    private String marca;
    private String modello;
    private String categoria;
    private double voto;
    private double prezzo;
    private String valuta;
    private ArrayList<String> descrizione;
    private String productURL;
    private String imageURL;

    public Capo() {

    }

    public Capo(String marca, String modello, String categoria, double voto, double prezzo, String valuta, ArrayList<String> descrizione, String productURL, String imageURL) {
        this.marca = marca;
        this.modello = modello;
        this.categoria = categoria;
        this.voto = voto;
        this.prezzo = prezzo;
        this.valuta = valuta;
        this.descrizione = descrizione;
        this.productURL = productURL;
        this.imageURL = imageURL;
    }

    public String getMarca() {
        return marca;
    }

    public String getModello() {
        return modello;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getVoto() {
        return voto;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public String getValuta() {
        return valuta;
    }

    public ArrayList<String> getDescrizione() {
        return descrizione;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getProductURL() {
        return productURL;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setVoto(double voto) {
        this.voto = voto;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public void setDescrizione(ArrayList<String> descrizione) {
        this.descrizione = descrizione;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setProductURL(String productURL) {
        this.productURL = productURL;
    }

    @Override
    public String toString() {
        return "Capo{" +
                "marca='" + marca + '\'' +
                ", modello='" + modello + '\'' +
                ", categoria='" + categoria + '\'' +
                ", voto=" + voto +
                ", descrizione=" + descrizione +
                ", productURL='" + productURL + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
