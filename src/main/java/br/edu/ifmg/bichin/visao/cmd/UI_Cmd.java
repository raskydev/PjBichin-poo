package br.edu.ifmg.bichin.visao.cmd;

public class UI_Cmd {
    private EntradaCmd entradaCmd;
    private UiCmdServico uiCmdServico;
    private UiCmdPet uiCmdPet;
    private UiCmdCliente uiCmdCliente;
    private UiCmdServicoPrestado menuServicoPrestado;

    public UI_Cmd(
            EntradaCmd entradaCmd,
            UiCmdServico uiCmdServic,
            UiCmdPet uiCmdPet,
            UiCmdCliente uiCmdCliente,
            UiCmdServicoPrestado menuServicoPrestado) {

        this.entradaCmd = entradaCmd;
        this.uiCmdServico = uiCmdServic;
        this.uiCmdPet = uiCmdPet;
        this.uiCmdCliente = uiCmdCliente;
        this.menuServicoPrestado = menuServicoPrestado;
    }

    public void iniciar() {
        int op;
        do {
            System.out.println("""
                    ╔═══════════════════════╗
                    ║        PET SHOP       ║
                    ╠═══════════════════════╣
                    ║ 1. Clientes           ║
                    ║ 2. Pets               ║
                    ║ 3. Serviços           ║
                    ║ 4. Serviços Prestados ║
                    ║ 0. Sair               ║
                    ╚═══════════════════════╝
                    """);
            op = entradaCmd.lerInteiro("Digite uma opção: ");

            switch (op) {
                case 1:
                    uiCmdCliente.iniciar();
                    break;

                case 2:
                    uiCmdPet.iniciar();
                    break;

                case 3:
                    uiCmdServico.iniciar();
                    break;

                case 4:
                    menuServicoPrestado.iniciar();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (op != 0);
    }

}