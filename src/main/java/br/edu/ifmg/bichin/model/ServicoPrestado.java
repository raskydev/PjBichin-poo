package br.edu.ifmg.bichin.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class ServicoPrestado {
    private int id;
    private Servico servico;
    private double valorCobrado;
    private Pet pet;
    private LocalDate data;

    public ServicoPrestado() {
    }

    public ServicoPrestado(int id, Servico servico, double valorCobrado, Pet pet, LocalDate data) {
        this.id = id;
        this.servico = servico;
        this.valorCobrado = valorCobrado;
        this.pet = pet;
        this.data = data;
    }

    public ServicoPrestado(int id, Servico servico, double valorCobrado, Pet pet) {
        this(id, servico, valorCobrado, pet, LocalDate.now());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, pet);
    }

    public Servico getServico() {
        return servico;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ServicoPrestado that = (ServicoPrestado) o;
        return id == that.id && Objects.equals(data, that.data) && Objects.equals(pet, that.pet);
    }

    public Pet getPet() {
        return pet;
    }

    public int getDiasDaExecucaoServico() {
        Period periodo = Period.between(LocalDate.now(), data);

        return periodo.getDays();

    }

    public void setValorCobrado(double valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    @Override
    public String toString() {
        return "Serviço prestado " + id + " {" +
                "\n\tServiço: " + servico.toString() +
                "\n\tData: " + data +
                "\n\tValor cobrado: " + valorCobrado +
                "\n\tPet: " + pet.toString() +
                "\n}";
    }

    public LocalDate getData() {
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValorCobrado() {
        return valorCobrado;
    }

    public int getId() {
        return id;
    }
}
