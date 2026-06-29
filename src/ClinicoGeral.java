public class ClinicoGeral extends Profissional {
    private String encaminhamento;

    public ClinicoGeral(String nome, String cpf) {
        super(nome, cpf, "clinica geral");
        this.encaminhamento = "";
    }

    public String getEncaminhamento() {
        return encaminhamento;
    }

    public void setEncaminhamento(String encaminhamento) {
        this.encaminhamento = encaminhamento;
    }

    @Override
    public String exibirResumo() {
        return "Clinico Geral - Nome: " + getNome() + " | CPF: " + getCpf()
                + " | Registro: " + getRegistroProfissional()
                + " | Valor: R$" + getValorConsulta()
                + " | Encaminhamento: " + encaminhamento
                + " | Dias: " + getDiasFormatado();
    }

    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        atendimento.adicionarProcedimento("Clinico Geral - Encaminhamento: " + encaminhamento);
    }
}
