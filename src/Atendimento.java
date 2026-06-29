public class Atendimento implements Exportavel {
    private Consulta consulta;
    // COMPOSICAO: prontuario so existe dentro de atendimento
    private Prontuario prontuario;

    public Atendimento(Consulta consulta, String dataRegistro) {
        this.consulta = consulta;
        this.prontuario = new Prontuario(dataRegistro);
    }

    // SOBRECARGA: mesmo nome mais com parametros diferentes
    public Atendimento(Consulta consulta, String dataRegistro, String observacoes) {
        this.consulta = consulta;
        this.prontuario = new Prontuario(dataRegistro);
        this.prontuario.setObservacoes(observacoes);
    }

    public Atendimento(Consulta consulta, String dataRegistro, String observacoes, String diagnostico) {
        this.consulta = consulta;
        this.prontuario = new Prontuario(dataRegistro);
        this.prontuario.setObservacoes(observacoes);
        this.prontuario.setDiagnostico(diagnostico);
    }

    public void adicionarProcedimento(String procedimento) {
        prontuario.adicionarProcedimento(procedimento);
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    @Override
    public String exportarDados() {
        return "Atendimento | CPF: " + consulta.getCpfPaciente()
                + " | Prof: " + consulta.getNomeProfissional()
                + " | Data: " + consulta.getData()
                + " | " + prontuario.exibirResumo();
    }

    public String exibirResumo() {
        return prontuario.exibirResumo();
    }
}
