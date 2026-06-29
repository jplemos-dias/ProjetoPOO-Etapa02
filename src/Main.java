import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static ClinicaServico servico = new ClinicaServico();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== CLINICA VIDAPLENA ===");
            System.out.println("1 - Pacientes");
            System.out.println("2 - Profissionais");
            System.out.println("3 - Consultas");
            System.out.println("4 - Atendimentos");
            System.out.println("5 - Pagamentos");
            System.out.println("6 - Relatorios");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido!");
                continue;
            }

            switch (opcao) {
                case 1: menuPacientes(); break;
                case 2: menuProfissionais(); break;
                case 3: menuConsultas(); break;
                case 4: menuAtendimentos(); break;
                case 5: menuPagamentos(); break;
                case 6: menuRelatorios(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
        System.out.println("Sistema encerrado.");
    }

    //pacientes

    public static void menuPacientes() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PACIENTES ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Complementar cadastro");
            System.out.println("3 - Buscar por CPF");
            System.out.println("4 - Listar todos");
            System.out.println("5 - Desativar");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido!");
                continue;
            }

            switch (op) {
                case 1: cadastrarPaciente(); break;
                case 2: complementarPaciente(); break;
                case 3: buscarPaciente(); break;
                case 4: listarPacientes(); break;
                case 5: desativarPaciente(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    public static void cadastrarPaciente() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Tipo (1-Minimo / 2-Com idade e tel / 3-Completo): ");
        int tipo = 0;
        try {
            tipo = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Tipo invalido!");
            return;
        }

        try {
            Paciente paciente;
            if (tipo == 1) {
                paciente = new Paciente(nome, cpf);
            } else if (tipo == 2) {
                System.out.print("Idade: ");
                int idade = Integer.parseInt(sc.nextLine());
                System.out.print("Telefone: ");
                String tel = sc.nextLine();
                paciente = new Paciente(nome, cpf, idade, tel);
            } else {
                System.out.print("Idade: ");
                int idade = Integer.parseInt(sc.nextLine());
                System.out.print("Telefone: ");
                String tel = sc.nextLine();
                System.out.print("Convenio: ");
                String conv = sc.nextLine();
                paciente = new Paciente(nome, cpf, idade, tel, conv);
            }
            servico.cadastrarPaciente(paciente);
            System.out.println("Paciente cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Valor numerico invalido!");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void complementarPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        try {
            Paciente paciente = servico.buscarPacientePorCpf(cpf);

            System.out.print("Vai informar convenio? (1-Nao / 2-Sim): ");
            int tipo = Integer.parseInt(sc.nextLine());

            System.out.print("Idade: ");
            int idade = Integer.parseInt(sc.nextLine());
            System.out.print("Telefone: ");
            String tel = sc.nextLine();

            if (tipo == 1) {
                paciente.complementar(idade, tel);
            } else {
                System.out.print("Convenio: ");
                String conv = sc.nextLine();
                paciente.complementar(idade, tel, conv);
            }
            System.out.println("Cadastro atualizado!");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Valor numerico invalido!");
        }
    }

    public static void buscarPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        try {
            Paciente p = servico.buscarPacientePorCpf(cpf);
            System.out.println(p.exibirResumo());
        } catch (PacienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listarPacientes() {
        List<Paciente> lista = servico.getPacientes();
        if (lista.size() == 0) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).exibirResumo());
        }
    }

    public static void desativarPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        try {
            servico.desativarPaciente(cpf);
            System.out.println("Paciente desativado.");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    //proficionais

    public static void menuProfissionais() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PROFISSIONAIS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar cadastro");
            System.out.println("3 - Listar todos");
            System.out.println("4 - Filtrar por especialidade");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido!");
                continue;
            }

            switch (op) {
                case 1: cadastrarProfissional(); break;
                case 2: atualizarProfissional(); break;
                case 3: listarProfissionais(); break;
                case 4: filtrarProfissionais(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    public static void cadastrarProfissional() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Especialidade (clinica geral/fisioterapia/psicologia/nutricao): ");
        String esp = sc.nextLine();

        if (!Profissional.especialidadeValida(esp)) {
            System.out.println("Especialidade invalida!");
            return;
        }

        Profissional prof;
        if (esp.equals("fisioterapia")) {
            System.out.print("Total de sessoes previstas: ");
            try {
                int sessoes = Integer.parseInt(sc.nextLine());
                prof = new Fisioterapeuta(nome, cpf, sessoes);
            } catch (NumberFormatException e) {
                System.out.println("Numero invalido!");
                return;
            }
        } else if (esp.equals("psicologia")) {
            System.out.print("Abordagem terapeutica (TCC/psicanalise/humanista): ");
            String abordagem = sc.nextLine();
            prof = new Psicologo(nome, cpf, abordagem);
        } else if (esp.equals("nutricao")) {
            prof = new Nutricionista(nome, cpf);
        } else {
            prof = new ClinicoGeral(nome, cpf);
        }

        servico.cadastrarProfissional(prof);
        System.out.println("Profissional cadastrado!");
    }

    public static void atualizarProfissional() {
        System.out.print("Nome do profissional: ");
        String nome = sc.nextLine();
        try {
            Profissional prof = servico.buscarProfissionalPorNome(nome);

            System.out.print("Registro: ");
            String reg = sc.nextLine();
            System.out.print("Valor consulta: ");
            double valor = Double.parseDouble(sc.nextLine());

            System.out.print("Vai informar dias? (1-Nao / 2-Sim): ");
            int tipo = Integer.parseInt(sc.nextLine());

            if (tipo == 1) {
                prof.atualizar(reg, valor);
            } else {
                System.out.print("Quantos dias? ");
                int qtd = Integer.parseInt(sc.nextLine());
                List<String> dias = new ArrayList<>();
                for (int i = 0; i < qtd; i++) {
                    System.out.print("Dia " + (i + 1) + ": ");
                    dias.add(sc.nextLine());
                }
                prof.atualizar(reg, valor, dias);
            }
            System.out.println("Profissional atualizado!");
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Valor numerico invalido!");
        }
    }

    public static void listarProfissionais() {
        List<Profissional> lista = servico.getProfissionais();
        if (lista.size() == 0) {
            System.out.println("Nenhum profissional cadastrado.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).exibirResumo());
        }
    }

    public static void filtrarProfissionais() {
        System.out.print("Especialidade: ");
        String esp = sc.nextLine();
        List<Profissional> lista = servico.getProfissionais();
        boolean achou = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEspecialidade().equals(esp)) {
                System.out.println(lista.get(i).exibirResumo());
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhum profissional com essa especialidade.");
    }

    //consultas

    public static void menuConsultas() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- CONSULTAS ---");
            System.out.println("1 - Agendar (escolher profissional)");
            System.out.println("2 - Agendar (busca por especialidade)");
            System.out.println("3 - Cancelar");
            System.out.println("4 - Remarcar");
            System.out.println("5 - Listar todas");
            System.out.println("6 - Buscar por CPF");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido!");
                continue;
            }

            switch (op) {
                case 1: agendarComProfissional(); break;
                case 2: agendarPorEspecialidade(); break;
                case 3: cancelarConsulta(); break;
                case 4: remarcarConsulta(); break;
                case 5: listarConsultas(); break;
                case 6: buscarConsultasPorPaciente(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    public static void agendarComProfissional() {
        System.out.print("CPF do paciente: ");
        String cpf = sc.nextLine();
        try {
            Paciente paciente = servico.buscarPacientePorCpf(cpf);

            System.out.print("Nome do profissional: ");
            String nomeProf = sc.nextLine();
            Profissional prof = servico.buscarProfissionalPorNome(nomeProf);

            System.out.print("Data (DD/MM/AAAA): ");
            String data = sc.nextLine();
            System.out.print("Horario (HH:MM): ");
            String horario = sc.nextLine();

            String diaSemana = descobrirDiaSemana(data);
            Consulta consulta = new Consulta(cpf, nomeProf, data, horario);

            try {
                servico.agendarConsulta(consulta, paciente, prof, diaSemana);
                System.out.println("Consulta agendada com sucesso!");
            } catch (HorarioIndisponivelException e) {
                System.out.println(e.getMessage());
                String sugestao = servico.sugerirHorario(nomeProf, data);
                if (!sugestao.equals("")) {
                    System.out.println("Sugestao: " + sugestao);
                    System.out.print("Aceita? (1-Sim / 2-Nao): ");
                    int aceita = Integer.parseInt(sc.nextLine());
                    if (aceita == 1) {
                        Consulta nova = new Consulta(cpf, nomeProf, data, sugestao);
                        servico.agendarConsulta(nova, paciente, prof, diaSemana);
                        System.out.println("Consulta agendada com sucesso!");
                    }
                }
            }
        } catch (PacienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (PacienteInativoException e) {
            System.out.println(e.getMessage());
        } catch (HorarioIndisponivelException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Valor invalido!");
        }
    }

    public static void agendarPorEspecialidade() {
        System.out.print("CPF do paciente: ");
        String cpf = sc.nextLine();
        try {
            Paciente paciente = servico.buscarPacientePorCpf(cpf);
            if (!paciente.isAtivo()) {
                throw new PacienteInativoException("Paciente inativo. Nao pode agendar.");
            }

            System.out.print("Especialidade: ");
            String esp = sc.nextLine();
            System.out.print("Data (DD/MM/AAAA): ");
            String data = sc.nextLine();
            System.out.print("Horario (HH:MM): ");
            String horario = sc.nextLine();

            String diaSemana = descobrirDiaSemana(data);
            Profissional prof = servico.buscarProfissionalPorEspecialidade(esp, diaSemana, horario);

            Consulta consulta = new Consulta(cpf, prof.getNome(), data, horario);
            servico.agendarConsulta(consulta, paciente, prof, diaSemana);
            System.out.println("Consulta agendada com " + prof.getNome() + "!");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (PacienteInativoException e) {
            System.out.println(e.getMessage());
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (HorarioIndisponivelException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void cancelarConsulta() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Data (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Horario (HH:MM): ");
        String horario = sc.nextLine();

        try {
            Consulta consulta = servico.buscarConsulta(cpf, data, horario);
            servico.cancelarConsulta(consulta);

            System.out.print("Horario atual (HH:MM): ");
            String horaAtual = sc.nextLine();
            int hConsulta = Integer.parseInt(horario.substring(0, 2));
            int hAgora = Integer.parseInt(horaAtual.substring(0, 2));
            if (hConsulta - hAgora < 2) {
                System.out.println("Multa de R$50.00 aplicada!");
                servico.adicionarMulta(50.0);
            }
            System.out.println("Consulta cancelada.");
        } catch (ConsultaNaoEncontradaException e) {
            System.out.println(e.getMessage());
        } catch (OperacaoInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void remarcarConsulta() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Data original (DD/MM/AAAA): ");
        String dataOrig = sc.nextLine();
        System.out.print("Horario original (HH:MM): ");
        String horarioOrig = sc.nextLine();

        try {
            Consulta original = servico.buscarConsulta(cpf, dataOrig, horarioOrig);
            Profissional prof = servico.buscarProfissionalPorNome(original.getNomeProfissional());

            System.out.print("Nova data (DD/MM/AAAA): ");
            String novaData = sc.nextLine();
            System.out.print("Novo horario (HH:MM): ");
            String novoHorario = sc.nextLine();

            String diaSemana = descobrirDiaSemana(novaData);
            servico.remarcarConsulta(original, novaData, novoHorario, prof, diaSemana);
            System.out.println("Consulta remarcada com sucesso!");
        } catch (ConsultaNaoEncontradaException e) {
            System.out.println(e.getMessage());
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (OperacaoInvalidaException e) {
            System.out.println(e.getMessage());
        } catch (HorarioIndisponivelException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listarConsultas() {
        List<Consulta> lista = servico.getConsultas();
        if (lista.size() == 0) {
            System.out.println("Nenhuma consulta.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("[" + i + "] " + lista.get(i).exibirResumo());
        }
    }

    public static void buscarConsultasPorPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        List<Consulta> lista = servico.getConsultas();
        boolean achou = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCpfPaciente().equals(cpf)) {
                System.out.println("[" + i + "] " + lista.get(i).exibirResumo());
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhuma consulta encontrada.");
    }

    public static String descobrirDiaSemana(String data) {
        int dia = Integer.parseInt(data.substring(0, 2));
        int mes = Integer.parseInt(data.substring(3, 5));
        int ano = Integer.parseInt(data.substring(6, 10));
        if (mes < 3) {
            mes = mes + 12;
            ano = ano - 1;
        }
        int k = ano % 100;
        int j = ano / 100;
        int resultado = (dia + (13 * (mes + 1)) / 5 + k + k / 4 + j / 4 - 2 * j) % 7;
        if (resultado < 0) resultado = resultado + 7;
        String[] nomes = {"sabado", "domingo", "segunda", "terca", "quarta", "quinta", "sexta"};
        return nomes[resultado];
    }
    
    //atendimentos

    public static void menuAtendimentos() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- ATENDIMENTOS ---");
            System.out.println("1 - Registrar atendimento");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido!");
                continue;
            }
            if (op == 1) registrarAtendimento();
        }
    }

    public static void registrarAtendimento() {
        System.out.print("CPF do paciente: ");
        String cpf = sc.nextLine();
        System.out.print("Data da consulta (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Horario da consulta (HH:MM): ");
        String horario = sc.nextLine();

        try {
            Consulta consulta = servico.buscarConsulta(cpf, data, horario);
            Profissional prof = servico.buscarProfissionalPorNome(consulta.getNomeProfissional());

            System.out.print("Observacoes: ");
            String obs = sc.nextLine();
            System.out.print("Diagnostico: ");
            String diag = sc.nextLine();

            Atendimento atendimento = new Atendimento(consulta, data, obs, diag);

            System.out.print("Adicionar procedimentos? (1-Sim / 2-Nao): ");
            int addProc = Integer.parseInt(sc.nextLine());
            if (addProc == 1) {
                String proc = "";
                while (!proc.equals("fim")) {
                    System.out.print("Procedimento (ou 'fim'): ");
                    proc = sc.nextLine();
                    if (!proc.equals("fim")) {
                        atendimento.adicionarProcedimento(proc);
                    }
                }
            }

            // registra as info especifica do medico e oque ele faz
            prof.registrarEspecifico(atendimento);

            servico.registrarAtendimento(atendimento, consulta);
            System.out.println("\n--- RESUMO ---");
            System.out.println(atendimento.exibirResumo());
            System.out.println("Consulta marcada como realizada.");
        } catch (ConsultaNaoEncontradaException e) {
            System.out.println(e.getMessage());
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (OperacaoInvalidaException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Valor invalido!");
        }
    }

    //pagamentos

    public static void menuPagamentos() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PAGAMENTOS ---");
            System.out.println("1 - Registrar pagamento");
            System.out.println("2 - Listar pagamentos");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido!");
                continue;
            }

            switch (op) {
                case 1: registrarPagamento(); break;
                case 2: listarPagamentos(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    public static void registrarPagamento() {
        System.out.print("CPF do paciente: ");
        String cpf = sc.nextLine();
        System.out.print("Data da consulta (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Horario da consulta (HH:MM): ");
        String horario = sc.nextLine();

        try {
            Consulta consulta = servico.buscarConsulta(cpf, data, horario);
            Profissional prof = servico.buscarProfissionalPorNome(consulta.getNomeProfissional());
            Paciente paciente = servico.buscarPacientePorCpf(cpf);
            double valorBase = prof.getValorConsulta();

            System.out.println("Valor base: R$" + valorBase);
            System.out.print("Tipo de pagamento (1-Dinheiro/Pix / 2-Cartao / 3-Convenio): ");
            int tipoPag = Integer.parseInt(sc.nextLine());

            Pagamento pagamento;

            if (tipoPag == 1) {
                pagamento = new PagamentoDinheiro(consulta, valorBase);
            } else if (tipoPag == 2) {
                System.out.print("Parcelas (1 a 6): ");
                int parcelas = Integer.parseInt(sc.nextLine());
                if (parcelas < 1 || parcelas > 6) {
                    throw new PagamentoInvalidoException("Parcelas devem ser entre 1 e 6");
                }
                pagamento = new PagamentoCartao(consulta, valorBase, parcelas);
            } else if (tipoPag == 3) {
                String convenioNome = paciente.getConvenioNome();
                if (convenioNome.equals("")) {
                    throw new PagamentoInvalidoException("Paciente nao possui convenio");
                }
                double percentual = 0;
                if (convenioNome.equals("SaudePlus")) percentual = 40;
                else if (convenioNome.equals("VidaMais")) percentual = 30;
                else if (convenioNome.equals("BemEstar")) percentual = 50;

                Convenio convenio = new Convenio(convenioNome, percentual);
                convenio.adicionarEspecialidade("clinica geral");
                convenio.adicionarEspecialidade("fisioterapia");
                convenio.adicionarEspecialidade("psicologia");
                convenio.adicionarEspecialidade("nutricao");

                if (!convenio.cobreEspecialidade(prof.getEspecialidade())) {
                    throw new ConvenioNaoCobreException("Convenio nao cobre " + prof.getEspecialidade());
                }
                pagamento = new PagamentoConvenio(consulta, valorBase, convenio);
            } else {
                throw new PagamentoInvalidoException("Tipo de pagamento invalido");
            }

            // ligacao dinamica: o metodo chamado depende do tipo real do objeto nao do tipo da referencia
            double valorFinal = Math.round(pagamento.calcularValorFinal() * 100.0) / 100.0;
            System.out.println("Valor final: R$" + valorFinal);

            servico.registrarPagamento(pagamento);
            System.out.println("Pagamento registrado!");
        } catch (ConsultaNaoEncontradaException e) {
            System.out.println(e.getMessage());
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (PacienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (PagamentoInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (ConvenioNaoCobreException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Valor numerico invalido!");
        } finally {
            System.out.println("--- Operacao de pagamento finalizada ---");
        }
    }

    public static void listarPagamentos() {
        List<Pagamento> lista = servico.getPagamentos();
        if (lista.size() == 0) {
            System.out.println("Nenhum pagamento registrado.");
            return;
        }
        // ligacao dinamica: o metodo chamado depende do tipo real do objeto nao do tipo da referencia
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).exibirResumo());
        }
    }

    //relatorios

    public static void menuRelatorios() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- RELATORIOS ---");
            System.out.println("1 - Geral de consultas");
            System.out.println("2 - Por profissional");
            System.out.println("3 - Por periodo");
            System.out.println("4 - Resumo financeiro");
            System.out.println("5 - Relatorio unificado de cadastros");
            System.out.println("6 - Exportar dados");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido!");
                continue;
            }

            switch (op) {
                case 1: Relatorio.gerarRelatorioGeral(servico); break;
                case 2:
                    System.out.print("Nome do profissional: ");
                    String nome = sc.nextLine();
                    Relatorio.gerarRelatorioPorProfissional(servico, nome);
                    break;
                case 3:
                    System.out.print("Data inicio (DD/MM/AAAA): ");
                    String ini = sc.nextLine();
                    System.out.print("Data fim (DD/MM/AAAA): ");
                    String fim = sc.nextLine();
                    Relatorio.gerarRelatorioPorPeriodo(servico, ini, fim);
                    break;
                case 4: Relatorio.gerarResumoFinanceiro(servico); break;
                case 5: Relatorio.gerarRelatorioUnificado(servico); break;
                case 6: Relatorio.exportarDados(servico); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }
}

