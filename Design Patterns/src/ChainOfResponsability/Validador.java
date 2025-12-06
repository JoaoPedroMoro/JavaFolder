package ChainOfResponsability;

/**
 *
 * Aqui definimos a interface comum que TODOS os validadores devem implementar.
 */
public interface Validador {

    /**
     * Define a ligação com o próximo validador da cadeia.
     *
     * @param proximo Validador seguinte
     */
    void setProximo(Validador proximo);

    /**
     * Método que executa a validação em questão.
     *
     * @param usuario objeto contendo os dados para validação
     */
    void validar(CadastroUsuario usuario);
}
