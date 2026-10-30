package br.edu.ifmg.bichin.repository;

import br.edu.ifmg.bichin.model.Cliente;
import br.edu.ifmg.bichin.model.Pet;
import br.edu.ifmg.bichin.model.Servico;
import br.edu.ifmg.bichin.model.ServicoPrestado;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ServicoPrestadoRepository {
    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private ArrayList<ServicoPrestado> servicoPrestado;

    public ServicoPrestadoRepository() {
        servicoPrestado = new ArrayList<ServicoPrestado>();
    }


    public ArrayList<ServicoPrestado> listar() {
        ArrayList<ServicoPrestado> listaServicoPrestado =
                new ArrayList<ServicoPrestado>(servicoPrestado);

        return listaServicoPrestado;
    }

    public void carregarDados(
            String arquivo,
            PetRepository petRepository,
            ServicoRepository servicoRepository) {

        File f = new File(arquivo);

        try (Scanner a = new Scanner(f)) {
            a.nextLine();
            while (a.hasNextLine()) {
                String linha = a.nextLine();
                String[] tokens = linha.split("\\;");

                if (tokens.length == 5) {
                    Servico s = servicoRepository.buscarPorId(Integer.parseInt(tokens[1].trim()));
                    Pet     p = petRepository.buscarPorId(Integer.parseInt(tokens[3].trim()));

                    if (s != null && p != null) {
                        ServicoPrestado servicoPrestado = new ServicoPrestado(
                                Integer.parseInt(tokens[0].trim()),
                                s,
                                Double.parseDouble(tokens[2]),
                                p,
                                LocalDate.parse(tokens[4], FORMATO_DATA)
                        );
                        adicionar(servicoPrestado);
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

    public ArrayList<ServicoPrestado> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        ArrayList<ServicoPrestado> resultado = new ArrayList<ServicoPrestado>();

        for (int i = 0; i < servicoPrestado.size(); i++) {
            if (!servicoPrestado.get(i).getData().isBefore(inicio) &&
                    !servicoPrestado.get(i).getData().isAfter(fim)) {

                resultado.add(servicoPrestado.get(i));
            }
        }
        return resultado;
    }

    public boolean remover(int id) {
        for (int i = 0; i < servicoPrestado.size(); i++) {
            if (servicoPrestado.get(i).getId() == id) {
                servicoPrestado.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Pet> buscarBichosPorNomeDono(String nome) {
        ArrayList<Pet> resultado = new ArrayList<Pet>();

        for (int i = 0; i < servicoPrestado.size(); i++) {
            Pet p = servicoPrestado.get(i).getPet();
            if (p.getCliente().getNome().equalsIgnoreCase(nome) &&
                    !resultado.contains(p)) {

                resultado.add(p);
            }
        }
        return resultado;
    }

    public ServicoPrestado buscarPorId(int id) {
        for (int i = 0; i < servicoPrestado.size(); i++) {
            if (servicoPrestado.get(i).getId() == id) {
                return servicoPrestado.get(i);
            }
        }
        return null;
    }

    public ArrayList<ServicoPrestado> buscarPorCliente(Cliente cliente) {
        ArrayList<ServicoPrestado> resultado = new ArrayList<ServicoPrestado>();

        for (int i = 0; i < servicoPrestado.size(); i++) {
            if (servicoPrestado.get(i).getPet().getCliente().equals(cliente)) {
                resultado.add(servicoPrestado.get(i));
            }
        }
        return resultado;
    }

    public double maiorValorPorCliente(Cliente cliente) {
        ArrayList<ServicoPrestado> servicosCliente = buscarPorCliente(cliente);
        double maior = 0;

        for (int i = 0; i < servicosCliente.size(); i++) {
            if (servicosCliente.get(i).getValorCobrado() > maior) {
                maior = servicosCliente.get(i).getValorCobrado();
            }
        }
        return maior;
    }

    public void adicionar(ServicoPrestado servicoPrestado) {
        this.servicoPrestado.add(servicoPrestado);
    }

}