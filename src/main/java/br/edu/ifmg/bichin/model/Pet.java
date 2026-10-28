package br.edu.ifmg.bichin.model;

import java.time.LocalDate;
import java.util.Objects;

public class Pet {
    private int id;
    private String nome;
    private String especie;
    private String raca;
    private Cliente cliente;
    private LocalDate nascimento;

    public Pet() {
        this.cliente = new Cliente();
    }

    public Pet(int id, String nome, String especie, String raca, Cliente cliente, LocalDate nascimento) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.cliente = cliente;
        this.nascimento = nascimento;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getIdade() {
        return (int) LocalDate.now().getYear() - this.nascimento.getYear();
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNome() {
        return nome;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "Pet " + id + " {" +
                "\n\tNome: " + nome +
                "\n\tEspécie: " + especie +
                "\n\tRaça: " + raca +
                "\n\tCliente: " + cliente +
                "\n\tNascimento: " + nascimento +
                "\n\tIdade: " + getIdade() + " anos" +
                "\n}";
    }

    public int getId() {
        return id;
    }

    public int hashCode() {
        return Objects.hash(id, cliente, nascimento);
    }

    public String getEspecie() {
        return especie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRaca() {
        return raca;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id == pet.id && Objects.equals(cliente, pet.cliente) && Objects.equals(nascimento, pet.nascimento);
    }

}
