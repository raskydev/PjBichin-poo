package br.edu.ifmg.bichin.model;

import java.util.Objects;

public class Cliente {
    private int id;
    private String nome;
    private String telefone;

    public Cliente() {
    }

    public Cliente(int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Cliente " + id + " [" +
                "\n\tNome = " + nome +
                "\n\tTelefone = " + telefone +
                "\n]";
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id && Objects.equals(telefone, cliente.telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, telefone);
    }

    public void setId(int id) {
        this.id = id;
    }

}
