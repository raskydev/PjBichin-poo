package br.edu.ifmg.bichin.repository;

import br.edu.ifmg.bichin.model.Cliente;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClienteRepository {
    private ArrayList<Cliente> clientes;

    public ClienteRepository() {
        clientes = new ArrayList<Cliente>();
    }


    public void adicionar(Cliente cliente) {
        clientes.add(cliente);
    }

    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>(clientes);

        return listaClientes;

    }

    public Cliente buscarPorId(int id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                return clientes.get(i);
            }
        }
        return null;
    }

    public boolean remover(int id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                clientes.remove(i);
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
                    Cliente cliente = new Cliente(
                            Integer.parseInt(tokens[0].trim()),
                            tokens[1],
                            tokens[2]
                    );
                    adicionar(cliente);
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