package br.edu.ifmg.bichin.visao.cmd;

import br.edu.ifmg.bichin.controller.ClienteController;
import br.edu.ifmg.bichin.controller.ServicoPrestadoController;
import br.edu.ifmg.bichin.model.Cliente;
import br.edu.ifmg.bichin.model.Pet;
import br.edu.ifmg.bichin.model.ServicoPrestado;

import java.time.LocalDate;
import java.util.ArrayList;

public class UiCmdServicoPrestado {
    private EntradaCmd entrada;
    private ClienteController clienteController;
    private ServicoPrestadoController controller;

    public UiCmdServicoPrestado(
            EntradaCmd entrada,
            ClienteController clienteController,
            ServicoPrestadoController controller) {

        this.entrada = entrada;
        this.clienteController = clienteController;
        this.controller = controller;
    }

    public void servicosPorPeriodo() {
        LocalDate inicio = entrada.lerData("Digite a data inicial: ");
        LocalDate fim = entrada.lerData("Digite a data final: ");

        ArrayList<ServicoPrestado> servicos = controller.buscarPorPeriodo(inicio, fim);

        if (!servicos.isEmpty()) {
            System.out.println("Listando todos os serviços prestados de " + inicio + " até " + fim + ": ");
            for (int i = 0; i < servicos.size(); i++) {
                System.out.println(servicos.get(i).toString());
            }
        } else {
            System.out.println("Nenhum serviço prestado foi realizado no período informado!");
        }
    }

    private void adicionar() {
        while (true) {
            int id = entrada.lerInteiro("Digite um ID: ");
            ServicoPrestado sp = controller.buscar(id);

            if (sp == null) {
                int idServico = entrada.lerInteiro("Digite o ID do serviço: ");
                int idPet = entrada.lerInteiro("Digite o ID do pet: ");
                double valor = entrada.lerValor("Digite o valor: ");
                LocalDate data = entrada.lerData("Digite a data: ");

                controller.adicionar(id, idServico, idPet, valor, data);
                System.out.printf("Serviço prestado [%d] cadastrado com sucesso!\n", id);
                break;
            } else {
                System.out.println("Erro! ID já existe! Tente novamente.\n");
            }
        }
    }

    public void animaisDoDono() {
        String nomeDono = entrada.lerString("Digite o nome do dono: ");
        ArrayList<Pet> pets = controller.buscarBichosPorNomeDono(nomeDono);

        if (!pets.isEmpty()) {
            System.out.println("Listando todos os pets de " + nomeDono + ": ");
            for (int i = 0; i < pets.size(); i++) {
                System.out.println(pets.get(i).toString());
            }
        } else {
            System.out.println("Nenhum pet cadastrado para o dono " + nomeDono + "!");
        }
    }

    private void apagar() {
        int id = entrada.lerInteiro("Digite o ID do serviço prestado para EXCLUIR: ");
        ServicoPrestado sp = controller.buscar(id);

        if (sp != null) {
            System.out.println(sp.toString());
            if (entrada.lerConfirmacao("Esse serviço prestado mesmo que gostaria de excluir? (s/n): ")) {
                if (controller.remover(id)) {
                    System.out.printf("\tServiço prestado [%d] excluído com sucesso!\n", id);
                }
            } else {
                System.out.println("Operação cancelada!");
            }

        } else {
            System.out.printf("\tServiço prestado [%d] não encontrado!\n", id);
        }
    }

    public void iniciar() {
        int op;
        do {
            System.out.print("""
                    ┌────────────────────────┐
                    │    Serviço prestado    │
                    ├────────────────────────┤
                    │1. Adicionar            │
                    │2. Apagar               │
                    │3. Listar               │
                    │4. Buscar               │
                    │5. Maior valor          │
                    │6. Serviços por período │
                    │7. Serviços do cliente  │
                    │8. Animais do dono      │
                    │0. Voltar               │
                    └────────────────────────┘
                    """);
            op = entrada.lerInteiro("Digite uma opção: ");

            switch (op) {
                case 1:
                    adicionar();
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

                case 5:
                    maiorValor();
                break;

                case 6:
                    servicosPorPeriodo();
                break;

                case 7:
                    servicosDoCliente();
                break;

                case 8:
                    animaisDoDono();
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

    public void maiorValor() {
        int id = entrada.lerInteiro("Digite o ID do cliente para buscar o seu maior valor: ");
        Cliente c = clienteController.buscar(id);

        if (c != null) {
            if (controller.maiorValorPorCliente(c) > 0) {
                System.out.println(c.getNome() + " - R$" + controller.maiorValorPorCliente(c));
            } else {
                System.out.printf("\tCliente [%d] não possui nenhum serviço prestado!\n", id);
            }
        } else {
            System.out.printf("\tCliente [%d] não encontrado!\n", id);
        }
    }

    private void listar() {
        ArrayList<ServicoPrestado> servicosPrestado = controller.listar();

        if (!servicosPrestado.isEmpty()) {
            System.out.println("Listando todos os serviços prestados: ");
            for (int i = 0; i < servicosPrestado.size(); i++) {
                System.out.println(servicosPrestado.get(i).toString());
            }
        } else {
            System.out.println("Nenhum serviço prestado cadastrado!");
        }
    }

    public void servicosDoCliente() {
        int id = entrada.lerInteiro("Digite o ID do cliente para exibir seus serviços prestados: ");
        Cliente c = clienteController.buscar(id);

        if (c != null) {
            ArrayList<ServicoPrestado> servicosPrestado = controller.buscarPorCliente(c);

            if (!servicosPrestado.isEmpty()) {
                System.out.println("Listando todos os serviços prestados do cliente: ");
                for (int i = 0; i < servicosPrestado.size(); i++) {
                    System.out.println(servicosPrestado.get(i).toString());
                }
            } else {
                System.out.printf("\tCliente [%d] não possui nenhum serviço prestado!\n", id);
            }
        } else {
            System.out.printf("\tCliente [%d] não encontrado!\n", id);
        }
    }

    private void buscar() {
        int id = entrada.lerInteiro("Digite o ID do serviço prestado: ");
        ServicoPrestado sp = controller.buscar(id);

        if (sp != null) {
            System.out.println(sp.toString());
        } else {
            System.out.printf("\tServiço prestado [%d] não encontrado!\n", id);
        }
    }

}
