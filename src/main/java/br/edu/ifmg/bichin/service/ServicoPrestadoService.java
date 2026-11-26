package br.edu.ifmg.bichin.service;

import br.edu.ifmg.bichin.model.Cliente;
import br.edu.ifmg.bichin.model.Pet;
import br.edu.ifmg.bichin.model.ServicoPrestado;
import br.edu.ifmg.bichin.repository.ServicoPrestadoRepository;

import java.time.LocalDate;
import java.util.ArrayList;

// Camada de serviço: concentra as regras de negócio do serviço prestado
public class ServicoPrestadoService {
    private ServicoPrestadoRepository repository;

    public ServicoPrestadoService(ServicoPrestadoRepository repository) {
        this.repository = repository;
    }

    // Regras de negócio: sem data no passado e sem valor negativo
    public void adicionar(ServicoPrestado servicoPrestado) {
        if (servicoPrestado.getData().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Não é possível cadastrar um serviço prestado no passado!");
        }
        if (servicoPrestado.getValorCobrado() < 0) {
            throw new IllegalArgumentException(
                    "O valor cobrado não pode ser negativo!");
        }
        repository.adicionar(servicoPrestado);
    }

    // Os demais métodos não têm regra de negócio: só repassam para o repository
    public ServicoPrestado buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public ArrayList<ServicoPrestado> listar() {
        return repository.listar();
    }

    public boolean remover(int id) {
        return repository.remover(id);
    }

    public double maiorValorPorCliente(Cliente cliente) {
        return repository.maiorValorPorCliente(cliente);
    }

    public ArrayList<Pet> buscarBichosPorNomeDono(String nome) {
        return repository.buscarBichosPorNomeDono(nome);
    }

    public ArrayList<ServicoPrestado> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return repository.buscarPorPeriodo(inicio, fim);
    }

    public ArrayList<ServicoPrestado> buscarPorCliente(Cliente cliente) {
        return repository.buscarPorCliente(cliente);
    }
}