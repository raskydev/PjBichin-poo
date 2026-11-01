package br.edu.ifmg.bichin.controller;

import br.edu.ifmg.bichin.model.Cliente;
import br.edu.ifmg.bichin.model.Pet;
import br.edu.ifmg.bichin.repository.ClienteRepository;
import br.edu.ifmg.bichin.repository.PetRepository;

import java.util.ArrayList;

public class ClienteController {
    private ClienteRepository repository;
    private PetRepository petRepository;

    public ClienteController(
            ClienteRepository repository,
            PetRepository petRepository) {

        this.repository = repository;
        this.petRepository = petRepository;
    }


    public Cliente buscar(int id) {
        return repository.buscarPorId(id);
    }

    public ArrayList<Cliente> listar() {
        return repository.listar();
    }

    // Modificação: Se há pets ainda associados ao cliente, não deixa excluir o cliente
    public boolean remover(int id) {
        ArrayList<Pet> pets = petRepository.listar();
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getCliente().getId() == id) {
                return false;
            }
        }
        return repository.remover(id);
    }

    public void adicionar(int id, String nome, String telefone) {
        if (repository.buscarPorId(id) == null) {
            Cliente c = new Cliente(id, nome, telefone);
            repository.adicionar(c);
        }
    }

}
