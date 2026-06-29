import java.util.ArrayList;
import java.util.List;

public abstract class Profissional extends Pessoa {
    private String especialidade;
    private String registroProfissional;
    private double valorConsulta;
    private List<String> diasDisponiveis;

    // cadastro inicial do medico e sua especialidade para ficar melhor de saber sua funcao
    public Profissional(String nome, String cpf, String especialidade) {
        super(nome, cpf);
        this.especialidade = especialidade;
        this.registroProfissional = "";
        this.valorConsulta = 0;
        this.diasDisponiveis = new ArrayList<>();
    }

    // SOBRECARGA: mesmo nome porem com parametros diferentes
    public Profissional(String nome, String cpf, String especialidade,
                        String registroProfissional, double valorConsulta) {
        super(nome, cpf);
        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
        this.valorConsulta = valorConsulta;
        this.diasDisponiveis = new ArrayList<>();
    }

    // cada 1 especialidade vai ter que implementar esse metodo
    public abstract void registrarEspecifico(Atendimento atendimento);

    // so as subclasses conseguem acessar isso
    protected double getValorBase() {
        return this.valorConsulta;
    }

    public void atualizar(String registro, double valor) {
        this.registroProfissional = registro;
        this.valorConsulta = valor;
    }

    // SOBRECARGA: tem mesmo nome mais com parametros diferentes
    public void atualizar(String registro, double valor, List<String> dias) {
        this.registroProfissional = registro;
        this.valorConsulta = valor;
        this.diasDisponiveis = dias;
    }

    // verifica se o medico atende no dia da consulta
    public boolean atendeNoDia(String dia) {
        for (int i = 0; i < diasDisponiveis.size(); i++) {
            if (diasDisponiveis.get(i).equals(dia)) {
                return true;
            }
        }
        return false;
    }

    // valida as especialidades aceitas pela clinica
    public static boolean especialidadeValida(String esp) {
        if (esp.equals("clinica geral")) return true;
        if (esp.equals("fisioterapia")) return true;
        if (esp.equals("psicologia")) return true;
        if (esp.equals("nutricao")) return true;
        return false;
    }

    // junta os dias em uma string so pra mostrar
    public String getDiasFormatado() {
        String resultado = "";
        for (int i = 0; i < diasDisponiveis.size(); i++) {
            if (i > 0) resultado = resultado + ", ";
            resultado = resultado + diasDisponiveis.get(i);
        }
        return resultado;
    }

    public void adicionarDia(String dia) {
        diasDisponiveis.add(dia);
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String reg) {
        this.registroProfissional = reg;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(double valor) {
        this.valorConsulta = valor;
    }

    public List<String> getDiasDisponiveis() {
        return diasDisponiveis;
    }
}
