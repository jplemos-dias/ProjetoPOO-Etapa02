public class Psicologo extends Profissional {
    private String abordagem;

    public Psicologo(String nome, String cpf, String abordagem) {
        super(nome, cpf, "psicologia");
        this.abordagem = abordagem;
    }

    public String getAbordagem() {
        return abordagem;
    }

    public void setAbordagem(String abordagem) {
        this.abordagem = abordagem;
    }

    @Override
    public String exibirResumo() {
        return "Psicologo - Nome: " + getNome() + " | CPF: " + getCpf()
                + " | Registro: " + getRegistroProfissional()
                + " | Valor: R$" + getValorConsulta()
                + " | Abordagem: " + abordagem
                + " | Dias: " + getDiasFormatado();
    }

    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        atendimento.adicionarProcedimento("Psicologia - Abordagem: " + abordagem);
    }
}
