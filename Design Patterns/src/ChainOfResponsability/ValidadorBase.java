package ChainOfResponsability;

/**
 * ValidadorBase - Classe abstrata que implementa a lógica padrão de
 * encadeamento de validadores.
 *
 * Cada validador concreto só precisa se preocupar com sua validação específica.
 */
public abstract class ValidadorBase implements Validador {

    protected Validador proximo;

    @Override
    public void setProximo(Validador proximo) {
        this.proximo = proximo;
    }

    /**
     * Método auxiliar para chamar o próximo validador (caso exista).
     */
    protected void validarProximo(CadastroUsuario usuario) {
        if (proximo != null) {
            proximo.validar(usuario);
        }
    }
}
