package br.edu.ifmg.bichin.model;

// Espécies de pequeno porte aceitas pelo petshop 
public enum Especie {
    CAO("Cão"),
    GATO("Gato"),
    PASSARO("Pássaro"),
    PEIXE("Peixe");

    private final String descricao;

    Especie(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
