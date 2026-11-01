package br.edu.ifmg.bichin.controller;

import br.edu.ifmg.bichin.model.Cliente;
import br.edu.ifmg.bichin.model.Pet;
import br.edu.ifmg.bichin.repository.ClienteRepository;
import br.edu.ifmg.bichin.repository.PetRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class PetController {
    private PetRepository repository;
    private ClienteRepository repositoryCliente;

    public PetController(PetRepository repository, ClienteRepository repositoryCliente) {
        this.repository = repository;
        this.repositoryCliente = repositoryCliente;
    }


    public ArrayList<Pet> listar() {
        return repository.listar();
    }

    public boolean remover(int id) {
        return repository.remover(id);
    }

    public void adicionar(
            int id, String nome, String especie, String raca, LocalDate data, int clienteId) {

        Cliente c = repositoryCliente.buscarPorId(clienteId);

        if (repository.buscarPorId(id) == null && c != null) {
            Pet p = new Pet(id, nome, especie, raca, c, data);
            repository.adicionar(p);
        }
    }

    public Pet buscar(int id) {
        return repository.buscarPorId(id);
    }

}
