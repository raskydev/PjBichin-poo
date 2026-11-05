package br.edu.ifmg.bichin.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
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


    @Override
    public int hashCode() {
        return Objects.hash(id);
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
        return id == that.id;
    }

    public Pet getPet() {
        return pet;
    }

    public int getDiasDaExecucaoServico() {
        return (int) ChronoUnit.DAYS.between(data, LocalDate.now());
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
                "\n\tValor cobrado: %.2f".formatted(valorCobrado)+
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