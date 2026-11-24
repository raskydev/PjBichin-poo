package br.edu.ifmg.bichin.controller;

import br.edu.ifmg.bichin.model.Servico;
import br.edu.ifmg.bichin.repository.ServicoRepository;
import br.edu.ifmg.bichin.model.ServicoExterno;
import br.edu.ifmg.bichin.model.ServicoInterno;

import java.util.ArrayList;

public class ServicoController {
    private ServicoRepository repository;

    public ServicoController(ServicoRepository repository) {
        this.repository = repository;
    }


    public boolean remover(int id) {
        return repository.remover(id);
    }

    public void adicionar(int id, String descricao, double valor, String responsavel, double valorTerceirizado) {
    if (repository.buscarPorId(id) == null) {
        Servico s;

        // Sem CRMV informado: serviço externo. Com CRMV: serviço interno.
        if (responsavel == null || responsavel.isBlank()) {
            s = new ServicoExterno(id, descricao, valor, valorTerceirizado);
        } else {
            s = new ServicoInterno(id, descricao, valor, responsavel);
        }
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
