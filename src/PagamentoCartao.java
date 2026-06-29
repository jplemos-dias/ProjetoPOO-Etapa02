public class PagamentoCartao extends Pagamento {
    private int parcelas;

    public PagamentoCartao(Consulta consulta, double valorBase, int parcelas) {
        super(consulta, valorBase);
        this.parcelas = parcelas;
    }

    // ate 3x sem taxa acima de 3x cobra 2,5% por parcela extra
    @Override
    public double calcularValorFinal() {
        double valor = getValorBase();
        if (parcelas > 3) {
            int parcelasExtras = parcelas - 3;
            double taxa = valor * 0.025 * parcelasExtras;
            valor = valor + taxa;
        }
        return valor;
    }

    @Override
    public String exibirResumo() {
        double valorFinal = Math.round(calcularValorFinal() * 100.0) / 100.0;
        double valorParcela = Math.round((valorFinal / parcelas) * 100.0) / 100.0;
        return "Cartao | CPF: " + getConsulta().getCpfPaciente()
                + " | Valor: R$" + valorFinal
                + " | " + parcelas + "x de R$" + valorParcela;
    }

    public int getParcelas() {
        return parcelas;
    }
}
