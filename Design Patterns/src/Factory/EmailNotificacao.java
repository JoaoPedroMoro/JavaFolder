package Factory;

/**
 * EmailNotificacao - implementação concreta da interface Notificacao.
 *
 * Esta classe é uma das opções de notificação que a Factory pode criar.
 * Cada classe concreta tem sua lógica específica de envio.
 */
public class EmailNotificacao implements Notificacao {

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando EMAIL: " + mensagem);
    }
}
