package Factory;

/**
 * PushNotificacao - implementação concreta de Notificacao.
 *
 * Este tipo simula o envio de push notifications para apps.
 */
public class PushNotificacao implements Notificacao {

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando PUSH: " + mensagem);
    }
}
