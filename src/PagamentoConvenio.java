public class PagamentoConvenio extends Pagamento {
    private Convenio convenio;

    public PagamentoConvenio(Consulta consulta, double valorBase, Convenio convenio) {
        super(consulta, valorBase);
        this.convenio = convenio;
    }

    // aplica o percentual de cobertura do conveno
    @Override
    public double calcularValorFinal() {
        double cobertura = convenio.getPercentualCobertura() / 100.0;
        return getValorBase() * (1 - cobertura);
    }

    @Override
    public String exibirResumo() {
        double valorFinal = Math.round(calcularValorFinal() * 100.0) / 100.0;
        return "Convenio (" + convenio.getNome() + ") | CPF: " + getConsulta().getCpfPaciente()
                + " | Cobertura: " + convenio.getPercentualCobertura() + "%"
                + " | Valor: R$" + valorFinal;
    }

    public Convenio getConvenio() {
        return convenio;
    }
}
