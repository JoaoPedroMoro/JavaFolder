package Factory;

/**
 * Enum que lista todos os tipos possíveis de notificação.
 *
 * O uso de enum evita o uso de Strings soltas no código (evita erros de digitação)
 * e permite suporte do compilador.
 */
public enum TipoNotificacao {
    EMAIL,
    SMS,
    PUSH
}
