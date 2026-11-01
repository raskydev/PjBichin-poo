package br.edu.ifmg.bichin.visao.cmd;

import java.util.Scanner;

// Essa parte foi desenvolvida além da especifiação do trabalho, que visa evitar o famoso Ctrl+C e Ctrl+V de código
public class EntradaCmd {
    private Scanner teclado;

    public EntradaCmd() {
        teclado = new Scanner(System.in);
    }

    public int lerInteiro(String texto) {
        int n;
        while (true) {
            try {
                System.out.print(texto);
                n = Integer.parseInt(teclado.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número inteiro!");
            }
        }
        return n;
    }

    public String lerString(String texto) {
        System.out.print(texto);
        String s = teclado.nextLine();

        while (s.isBlank()) {
            System.out.print("Este campo não pode ser vazio!\n" + texto);
            s = teclado.nextLine();
        }

        return s.trim();
    }

    public double lerValor(String texto) {
        double d;
        while (true) {
            try {
                System.out.print(texto);
                d = Double.parseDouble(teclado.nextLine().trim().replace(",", "."));
                if (d > 0) {
                    break;
                } else {
                    System.out.println("Valor inválido! Número deve ser maior que zero!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Valor inválida! Digite um número decimal!");
            }
        }
        return d;
    }

    public boolean lerConfirmacao(String texto) {
        String c;
        while (true) {
            c = lerString(texto).toLowerCase();
            if (c.matches("[sny]")) {
                break;
            } else {
                System.out.print("Caractere inválido! ");
            }
        }

        if (c.equals("s") || c.equals("y")) {
            return true;
        } else {
            return false;
        }
    }
}
