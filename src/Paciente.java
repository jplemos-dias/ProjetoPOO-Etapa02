public class Paciente extends Pessoa {
    private int idade;
    private String convenioNome;
    private boolean ativo;

    // so pode conter nome e cpf apenas
    public Paciente(String nome, String cpf) {
        super(nome, cpf);
        this.idade = 0;
        this.convenioNome = "";
        this.ativo = true;
    }

    // SOBRECARGA mesmo nome porem com parametros mudados
    public Paciente(String nome, String cpf, int idade, String telefone) {
        super(nome, cpf);
        setIdade(idade);
        setTelefone(telefone);
        this.convenioNome = "";
        this.ativo = true;
    }

    // construtor com os dados completos do pacientee
    public Paciente(String nome, String cpf, int idade, String telefone, String convenioNome) {
        super(nome, cpf);
        setIdade(idade);
        setTelefone(telefone);
        this.convenioNome = convenioNome;
        this.ativo = true;
    }

    // atualiza so idade e telefone
    public void complementar(int idade, String telefone) {
        setIdade(idade);
        setTelefone(telefone);
    }

    // SOBRECARGA: mesmo nome mais com os parametros diferentes
    public void complementar(int idade, String telefone, String convenioNome) {
        setIdade(idade);
        setTelefone(telefone);
        this.convenioNome = convenioNome;
    }

    public void desativar() {
        this.ativo = false;
    }

    // setter com validacao idade tem que ser valida, nao pode ser menor que 0
    public void setIdade(int idade) {
        if (idade < 0) {
            throw new IllegalArgumentException("Idade nao pode ser negativa");
        }
        this.idade = idade;
    }

    public int getIdade() {
        return idade;
    }

    public String getConvenioNome() {
        return convenioNome;
    }

    public void setConvenioNome(String convenioNome) {
        this.convenioNome = convenioNome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    // SOBRESCRITA: mesmo nome e os parametros classe filha redefine o comportamento
    @Override
    public String exibirResumo() {
        String status = "Sim";
        if (!ativo) {
            status = "Nao";
        }
        return "Paciente - Nome: " + getNome() + " | CPF: " + getCpf() + " | Idade: " + idade
                + " | Tel: " + getTelefone() + " | Convenio: " + convenioNome
                + " | Ativo: " + status;
    }
}
