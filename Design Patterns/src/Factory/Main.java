package Factory;

/**
 * Classe Main para demonstrar o uso do Factory Pattern.
 *
 * Note como o cliente NÃO instancia classes diretamente.
 * Apenas usa a Factory.
 */

public class Main {

    public static void main(String[] args) {

        // Criando notificações via Factory
        Notificacao email = NotificacaoFactory.criarNotificacao(TipoNotificacao.EMAIL);
        Notificacao sms = NotificacaoFactory.criarNotificacao(TipoNotificacao.SMS);
        Notificacao push = NotificacaoFactory.criarNotificacao(TipoNotificacao.PUSH);

        // Usando as notificações
        email.enviar("Olá por e-mail!");
        sms.enviar("Olá por SMS!");
        push.enviar("Olá via push notification!");
    }
}
