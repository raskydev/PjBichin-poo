package br.edu.ifmg.bichin.model;

import java.util.Objects;

public class Servico {
    private String descricao;
    private int id;
    private double valor;

    public Servico() {
    }

    public Servico(int id, String descricao, double valor) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return id == servico.id && Objects.equals(descricao, servico.descricao);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, id);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Serviço " + id + " [" +
                "\n\tValor: " + valor +
                "\n\tDescrição: " + descricao +
                "\n]";
    }

    public double getValor() {
        return valor;
    }

}
