package ChainOfResponsability;

/**
 * NomeObrigatorioValidador
 *
 * Responsável por verificar se o nome não está vazio ou nulo.
 */
public class NomeObrigatorioValidador extends ValidadorBase {

    @Override
    public void validar(CadastroUsuario usuario) {

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }

        // Se passou na validação, chama o próximo da cadeia
        validarProximo(usuario);
    }
}
