import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ClinicaServico {
    // ArrayList<Paciente>: acesso por indice e necessario
    private List<Paciente> pacientes;
    // ArrayList<Profissional>: acesso por indice tambem e necessario
    private List<Profissional> profissionais;
    private List<Consulta> consultas;
    private List<Atendimento> atendimentos;
    private List<Pagamento> pagamentos;
    // LIGACAO DINAMICA: lista de pessoa permite guardar o paciente e profissional juntos
    private List<Pessoa> pessoas;
    // HashSet<String>: apenas verificacao de existencia as contains nao precisa ficar em ordem
    private HashSet<String> cpfsCadastrados;
    // HashMap<String, Paciente>: busca por chave cpf mais facil que ficar olhando a lista
    private HashMap<String, Paciente> pacientesPorCpf;
    private HashMap<String, Profissional> profissionaisPorNome;
    private List<Double> multas;

    public ClinicaServico() {
        this.pacientes = new ArrayList<>();
        this.profissionais = new ArrayList<>();
        this.consultas = new ArrayList<>();
        this.atendimentos = new ArrayList<>();
        this.pagamentos = new ArrayList<>();
        this.pessoas = new ArrayList<>();
        this.cpfsCadastrados = new HashSet<>();
        this.pacientesPorCpf = new HashMap<>();
        this.profissionaisPorNome = new HashMap<>();
        this.multas = new ArrayList<>();
    }

    // pacientes

    public void cadastrarPaciente(Paciente paciente) throws PacienteNaoEncontradoException {
        String cpf = paciente.getCpf();
        // HashSet: se tentar adicionar a mesma coisa vai retornar falsae
        if (!cpfsCadastrados.add(cpf)) {
            throw new PacienteNaoEncontradoException("CPF " + cpf + " ja cadastrado no sistema");
        }
        pacientes.add(paciente);
        pessoas.add(paciente);
        // HashMap: armazena para facilitar procurar o cpf na lista
        pacientesPorCpf.put(cpf, paciente);
    }

    public Paciente buscarPacientePorCpf(String cpf) throws PacienteNaoEncontradoException {
        // HashMap: faz a busca por chave melhor para nao ficar procurando na lista
        Paciente p = pacientesPorCpf.get(cpf);
        if (p == null) {
            throw new PacienteNaoEncontradoException("Paciente com CPF " + cpf + " nao encontrado");
        }
        return p;
    }

    public void desativarPaciente(String cpf) throws PacienteNaoEncontradoException {
        Paciente p = buscarPacientePorCpf(cpf);
        p.desativar();
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    // proficionais

    public void cadastrarProfissional(Profissional profissional) {
        profissionais.add(profissional);
        pessoas.add(profissional);
        profissionaisPorNome.put(profissional.getNome(), profissional);
    }

    public Profissional buscarProfissionalPorNome(String nome) throws ProfissionalNaoEncontradoException {
        Profissional p = profissionaisPorNome.get(nome);
        if (p == null) {
            throw new ProfissionalNaoEncontradoException("Profissional " + nome + " nao encontrado");
        }
        return p;
    }

    public Profissional buscarProfissionalPorEspecialidade(String especialidade, String data, String horario)
            throws ProfissionalNaoEncontradoException, HorarioIndisponivelException {
        for (int i = 0; i < profissionais.size(); i++) {
            Profissional p = profissionais.get(i);
            if (p.getEspecialidade().equals(especialidade) && p.getValorConsulta() > 0) {
                if (p.atendeNoDia(data) && !temConflito(p.getNome(), data, horario)) {
                    return p;
                }
            }
        }
        throw new ProfissionalNaoEncontradoException("Nenhum profissional disponivel para " + especialidade);
    }

    public List<Profissional> getProfissionais() {
        return profissionais;
    }

    //consultas

    public void agendarConsulta(Consulta consulta, Paciente paciente, Profissional profissional,
                                String diaSemana)
            throws PacienteInativoException, HorarioIndisponivelException {
        if (!paciente.isAtivo()) {
            throw new PacienteInativoException("Paciente " + paciente.getNome() + " esta inativo");
        }
        if (!profissional.atendeNoDia(diaSemana)) {
            throw new HorarioIndisponivelException("Profissional nao atende nesse dia");
        }
        if (temConflito(profissional.getNome(), consulta.getData(), consulta.getHorario())) {
            throw new HorarioIndisponivelException("Horario ja ocupado para esse profissional");
        }
        consultas.add(consulta);
    }

    public Consulta buscarConsulta(String cpf, String data, String horario)
            throws ConsultaNaoEncontradaException {
        for (int i = 0; i < consultas.size(); i++) {
            Consulta c = consultas.get(i);
            if (c.getCpfPaciente().equals(cpf) && c.getData().equals(data)
                    && c.getHorario().equals(horario)) {
                return c;
            }
        }
        throw new ConsultaNaoEncontradaException("Consulta nao encontrada");
    }

    public void cancelarConsulta(Consulta consulta) throws OperacaoInvalidaException {
        if (consulta.getStatus().equals("realizada")) {
            throw new OperacaoInvalidaException("Nao pode cancelar consulta ja realizada");
        }
        if (consulta.getStatus().equals("cancelada")) {
            throw new OperacaoInvalidaException("Consulta ja esta cancelada");
        }
        consulta.cancelar();
    }

    public void remarcarConsulta(Consulta original, String novaData, String novoHorario,
                                 Profissional profissional, String diaSemana)
            throws OperacaoInvalidaException, HorarioIndisponivelException {
        if (!original.getStatus().equals("agendada")) {
            throw new OperacaoInvalidaException("So pode remarcar consulta agendada");
        }
        if (!profissional.atendeNoDia(diaSemana)) {
            throw new HorarioIndisponivelException("Profissional nao atende nesse dia");
        }
        if (temConflito(profissional.getNome(), novaData, novoHorario)) {
            throw new HorarioIndisponivelException("Horario ocupado");
        }
        original.remarcar();
        Consulta nova = new Consulta(original.getCpfPaciente(), profissional.getNome(),
                novaData, novoHorario, original.getTipo());
        consultas.add(nova);
    }

    public boolean temConflito(String nomeProf, String data, String horario) {
        for (int i = 0; i < consultas.size(); i++) {
            Consulta c = consultas.get(i);
            if (c.getNomeProfissional().equals(nomeProf) && c.getData().equals(data)
                    && c.getHorario().equals(horario) && c.getStatus().equals("agendada")) {
                return true;
            }
        }
        return false;
    }

    public String sugerirHorario(String nomeProf, String data) {
        for (int h = 8; h <= 18; h++) {
            String teste;
            if (h < 10) {
                teste = "0" + h + ":00";
            } else {
                teste = h + ":00";
            }
            if (!temConflito(nomeProf, data, teste)) {
                return teste;
            }
        }
        return "";
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    //atendimentos

    public void registrarAtendimento(Atendimento atendimento, Consulta consulta)
            throws OperacaoInvalidaException {
        if (!consulta.getStatus().equals("agendada")) {
            throw new OperacaoInvalidaException("So pode registrar atendimento em consulta agendada");
        }
        consulta.realizar();
        atendimentos.add(atendimento);
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    //pagamentos

    public void registrarPagamento(Pagamento pagamento) {
        pagamentos.add(pagamento);
    }

    public void adicionarMulta(double valor) {
        multas.add(valor);
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public List<Double> getMultas() {
        return multas;
    }

    //relatorios

    // ligacao dinamica: quem manda e o tipo do objeto nao o de referência
    public List<Pessoa> getPessoas() {
        return pessoas;
    }
}
