public class Fisioterapeuta extends Profissional {
    private int totalSessoesPrevistas;

    public Fisioterapeuta(String nome, String cpf, int totalSessoes) {
        super(nome, cpf, "fisioterapia");
        this.totalSessoesPrevistas = totalSessoes;
    }

    public int getTotalSessoesPrevistas() {
        return totalSessoesPrevistas;
    }

    public void setTotalSessoesPrevistas(int totalSessoes) {
        this.totalSessoesPrevistas = totalSessoes;
    }

    // SOBRESCRITA: mesmo nome e tambem parametros redefine os comportamentos na classe filha
    @Override
    public String exibirResumo() {
        return "Fisioterapeuta - Nome: " + getNome() + " | CPF: " + getCpf()
                + " | Registro: " + getRegistroProfissional()
                + " | Valor: R$" + getValorConsulta()
                + " | Sessoes previstas: " + totalSessoesPrevistas
                + " | Dias: " + getDiasFormatado();
    }

    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        atendimento.adicionarProcedimento("Fisioterapia - Sessoes previstas: " + totalSessoesPrevistas);
    }
}
