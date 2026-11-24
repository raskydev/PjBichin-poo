package br.edu.ifmg.bichin.model;

// Serviço realizado pela própria equipe do petshop
public class ServicoInterno extends Servico {
    private String crmv;   // registro do veterinário responsável

    public ServicoInterno(int id, String descricao, double valor, String crmv) {
        super(id, descricao, valor);
        this.crmv = crmv;
    }

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    // No serviço interno o valor final é só o valor-base
    @Override
    public double valorFinalDoServico() {
        return getValor();
    }

    @Override
    public String toString() {
        return "Serviço Interno " + getId() + " [" +
                "\n\tDescrição: " + getDescricao() +
                "\n\tValor: %.2f".formatted(valorFinalDoServico()) +
                "\n\tCRMV: " + crmv +
                "\n]";
    }
}
