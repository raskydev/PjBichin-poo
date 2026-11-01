package br.edu.ifmg.bichin.controller;

import br.edu.ifmg.bichin.model.Cliente;
import br.edu.ifmg.bichin.repository.ClienteRepository;

import java.util.ArrayList;

public class ClienteController {
    private ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }


    public Cliente buscar(int id) {
        return repository.buscarPorId(id);
    }

    public ArrayList<Cliente> listar() {
        return repository.listar();
    }

    public boolean remover(int id) {
        return repository.remover(id);
    }

    public void adicionar(int id, String nome, String telefone) {
        if (repository.buscarPorId(id) == null) {
            Cliente c = new Cliente(id, nome, telefone);
            repository.adicionar(c);
        }
    }

}
