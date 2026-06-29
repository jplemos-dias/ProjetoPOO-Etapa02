public class Nutricionista extends Profissional {
    private String planoAlimentar;

    public Nutricionista(String nome, String cpf) {
        super(nome, cpf, "nutricao");
        this.planoAlimentar = "";
    }

    public String getPlanoAlimentar() {
        return planoAlimentar;
    }

    public void setPlanoAlimentar(String plano) {
        this.planoAlimentar = plano;
    }

    @Override
    public String exibirResumo() {
        return "Nutricionista - Nome: " + getNome() + " | CPF: " + getCpf()
                + " | Registro: " + getRegistroProfissional()
                + " | Valor: R$" + getValorConsulta()
                + " | Plano alimentar: " + planoAlimentar
                + " | Dias: " + getDiasFormatado();
    }

    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        atendimento.adicionarProcedimento("Nutricao - Plano: " + planoAlimentar);
    }
}
