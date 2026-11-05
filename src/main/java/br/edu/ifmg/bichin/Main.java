package br.edu.ifmg.bichin;

import br.edu.ifmg.bichin.controller.ClienteController;
import br.edu.ifmg.bichin.controller.PetController;
import br.edu.ifmg.bichin.controller.ServicoController;
import br.edu.ifmg.bichin.controller.ServicoPrestadoController;
import br.edu.ifmg.bichin.repository.ClienteRepository;
import br.edu.ifmg.bichin.repository.PetRepository;
import br.edu.ifmg.bichin.repository.ServicoPrestadoRepository;
import br.edu.ifmg.bichin.repository.ServicoRepository;
import br.edu.ifmg.bichin.visao.cmd.*;

/*
    Trabalho prático - Programação Orientada a Objetos
    Grupo:
        Rayane Kaylla Venâncio Gomes Alves - 0117243
        Mateus Otacílio Silva Gomes - 0117242
*/

public class Main {
    public static void main(String[] args) {

        // Inicializando os repositórios das classes
        ClienteRepository clienteRepository =
                new ClienteRepository();

        PetRepository petRepository =
                new PetRepository();

        ServicoPrestadoRepository servicoPrestadoRepository =
                new ServicoPrestadoRepository();

        ServicoRepository servicoRepository =
                new ServicoRepository();

        // Carregando os dados dos arquivos
        clienteRepository.carregarDados("src/main/resources/dados/clientes.txt");
        petRepository.carregarDados("src/main/resources/dados/pets.txt", clienteRepository);
        servicoRepository.carregarDados("src/main/resources/dados/servicos.txt");
        servicoPrestadoRepository.carregarDados(
                "src/main/resources/dados/servicos_prestados.txt",
                petRepository, servicoRepository);

        // Criando os objetos das classes controllers
        ClienteController clienteController =
                new ClienteController(clienteRepository, petRepository);
        PetController petController =
                new PetController(petRepository, clienteRepository);
        ServicoController servicoController =
                new ServicoController(servicoRepository);
        ServicoPrestadoController servicoPrestadoController =
                new ServicoPrestadoController(
                        petRepository,
                        servicoRepository,
                        servicoPrestadoRepository);

        // Criando as classes de interface do usuário (UIs) informando a classe de controller que ela deve manipular
        EntradaCmd entrada = new EntradaCmd();
        UiCmdCliente uiCmdCliente = new UiCmdCliente(entrada, clienteController);
        UiCmdPet uiCmdPet = new UiCmdPet(entrada, clienteController, petController);
        UiCmdServico uiCmdServico = new UiCmdServico(entrada, servicoController);
        UiCmdServicoPrestado menuServicoPrestado = new UiCmdServicoPrestado(
                entrada,
                clienteController,
                servicoPrestadoController
        );

        // Criando a classe de interface do usuário principal (UI principal) informando as classes de UIs que ela deve manipular
        UI_Cmd UICmd =
                new UI_Cmd(
                        entrada,
                        uiCmdServico,
                        uiCmdPet,
                        uiCmdCliente,
                        menuServicoPrestado
                );

        // Gloria in excelsis Deo :)
        UICmd.iniciar();
    }
}