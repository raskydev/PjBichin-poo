package br.edu.ifmg.bichin.controller;

import br.edu.ifmg.bichin.model.Cliente;
import br.edu.ifmg.bichin.model.Pet;
import br.edu.ifmg.bichin.model.Servico;
import br.edu.ifmg.bichin.model.ServicoPrestado;
import br.edu.ifmg.bichin.repository.PetRepository;
import br.edu.ifmg.bichin.repository.ServicoRepository;
import br.edu.ifmg.bichin.service.ServicoPrestadoService;

import java.time.LocalDate;
import java.util.ArrayList;

public class ServicoPrestadoController {
    private PetRepository repositoryPet;
    private ServicoRepository repositoryServico;
    private ServicoPrestadoService service;

    public ServicoPrestadoController(
            PetRepository repositoryPet,
            ServicoRepository repositoryServico,
            ServicoPrestadoService service) {

        this.repositoryPet = repositoryPet;
        this.repositoryServico = repositoryServico;
        this.service = service;
    }

    public double maiorValorPorCliente(Cliente cliente) {
        return service.maiorValorPorCliente(cliente);
    }

    public boolean remover(int id) {
        return service.remover(id);
    }

    public ServicoPrestado buscar(int id) {
        return service.buscarPorId(id);
    }

    public ArrayList<Pet> buscarBichosPorNomeDono(String nome) {
        return service.buscarBichosPorNomeDono(nome);
    }

    public ArrayList<ServicoPrestado> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return service.buscarPorPeriodo(inicio, fim);
    }

    // As regras de negócio (data e valor) são validadas no service, que lança exceção se forem violadas
    public void adicionar(
            int id, int idServico, int idPet, double valor, LocalDate data) {

        Pet p = repositoryPet.buscarPorId(idPet);
        Servico s = repositoryServico.buscarPorId(idServico);

        if (service.buscarPorId(id) == null && p != null && s != null) {
            ServicoPrestado sp = new ServicoPrestado(id, s, valor, p, data);
            service.adicionar(sp);
        }
    }

    public ArrayList<ServicoPrestado> buscarPorCliente(Cliente cliente) {
        return service.buscarPorCliente(cliente);
    }

    public ArrayList<ServicoPrestado> listar() {
        return service.listar();
    }

    // Usados pela visão para confirmar que o serviço e o pet existem
    public Servico buscarServico(int idServico) {
        return repositoryServico.buscarPorId(idServico);
    }

    public Pet buscarPet(int idPet) {
        return repositoryPet.buscarPorId(idPet);
    }
}