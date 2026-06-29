public abstract class Pagamento implements Exportavel {
    private double valorBase;
    private Consulta consulta;

    public Pagamento(Consulta consulta, double valorBase) {
        this.consulta = consulta;
        this.valorBase = valorBase;
    }

    // metodo abstrato cada forma de pagamento calcula diferente uma da outa
    public abstract double calcularValorFinal();

    @Override
    public String exportarDados() {
        double valorFinal = Math.round(calcularValorFinal() * 100.0) / 100.0;
        return "Pagamento | CPF: " + consulta.getCpfPaciente()
                + " | Valor: R$" + valorFinal;
    }

    public String exibirResumo() {
        double valorFinal = Math.round(calcularValorFinal() * 100.0) / 100.0;
        return "CPF: " + consulta.getCpfPaciente()
                + " | Valor final: R$" + valorFinal;
    }

    public double getValorBase() {
        return valorBase;
    }

    public Consulta getConsulta() {
        return consulta;
    }
}
