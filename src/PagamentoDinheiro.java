public class PagamentoDinheiro extends Pagamento {

    public PagamentoDinheiro(Consulta consulta, double valorBase) {
        super(consulta, valorBase);
    }

    // desconto de 5% pra pagamento em dinheiro ou pix
    @Override
    public double calcularValorFinal() {
        return getValorBase() * 0.95;
    }

    @Override
    public String exibirResumo() {
        double valorFinal = Math.round(calcularValorFinal() * 100.0) / 100.0;
        return "Dinheiro/Pix | CPF: " + getConsulta().getCpfPaciente()
                + " | Valor: R$" + valorFinal + " (5% desconto)";
    }
}
