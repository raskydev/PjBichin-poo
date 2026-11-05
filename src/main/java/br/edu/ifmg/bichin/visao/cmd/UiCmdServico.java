package br.edu.ifmg.bichin.visao.cmd;

import br.edu.ifmg.bichin.controller.ServicoController;
import br.edu.ifmg.bichin.model.Servico;

import java.util.ArrayList;

public class UiCmdServico {
    private EntradaCmd entrada;
    private ServicoController controller;

    public UiCmdServico(EntradaCmd entrada, ServicoController controller) {
        this.entrada = entrada;
        this.controller = controller;
    }

    public void iniciar() {
        int op;
        do {
            System.out.print("""
                    ┌────────────────────┐
                    │      Serviço       │
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

    private void alterar() {
        int id = entrada.lerInteiro("Digite o ID do serviço que deseja alterar: ");

        Servico s = controller.buscar(id);

        if (s != null) {
            String novaDescricao = entrada.lerString("Digite nova descrição: ");
            double novoValor = entrada.lerValor("Digite novo valor: ");

            s.setDescricao(novaDescricao);
            s.setValor(novoValor);

            System.out.printf("\tServiço [%d] alterado com sucesso!\n", id);
        } else {
            System.out.printf("\tServiço [%d] não encontrado!\n", id);
        }
    }

    private void listar() {
        ArrayList<Servico> servicos = controller.listar();

        if (!servicos.isEmpty()) {
            System.out.println("Listando todos os serviços existentes: ");
            for (int i = 0; i < servicos.size(); i++) {
                System.out.println(servicos.get(i).toString());
            }
        } else {
            System.out.println("Nenhum serviço cadastrado!");
        }
    }

    private void adicionar() {
        while (true) {
            int id = entrada.lerInteiro("Digite um ID: ");
            if (controller.buscar(id) == null) {
                String descricao = entrada.lerString("Digite a descrição: ");
                double valor = entrada.lerValor("Digite o valor: ");

                controller.adicionar(id, descricao, valor);
                System.out.printf("Serviço [%d] cadastrado com sucesso!\n", id);
                break;
            } else {
                System.out.println("Erro! ID já existe! Tente novamente.\n");
            }
        }
    }

    private void apagar() {
        int id = entrada.lerInteiro("Digite o ID do serviço para EXCLUIR: ");
        Servico s = controller.buscar(id);

        if (s != null) {
            System.out.println(s.toString());
            if (entrada.lerConfirmacao("Esse serviço mesmo que gostaria de excluir? (s/n): ")) {
                if (controller.remover(id)) {
                    System.out.printf("\tServiço [%d] excluído com sucesso!\n", id);
                }
            } else {
                System.out.println("Operação cancelada!");
            }

        } else {
            System.out.printf("\tServiço [%d] não encontrado!\n", id);
        }
    }

    private void buscar() {
        int id = entrada.lerInteiro("Digite o ID do serviço: ");
        Servico s = controller.buscar(id);

        if (s != null) {
            System.out.println(s.toString());
        } else {
            System.out.printf("\tServiço [%d] não encontrado!\n", id);
        }
    }

}
