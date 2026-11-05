package br.edu.ifmg.bichin.visao.cmd;

import br.edu.ifmg.bichin.controller.ClienteController;
import br.edu.ifmg.bichin.controller.PetController;
import br.edu.ifmg.bichin.model.Pet;

import java.time.LocalDate;
import java.util.ArrayList;

public class UiCmdPet {
    private EntradaCmd entrada;
    private ClienteController clienteController;
    private PetController controller;

    public UiCmdPet(
            EntradaCmd entrada,
            ClienteController clienteController,
            PetController controller) {

        this.entrada = entrada;
        this.clienteController = clienteController;
        this.controller = controller;
    }

    public void iniciar() {
        int op;
        do {
            System.out.print("""
                    ┌────────────────────┐
                    │         Pet        │
                    ├────────────────────┤
                    │1. Adicionar        │
                    │2. Alterar          │
                    │3. Apagar           │
                    │4. Listar           │
                    │5. Buscar           │
                    │0. Voltar           │
                    └────────────────────┘
                    """);
            op = entrada.lerInteiro("Digite uma opção: ");

            switch (op) {
                case 1:
                    adicionar();
                break;

                case 2:
                    alterar();
                break;

                case 3:
                    apagar();
                break;

                case 4:
                    listar();
                break;

                case 5:
                    buscar();
                break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                break;

                default:
                    System.out.println("Opção inválida! ");
                break;
            }
        } while (op != 0);
    }

    private void adicionar() {
        while (true) {
            int id = entrada.lerInteiro("Digite um ID: ");
            Pet p = controller.buscar(id);

            if (p == null) {
                String nome = entrada.lerString("Digite o nome: ");
                String especie = entrada.lerString("Digite a espécie: ");
                String raca = entrada.lerString("Digite a raça: ");
                LocalDate data = entrada.lerData("Digite a data de nascimento: ");
                int idCliente;

                while (true) {
                    idCliente = entrada.lerInteiro("Digite o ID do cliente: ");

                    if (clienteController.buscar(idCliente) == null) {
                        System.out.printf("Cliente [%d] não encontrado... \n", idCliente);
                        if (entrada.lerConfirmacao(
                                "Cadastrar um novo cliente? Se não, tente outro ID (s/n): ")) {

                            adicionarCliente(idCliente);
                            break;
                        }
                    } else {
                        break;
                    }
                }
                controller.adicionar(id, nome, especie, raca, data, idCliente);
                System.out.printf("Pet [%d] cadastrado com sucesso!\n", id);
                break;
            } else {
                System.out.println("Erro! ID já existe! Tente novamente.\n");
            }
        }
    }

    private void listar() {
        ArrayList<Pet> pets = controller.listar();

        if (!pets.isEmpty()) {
            System.out.println("Listando todos os pets existentes: ");
            for (int i = 0; i < pets.size(); i++) {
                System.out.println(pets.get(i).toString());
            }
        } else {
            System.out.println("Nenhum pet cadastrado!");
        }
    }

    private void adicionarCliente(int idCliente) {
        String nome = entrada.lerString("Digite o nome: ");
        String telefone = entrada.lerTelefone("Digite o telefone: ");

        clienteController.adicionar(idCliente, nome, telefone);
        System.out.printf("Cliente [%d] cadastrado com sucesso!\n", idCliente);
    }

    private void alterar() {
        int id = entrada.lerInteiro("Digite o ID do pet que deseja alterar: ");

        Pet p = controller.buscar(id);

        if (p != null) {
            String novoNome = entrada.lerString("Digite o novo nome: ");
            String novaEspecie = entrada.lerString("Digite a nova espécie: ");
            String novaRaca = entrada.lerString("Digite a nova raça: ");
            LocalDate novaData = entrada.lerData("Digite a nova data de nascimento: ");

            p.setNome(novoNome);
            p.setEspecie(novaEspecie);
            p.setRaca(novaRaca);
            p.setNascimento(novaData);

            System.out.printf("\tPet [%d] alterado com sucesso!\n", id);
        } else {
            System.out.printf("\tPet [%d] não encontrado!\n", id);
        }
    }

    private void buscar() {
        int id = entrada.lerInteiro("Digite o ID do pet: ");
        Pet p = controller.buscar(id);

        if (p != null) {
            System.out.println(p.toString());
        } else {
            System.out.printf("\tPet [%d] não encontrado!\n", id);
        }
    }


    private void apagar() {
        int id = entrada.lerInteiro("Digite o ID do pet para EXCLUIR: ");
        Pet p = controller.buscar(id);

        if (p != null) {
            System.out.println(p.toString());
            int idCliente = p.getCliente().getId();
            if (entrada.lerConfirmacao("Esse pet mesmo que gostaria de excluir? (s/n): ")) {
                if (controller.remover(id)) {
                    clienteController.remover(idCliente);
                    System.out.printf("\tPet [%d] excluído com sucesso!\n", id);
                }
            } else {
                System.out.println("Operação cancelada!");
            }

        } else {
            System.out.printf("\tPet [%d] não encontrado!\n", id);
        }
    }

}
