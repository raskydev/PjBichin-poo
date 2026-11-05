package br.edu.ifmg.bichin.visao.cmd;

import br.edu.ifmg.bichin.controller.ClienteController;
import br.edu.ifmg.bichin.model.Cliente;

import java.util.ArrayList;

public class UiCmdCliente {
    private EntradaCmd entrada;
    private ClienteController controller;

    public UiCmdCliente(EntradaCmd entrada, ClienteController controller) {
        this.entrada = entrada;
        this.controller = controller;
    }


    private void buscar() {
        int id = entrada.lerInteiro("Digite o ID do cliente: ");
        Cliente c = controller.buscar(id);

        if (c != null) {
            System.out.println(c.toString());
        } else {
            System.out.printf("\tCliente [%d] não encontrado!\n", id);
        }
    }

    private void listar() {
        ArrayList<Cliente> clientes = controller.listar();

        if (!clientes.isEmpty()) {
            System.out.println("Listando todos os clientes existentes: ");
            for (int i = 0; i < clientes.size(); i++) {
                System.out.println(clientes.get(i).toString());
            }
        } else {
            System.out.println("Nenhum cliente cadastrado!");
        }
    }

    public void iniciar() {
        int op;
        do {
            System.out.print("""
                    ┌────────────────────┐
                    │      Cliente       │
                    ├────────────────────┤
                    │1. Alterar          │
                    │2. Apagar           │
                    │3. Listar           │
                    │4. Buscar           │
                    │0. Voltar           │
                    └────────────────────┘
                    """);
            op = entrada.lerInteiro("Digite uma opção: ");

            switch (op) {
                case 1:
                    alterar();
                break;

                case 2:
                    apagar();
                break;

                case 3:
                   listar();
                break;

                case 4:
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

    private void alterar() {
        int id = entrada.lerInteiro("Digite o ID do cliente que deseja alterar: ");

        Cliente c = controller.buscar(id);

        if (c != null) {
            String novoNome = entrada.lerString("Digite novo nome: ");
            String novoTelefone = entrada.lerTelefone("Digite novo telefone: ");

            c.setNome(novoNome);
            c.setTelefone(novoTelefone);

            System.out.printf("\tCliente [%d] alterado com sucesso!\n", id);
        } else {
            System.out.printf("\tCliente [%d] não encontrado!\n", id);
        }
    }

    private void apagar() {
        int id = entrada.lerInteiro("Digite o ID do cliente para EXCLUIR: ");
        Cliente c = controller.buscar(id);

        if (c != null) {
            System.out.println(c.toString());
            if (entrada.lerConfirmacao("Esse cliente mesmo que gostaria de excluir? (s/n): ")) {
                if (controller.remover(id)) {
                    System.out.printf("\tCliente [%d] excluído com sucesso!\n", id);
                } else {
                    System.out.printf("\tCliente [%d] possui pets vinculados!\nOperação cancelada!\n", id);
                }
            } else {
                System.out.println("Operação cancelada!");
            }

        } else {
            System.out.printf("\tCliente [%d] não encontrado!\n", id);
        }
    }

}
