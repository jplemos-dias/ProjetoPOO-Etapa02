import java.util.ArrayList;
import java.util.List;

public class Convenio {
    private String nome;
    private double percentualCobertura;
    // ArrayList<String>: ordem de insercao importa acesso por indice necessario
    private List<String> especialidadesCobertas;

    public Convenio(String nome, double percentualCobertura) {
        this.nome = nome;
        this.percentualCobertura = percentualCobertura;
        this.especialidadesCobertas = new ArrayList<>();
    }

    public void adicionarEspecialidade(String especialidade) {
        especialidadesCobertas.add(especialidade);
    }

    public boolean cobreEspecialidade(String especialidade) {
        for (int i = 0; i < especialidadesCobertas.size(); i++) {
            if (especialidadesCobertas.get(i).equals(especialidade)) {
                return true;
            }
        }
        return false;
    }

    public String getNome() {
        return nome;
    }

    public double getPercentualCobertura() {
        return percentualCobertura;
    }

    public List<String> getEspecialidadesCobertas() {
        return especialidadesCobertas;
    }
}
