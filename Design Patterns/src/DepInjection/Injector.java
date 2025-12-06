package DepInjection;

/**
 * Esta classe simula um "Container de Injeção".
 *
 * Em frameworks como Spring, isso é feito automaticamente.
 * Aqui mostramos a ideia raiz do DI: separar a criação de objetos
 * da lógica do seu uso.
 */
public class Injector {

    /**
     * Você pode escolher dinamicamente qual implementação fornecer
     * dependendo de configurações, ambiente, banco de dados, etc.
     */
    public static NotificationService provideNotificationService() {

        // Exemplo: escolha a implementação aqui
         return new EmailNotificationService();
//        return new SMSNotificationService();

        // Poderia vir de config:
        // if(config == "email") return new Email...
        // if(config == "sms") return new SMS...
    }

    public static UserController provideUserController() {
        return new UserController(provideNotificationService());
    }
}
