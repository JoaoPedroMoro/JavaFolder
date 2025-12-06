package ChainOfResponsability;

import java.util.regex.Pattern;

/**
 * EmailValidoValidador
 *
 * Aqui verificamos se o email segue um formato básico válido.
 */
public class EmailValidoValidador extends ValidadorBase {

    private static final Pattern PADRAO_EMAIL =
        Pattern.compile("^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$");

    @Override
    public void validar(CadastroUsuario usuario) {

        if (usuario.getEmail() == null || 
            !PADRAO_EMAIL.matcher(usuario.getEmail()).matches()) {

            throw new IllegalArgumentException("Email inválido.");
        }

        validarProximo(usuario);
    }
}
