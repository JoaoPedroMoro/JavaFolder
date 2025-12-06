package DepInjection;

/**
 * Esta classe representa algo como um "controller" ou "serviço"
 * que precisa enviar notificações.
 *
 * Observe que ela NÃO instancia EmailNotificationService nem SMSNotificationService.
 * A dependência chega pronta — isso é DI.
 */
public class UserController {

    // DEPENDÊNCIA — note que é a interface, não a implementação
    private final NotificationService notificationService;

    /**
     * Dependência é injetada pelo construtor.
     * Este é o padrão clássico de DI.
     */
    public UserController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void registerUser(String name) {
        System.out.println("👤 Registrando usuário: " + name);

        // Usa a interface (polimorfismo)
        notificationService.send("Bem-vindo, " + name + "!");
    }
}
