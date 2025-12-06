package Factory;

/**
 * SMSNotificacao - implementação concreta de Notificacao.
 *
 * Representa o envio de mensagens por SMS.
 */
public class SMSNotificacao implements Notificacao {

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando SMS: " + mensagem);
    }
}
