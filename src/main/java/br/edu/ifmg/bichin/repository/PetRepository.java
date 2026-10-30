package br.edu.ifmg.bichin.repository;

import br.edu.ifmg.bichin.model.Cliente;
import br.edu.ifmg.bichin.model.Pet;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class PetRepository {
    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private ArrayList<Pet> pets;

    public PetRepository() {
        pets = new ArrayList<Pet>();
    }


    public boolean remover(int id) {
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getId() == id) {
                pets.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Pet> listar() {
        ArrayList<Pet> listaPet = new ArrayList<Pet>(pets);

        return listaPet;
    }

    public void adicionar(Pet pet) {
        pets.add(pet);
    }

    public void carregarDados(String arquivo, ClienteRepository clienteRepository) {
        File f = new File(arquivo);

        try (Scanner a = new Scanner(f)) {
            a.nextLine();
            while (a.hasNextLine()) {
                String linha = a.nextLine();
                String[] tokens = linha.split("\\;");

                if (tokens.length == 6) {
                    Cliente c = clienteRepository.buscarPorId(Integer.parseInt(tokens[4].trim()));
                    if (c != null) {
                        Pet pet = new Pet(
                            Integer.parseInt(tokens[0].trim()),
                            tokens[1],
                            tokens[2],
                            tokens[3],
                            c,
                            LocalDate.parse(tokens[5], FORMATO_DATA)
                        );
                        adicionar(pet);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(
                    "Erro no carregamento do arquivo!",
                    e
            );
        }
    }

    public Pet buscarPorId(int id) {
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getId() == id) {
                return pets.get(i);
            }
        }
        return null;
    }

}