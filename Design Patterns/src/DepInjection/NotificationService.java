package DepInjection;

/**
 * Interface base que o controlador irá usar.
 * Quem recebe esta interface NÃO sabe qual implementação está sendo usada.
 *
 * Isso é a base da Injeção de Dependência: programar para interfaces,
 * não para implementações.
 */
public interface NotificationService {
    void send(String message);
}
