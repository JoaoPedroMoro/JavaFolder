package Strategy;

/**
 * SedexFrete - Estratégia concreta para SEDEX
 *
 * Exemplo de lógica mais cara que o PAC (taxa fixa + valor por kg maior).
 */
public class SedexFrete implements FreteStrategy {

    @Override
    public double calcular(double peso) {
        if (peso <= 0) {
            return 0;
        }
        // SEDEX é mais rápido e mais caro: taxa fixa 20 + 2.5 por kg
        return 20.0 + (peso * 2.5);
    }
}
