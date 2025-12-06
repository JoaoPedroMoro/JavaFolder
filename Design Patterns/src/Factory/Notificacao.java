package Factory;

/**
 * 
 * Nesta interface definimos o "contrato" que TODAS as formas de notificação 
 * devem implementar. O cliente só conhece esta interface, nunca as classes concretas.
 */
public interface Notificacao {

    /**
     * Método responsável por enviar a notificação.
     *
     * @param mensagem conteúdo textual da mensagem
     */
    void enviar(String mensagem);
}
