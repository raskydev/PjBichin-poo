package br.edu.ifmg.bichin.controller;

import br.edu.ifmg.bichin.model.Cliente;
import br.edu.ifmg.bichin.model.Pet;
import br.edu.ifmg.bichin.model.Servico;
import br.edu.ifmg.bichin.model.ServicoPrestado;
import br.edu.ifmg.bichin.repository.PetRepository;
import br.edu.ifmg.bichin.repository.ServicoPrestadoRepository;
import br.edu.ifmg.bichin.repository.ServicoRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class ServicoPrestadoController {
    private PetRepository repositoryPet;
    private ServicoRepository repositoryServico;
    private ServicoPrestadoRepository repository;

    public ServicoPrestadoController(
            PetRepository repositoryPet,
            ServicoRepository repositoryServico,
            ServicoPrestadoRepository repository) {

        this.repositoryPet = repositoryPet;
        this.repositoryServico = repositoryServico;
        this.repository = repository;
    }


    public double maiorValorPorCliente(Cliente cliente) {
        return repository.maiorValorPorCliente(cliente);
    }

    public boolean removerServicoPrestado(int id) {
        return repository.remover(id);
    }

    public ServicoPrestado buscar(int id) {
        return repository.buscarPorId(id);
    }

    public ArrayList<Pet> buscarBichosPorNomeDono(String nome) {
        return repository.buscarBichosPorNomeDono(nome);
    }

    public ArrayList<ServicoPrestado> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return repository.buscarPorPeriodo(inicio, fim);
    }

    public void adicionar(
            int id, int idServico, int idPet, double valor, LocalDate data) {

        Pet     p = repositoryPet.buscarPorId(idPet);
        Servico s = repositoryServico.buscarPorId(idServico);

        if (repository.buscarPorId(id) == null && p != null && s != null) {
            ServicoPrestado sp = new ServicoPrestado(id, s, valor, p, data);
            repository.adicionar(sp);
        }
    }

    public ArrayList<ServicoPrestado> buscarPorCliente(Cliente cliente) {
        return repository.buscarPorCliente(cliente);
    }

    public ArrayList<ServicoPrestado> listar() {
        return repository.listar();
    }

}