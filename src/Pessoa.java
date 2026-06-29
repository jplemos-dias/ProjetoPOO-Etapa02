public abstract class Pessoa {
    // atributos para as classes Paciente e profissional acessarem
    protected String nome;
    private String cpf;
    private String telefone;
    private String dataNascimento;

    // so pode ter nome e cpf
    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        setCpf(cpf);
        this.telefone = "";
        this.dataNascimento = "";
    }

    // SOBRECARGA: mesmo nome oque muda sao so os parametros
    public Pessoa(String nome, String cpf, String telefone, String dataNascimento) {
        this.nome = nome;
        setCpf(cpf);
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    // metodo abstrato as subclasses vao implementar cada uma do seu jeito
    public abstract String exibirResumo();

    // setter com validacao cpf tem que ter so nuumeros e nao pode esta vazio
    public void setCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF nao pode ser vazio");
        }
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
