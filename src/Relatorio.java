import java.util.List;

public class Relatorio {

    public static void gerarRelatorioGeral(ClinicaServico servico) {
        System.out.println("\n=== RELATORIO GERAL ===");
        List<Consulta> consultas = servico.getConsultas();
        for (int i = 0; i < consultas.size(); i++) {
            System.out.println(consultas.get(i).exibirResumo());
            System.out.println("---");
        }
    }

    public static void gerarRelatorioPorProfissional(ClinicaServico servico, String nomeProfissional) {
        System.out.println("\n=== RELATORIO - " + nomeProfissional + " ===");
        List<Consulta> consultas = servico.getConsultas();
        boolean achou = false;
        for (int i = 0; i < consultas.size(); i++) {
            if (consultas.get(i).getNomeProfissional().equals(nomeProfissional)) {
                System.out.println(consultas.get(i).exibirResumo());
                System.out.println("---");
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Nenhuma consulta encontrada para esse profissional.");
        }
    }

    public static void gerarRelatorioPorPeriodo(ClinicaServico servico, String dataInicio, String dataFim) {
        System.out.println("\n=== RELATORIO - " + dataInicio + " a " + dataFim + " ===");
        List<Consulta> consultas = servico.getConsultas();
        for (int i = 0; i < consultas.size(); i++) {
            if (estaNoIntervalo(consultas.get(i).getData(), dataInicio, dataFim)) {
                System.out.println(consultas.get(i).exibirResumo());
                System.out.println("---");
            }
        }
    }

    public static void gerarResumoFinanceiro(ClinicaServico servico) {
        List<Consulta> consultas = servico.getConsultas();
        List<Pagamento> pagamentos = servico.getPagamentos();
        List<Double> multas = servico.getMultas();

        int realizadas = 0;
        int canceladas = 0;
        double totalFaturado = 0;
        double totalEmMultas = 0;

        for (int i = 0; i < consultas.size(); i++) {
            if (consultas.get(i).getStatus().equals("realizada")) realizadas++;
            if (consultas.get(i).getStatus().equals("cancelada")) canceladas++;
        }

        //ligacao dinamica: o metodo chamado depende do tipo real do objeto nao do tipo da referencia
        for (int i = 0; i < pagamentos.size(); i++) {
            totalFaturado = totalFaturado + pagamentos.get(i).calcularValorFinal();
        }

        for (int i = 0; i < multas.size(); i++) {
            totalEmMultas = totalEmMultas + multas.get(i);
        }

        System.out.println("\n=== RESUMO FINANCEIRO ===");
        System.out.println("Atendimentos realizados: " + realizadas);
        System.out.println("Total faturado: R$" + Math.round(totalFaturado * 100.0) / 100.0);
        System.out.println("Cancelamentos: " + canceladas);
        System.out.println("Total em multas: R$" + Math.round(totalEmMultas * 100.0) / 100.0);
    }

    //juntei tudo em um relatorio so usando polimorfismo e ligacao dinamica.
    public static void gerarRelatorioUnificado(ClinicaServico servico) {
        System.out.println("\n=== RELATORIO UNIFICADO DE CADASTROS ===");
        List<Pessoa> pessoas = servico.getPessoas();
        int totalPacientes = 0;
        int totalProfissionais = 0;

        // LIGACAO DINAMICA: o metodo chamado depende do tipo REAL do objeto, nao do tipo da referencia
        for (int i = 0; i < pessoas.size(); i++) {
            Pessoa p = pessoas.get(i);
            System.out.println(p.exibirResumo());

            // dynamic casting pra saber se e paciente ou medico
            if (p instanceof Paciente) {
                totalPacientes++;
            } else if (p instanceof Profissional) {
                totalProfissionais++;
            }
            System.out.println("---");
        }
        System.out.println("Total pacientes: " + totalPacientes);
        System.out.println("Total profissionais: " + totalProfissionais);
    }

    //exporta dados de todas entidades que implementam exportavel
    public static void exportarDados(ClinicaServico servico) {
        System.out.println("\n=== EXPORTACAO DE DADOS ===");

        System.out.println("\n-- Consultas --");
        List<Consulta> consultas = servico.getConsultas();
        for (int i = 0; i < consultas.size(); i++) {
            System.out.println(consultas.get(i).exportarDados());
        }

        System.out.println("\n-- Atendimentos --");
        List<Atendimento> atendimentos = servico.getAtendimentos();
        for (int i = 0; i < atendimentos.size(); i++) {
            System.out.println(atendimentos.get(i).exportarDados());
        }

        System.out.println("\n-- Pagamentos --");
        List<Pagamento> pagamentos = servico.getPagamentos();
        for (int i = 0; i < pagamentos.size(); i++) {
            System.out.println(pagamentos.get(i).exportarDados());
        }
    }

    public static boolean estaNoIntervalo(String data, String inicio, String fim) {
        int valorData = converterDataParaNumero(data);
        int valorInicio = converterDataParaNumero(inicio);
        int valorFim = converterDataParaNumero(fim);
        return valorData >= valorInicio && valorData <= valorFim;
    }

    private static int converterDataParaNumero(String data) {
        int dia = Integer.parseInt(data.substring(0, 2));
        int mes = Integer.parseInt(data.substring(3, 5));
        int ano = Integer.parseInt(data.substring(6, 10));
        return ano * 10000 + mes * 100 + dia;
    }
}
