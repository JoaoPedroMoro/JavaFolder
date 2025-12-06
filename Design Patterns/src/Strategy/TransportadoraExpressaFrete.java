package Strategy;

/**
 * TransportadoraExpressaFrete - Estratégia concreta para uma transportadora expressa
 *
 * Pode representar um parceiro externo, API de transportadora, etc.
 * Aqui apenas um cálculo de exemplo.
 */
public class TransportadoraExpressaFrete implements FreteStrategy {

    @Override
    public double calcular(double peso) {
        if (peso <= 0) {
            return 0;
        }
        // Exemplo: preço base maior para entrega expressa
        return 30.0 + (peso * 3.8);
    }
}
