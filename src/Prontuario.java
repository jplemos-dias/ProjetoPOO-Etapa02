import java.util.ArrayList;
import java.util.List;

public class Prontuario {
    private String observacoes;
    private String diagnostico;
    // ArrayList<String>: acesso por indice necessario
    private List<String> procedimentos;
    private String dataRegistro;

    // COMPOSICAO: prontuario so existe dentro de atendimento se atendimento for removido prontuario tambem e
    // construtor package-private, so pode ser criado dentro da mesma pasta
    Prontuario(String dataRegistro) {
        this.observacoes = "";
        this.diagnostico = "";
        this.procedimentos = new ArrayList<>();
        this.dataRegistro = dataRegistro;
    }

    public void adicionarProcedimento(String procedimento) {
        procedimentos.add(procedimento);
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public List<String> getProcedimentos() {
        return procedimentos;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public String exibirResumo() {
        String resumo = "Observacoes: " + observacoes;
        if (!diagnostico.equals("")) {
            resumo = resumo + "\nDiagnostico: " + diagnostico;
        }
        if (procedimentos.size() > 0) {
            resumo = resumo + "\nProcedimentos: ";
            for (int i = 0; i < procedimentos.size(); i++) {
                resumo = resumo + procedimentos.get(i);
                if (i < procedimentos.size() - 1) {
                    resumo = resumo + ", ";
                }
            }
        }
        resumo = resumo + "\nData: " + dataRegistro;
        return resumo;
    }
}
