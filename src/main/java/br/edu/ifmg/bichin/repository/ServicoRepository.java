package br.edu.ifmg.bichin.repository;

import br.edu.ifmg.bichin.model.Servico;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import br.edu.ifmg.bichin.model.ServicoExterno;
import br.edu.ifmg.bichin.model.ServicoInterno;

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

                if (tokens.length == 5) {
                    int id = Integer.parseInt(tokens[0].trim());
                    String descricao = tokens[1];
                    double valor = Double.parseDouble(tokens[2].trim());
                    String tipo = tokens[3].trim().toUpperCase();

                    Servico servico;
                    if (tipo.equals("INTERNO")) {
                        servico = new ServicoInterno(id, descricao, valor, tokens[4].trim());
                    } else if (tipo.equals("EXTERNO")) {
                        servico = new ServicoExterno(id, descricao, valor, Double.parseDouble(tokens[4].trim()));
                    } else {
                        System.out.println("Linha ignorada! Tipo de serviço inválido: " + tokens[3]);
                        continue;
                    }
                    adicionar(servico);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(
                    "Erro no carregamento do arquivo!",
                    e);
        }
    }

}