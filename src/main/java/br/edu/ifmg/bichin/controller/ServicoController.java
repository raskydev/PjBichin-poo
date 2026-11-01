package br.edu.ifmg.bichin.controller;

import br.edu.ifmg.bichin.model.Servico;
import br.edu.ifmg.bichin.repository.ServicoRepository;

import java.util.ArrayList;

public class ServicoController {
    private ServicoRepository repository;

    public ServicoController(ServicoRepository repository) {
        this.repository = repository;
    }


    public boolean remover(int id) {
        return repository.remover(id);
    }

    public void adicionar(int id, String descricao, double valor) {
        if (repository.buscarPorId(id) == null) {
            Servico s = new Servico(id, descricao, valor);
            repository.adicionar(s);
        }
    }

    public ArrayList<Servico> listar() {
        return repository.listar();
    }

    public Servico buscar(int id) {
        return repository.buscarPorId(id);
    }
}
