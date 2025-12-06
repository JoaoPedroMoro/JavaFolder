package Factory;

/**
 * O Factory Pattern (Padrão de Projeto Criacional) tem como objetivo centralizar
 * a criação de objetos em uma única classe (a Fábrica), removendo a necessidade
 * de usar "new" diretamente no código cliente. Assim, o cliente NÃO precisa
 * saber qual classe concreta deve ser criada.
 * 
 * O problema que ele resolve:
 * - Evitar "if/else" ou "switch" espalhados pelo sistema para decidir qual
 *   classe instanciar.
 * - Encapsular a lógica de criação em uma única classe, facilitando manutenção.
 * 
 * Benefícios:
 * - Desacoplamento: o cliente depende apenas de uma INTERFACE (Notificacao),
 *   e não das classes concretas (EmailNotificacao, SMSNotificacao...).
 * - Manutenção fácil: adicionar uma nova forma de notificação exige criar
 *   uma nova classe e ajustar a Factory — sem mexer no cliente.
 *   
 * NotificacaoFactory - Classe responsável por criar instâncias de Notificacao.
 *
 * Este é o coração do Factory Pattern:
 * Ele recebe um TipoNotificacao e decide QUAL classe concreta instanciar.
 *
 * O cliente NÃO usa "new EmailNotificacao()" diretamente.
 * Ele apenas chama: NotificacaoFactory.criarNotificacao(TipoNotificacao.EMAIL)
 *
 * Assim, isolamos a lógica de criação dentro desta fábrica.
 */
public class NotificacaoFactory {

    /**
     * Método estático que cria a implementação correta de Notificacao com base no tipo.
     *
     * @param tipo enum TipoNotificacao indicando qual tipo criar
     * @return objeto concreto que implementa Notificacao
     */
    public static Notificacao criarNotificacao(TipoNotificacao tipo) {

        // switch centralizado → caso necessário alterar a criação, alterar apenas aqui
        switch (tipo) {
            case EMAIL:
                return new EmailNotificacao();

            case SMS:
                return new SMSNotificacao();

            case PUSH:
                return new PushNotificacao();

            default:
                throw new IllegalArgumentException("Tipo de notificação inválido: " + tipo);
        }
    }
}
