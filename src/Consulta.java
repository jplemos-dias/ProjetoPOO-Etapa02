public class Consulta implements Agendavel, Exportavel {
    private String cpfPaciente;
    private String nomeProfissional;
    private String data;
    private String horario;
    private String tipo;
    private String status;

    // sem tipo so assume a inicial
    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = "inicial";
        this.status = "agendada";
    }

    // SOBRECARGA: mesmo nome mais com parametros diferentes
    public Consulta(String cpfPaciente, String nomeProfissional, String data,
                    String horario, String tipo) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
        this.status = "agendada";
    }

    // construtor pra remarcacao onde ja seta o status
    public Consulta(String cpfPaciente, String nomeProfissional, String data,
                    String horario, String tipo, String status) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
        this.status = status;
    }

    @Override
    public void agendar() {
        this.status = "agendada";
    }

    @Override
    public void cancelar() {
        this.status = "cancelada";
    }

    // SOBRECARGA: mesmo nome mas com parametros diferentes
    public String cancelar(String motivo) {
        this.status = "cancelada";
        return "Consulta cancelada. Motivo: " + motivo;
    }

    @Override
    public void remarcar() {
        this.status = "remarcada";
    }

    public void realizar() {
        this.status = "realizada";
    }

    // implementacao de interface Exportavel
    @Override
    public String exportarDados() {
        return "Consulta | CPF: " + cpfPaciente + " | Prof: " + nomeProfissional
                + " | Data: " + data + " | Hora: " + horario
                + " | Tipo: " + tipo + " | Status: " + status;
    }

    public String exibirResumo() {
        return "Paciente(CPF): " + cpfPaciente + " | Prof: " + nomeProfissional
                + " | Data: " + data + " | Hora: " + horario
                + " | Tipo: " + tipo + " | Status: " + status;
    }

    // getters e setters apenas
    public String getCpfPaciente() { return cpfPaciente; }
    public String getNomeProfissional() { return nomeProfissional; }
    public String getData() { return data; }
    public String getHorario() { return horario; }
    public String getTipo() { return tipo; }
    public String getStatus() { return status; }

    public void setData(String data) { this.data = data; }
    public void setHorario(String horario) { this.horario = horario; }
    public void setStatus(String status) { this.status = status; }
}
