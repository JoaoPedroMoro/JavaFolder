package Strategy;

/**
 * o Strategy Pattern é um padrão de projeto comportamental que permite definir uma família de algoritmos (ou comportamentos), encapsulá-los
 * em classes separadas e torná-los intercambiáveis. Resolve o problema de ter if/else e switch espalhados pelo código quando o comportamento
 * varia. O benefício é separar as regras de cálculo em classes, facilitando testes, manutenção e adição de novos algoritmos sem tocar no
 * código cliente.
 * 
 * FreteStrategy - Interface Strategy
 *
 * Esta interface define o contrato que todas as estratégias de cálculo de frete
 * devem seguir. Note que o cliente (quem usa) só conhece esta interface, não
 * as implementações concretas.
 *
 * Usamos double calcular(double peso) apenas como exemplo simples — em um sistema
 * real você poderia passar mais dados (distância, dimensões, tipo de entrega, endereço, etc).
 */
public interface FreteStrategy {

    /**
     * Calcula o valor do frete com base no peso (ou outro critério).
     *
     * @param peso peso do pacote em kg
     * @return valor do frete em reais (exemplo)
     */
    double calcular(double peso);
}
