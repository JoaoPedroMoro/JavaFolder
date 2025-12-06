package Strategy;

/**
 * O Factory Pattern é um padrão de criação que centraliza a criação de objetos em um único lugar (a fábrica), isolando o cliente
 * da lógica de criação. Evite o problema de espalhar vários "new Tipo()" pelo código cliente e encapsula lógica de escolha/instanciação.
 * O benefício é desacoplar o código cliente das classes concretas, trocar a implementação passa a ser simples.
 * 
 * FreteFactory - Factory Pattern (fábrica simples/estática)
 *
 * Responsável por instanciar a estratégia correta (FreteStrategy) com base no TipoFrete.
 * O cliente (quem solicita o frete) chama FreteFactory.criarFrete(tipo) e recebe
 * uma referência a FreteStrategy, sem saber qual implementação concreta foi criada.
 *
 * Observações:
 * - Se a criação exigir parâmetros (ex.: configuração, credenciais), podemos adaptar
 *   a fábrica para receber essas dependências.
 * - Alternativa mais avançada: usar Inversion of Control / Dependency Injection.
 */
public class FreteFactory {

    /**
     * Cria/retorna uma implementação de FreteStrategy conforme o tipo.
     *
     * @param tipo TipoFrete (enum)
     * @return implementação de FreteStrategy correspondente
     */
    public static FreteStrategy criarFrete(TipoFrete tipo) {
        // Switch centraliza a lógica de criação — fácil de manter/estender
        switch (tipo) {
            case PAC:
                return new PACFrete();

            case SEDEX:
                return new SedexFrete();

            case EXPRESSA:
                return new TransportadoraExpressaFrete();

            default:
                // Lançar exceção em caso de tipo desconhecido é uma prática segura
                throw new IllegalArgumentException("Tipo de frete inválido: " + tipo);
        }
    }
}
