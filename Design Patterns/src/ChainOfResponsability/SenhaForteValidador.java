package ChainOfResponsability;

/**
 * SenhaForteValidador
 *
 * Exemplo de senha forte: pelo menos 8 caracteres.
 * (Você pode melhorar colocando números, letras maiúsculas, símbolos, etc.)
 */
public class SenhaForteValidador extends ValidadorBase {

    @Override
    public void validar(CadastroUsuario usuario) {

        String senha = usuario.getSenha();

        if (senha == null || senha.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
        }

        validarProximo(usuario);
    }
}
