package DepInjection;

/**
 * Segunda implementação, usando SMS.
 * Poderíamos ter centenas de implementações e o cliente não muda.
 */
public class SMSNotificationService implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("📱 Enviando SMS: " + message);
    }
}
