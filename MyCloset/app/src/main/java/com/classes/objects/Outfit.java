package com.classes.objects;

import java.util.ArrayList;

public class Outfit extends ArrayList<Capo> {
    private String nome;
    private String stagione;

    public Outfit(String nome, String stagione) {
        this.nome = nome;
        this.stagione = stagione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStagione() {
        return stagione;
    }

    public void setStagione(String stagione) {
        this.stagione = stagione;
    }
}
