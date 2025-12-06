package Strategy;

/**
 * PACFrete - Estratégia concreta para PAC
 *
 * Implementa a interface FreteStrategy e contém a lógica específica do PAC.
 * Manter a lógica em uma classe separada facilita testes e manutenção.
 */
public class PACFrete implements FreteStrategy {

    /**
     * Exemplo de implementação:
     * - Taxa fixa: 10
     * - Valor por kg: 1.2
     *
     * Em código real, valores como "10" e "1.2" viriam de configuração/DB.
     */
    @Override
    public double calcular(double peso) {
        // checagem simples para evitar negativos
        if (peso <= 0) {
            return 0;
        }
        return 10.0 + (peso * 1.2);
    }
}
