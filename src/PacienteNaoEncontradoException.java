public class PacienteNaoEncontradoException extends Exception {
    public PacienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    // SOBRECARGA: mesmo nome, parametros diferentes (resolvido em tempo de compilacao)
    public PacienteNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
