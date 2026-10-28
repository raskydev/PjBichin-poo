package br.edu.ifmg.bichin.model;

import java.util.Objects;

public class Servico {
    private String descricao;
    private int id;
    private float valor;

    public Servico() {
    }

    public Servico(int id, String descricao, float valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return id == servico.id && Objects.equals(descricao, servico.descricao);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int hashCode() {
        return Objects.hash(descricao, id);
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Serviço " + id + " [" +
                "\n\tValor: " + valor +
                "\n\tDescrição: " + descricao +
                "\n]";
    }

    public float getValor() {
        return valor;
    }

}
