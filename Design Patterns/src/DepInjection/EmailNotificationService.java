package DepInjection;

/**
 * Implementação concreta usando email.
 * Esta classe poderia enviar email real, mas aqui é só exemplo.
 */
public class EmailNotificationService implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("📧 Enviando EMAIL: " + message);
    }
}
