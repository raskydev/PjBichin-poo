package br.edu.ifmg.bichin.repository;

import br.edu.ifmg.bichin.model.Servico;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ServicoRepository {
    private ArrayList<Servico> servicos;

    public ServicoRepository() {
        servicos = new ArrayList<Servico>();
    }

    public Servico buscarPorId(int id) {
        for (int i = 0; i < servicos.size(); i++) {
            if (servicos.get(i).getId() == id) {
                return servicos.get(i);
            }
        }
        return null;
    }

    public ArrayList<Servico> listar() {
        ArrayList<Servico> listaServico = new ArrayList<Servico>(servicos);

        return listaServico;
    }

    public void adicionar(Servico servico) {
        servicos.add(servico);
    }

    public boolean remover(int id) {
        for (int i = 0; i < servicos.size(); i++) {
            if (servicos.get(i).getId() == id) {
                servicos.remove(i);
                return true;
            }
        }
        return false;
    }

    public void carregarDados(String arquivo) {
        File f = new File(arquivo);

        try (Scanner a = new Scanner(f)) {
            a.nextLine();
            while (a.hasNextLine()) {
                String linha = a.nextLine();
                String[] tokens = linha.split("\\;");

                if (tokens.length == 3){
                    Servico servico = new Servico(
                            Integer.parseInt(tokens[0].trim()),
                            tokens[1],
                            Double.parseDouble(tokens[2].trim())
                    );
                    adicionar(servico);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(
                    "Erro no carregamento do arquivo!",
                    e
            );
        }
    }

}