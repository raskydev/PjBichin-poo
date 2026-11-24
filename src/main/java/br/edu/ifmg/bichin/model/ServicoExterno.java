package br.edu.ifmg.bichin.model;

// Serviço que depende de um profissional terceirizado (ex.: acupuntura)
public class ServicoExterno extends Servico {
    private double valorTerceirizado;

    public ServicoExterno(int id, String descricao, double valor, double valorTerceirizado) {
        super(id, descricao, valor);
        this.valorTerceirizado = valorTerceirizado;
    }

    public double getValorTerceirizado() {
        return valorTerceirizado;
    }

    public void setValorTerceirizado(double valorTerceirizado) {
        this.valorTerceirizado = valorTerceirizado;
    }

    // No serviço externo o valor final é o valor-base + o valor do profissional
    @Override
    public double valorFinalDoServico() {
        return getValor() + valorTerceirizado;
    }

    @Override
    public String toString() {
        return "Serviço Externo " + getId() + " [" +
                "\n\tDescrição: " + getDescricao() +
                "\n\tValor-base: %.2f".formatted(getValor()) +
                "\n\tValor do terceirizado: %.2f".formatted(valorTerceirizado) +
                "\n\tValor final: %.2f".formatted(valorFinalDoServico()) +
                "\n]";
    }
}