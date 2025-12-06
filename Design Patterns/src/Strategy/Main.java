package Strategy;

/**
 * Classe Main - demonstra o uso conjunto de Factory + Strategy.
 *
 * O cliente apenas pede "me dê a estratégia para esse tipo de frete" e usa o método calcular.
 * Ele não precisa conhecer classes concretas nem lógica de escolha.
 */
public class Main {
    public static void main(String[] args) {

        double peso = 5.0; // exemplo: 5 kg

        // Criamos as estratégias via factory — cliente só conhece a interface FreteStrategy
        FreteStrategy fretePAC = FreteFactory.criarFrete(TipoFrete.PAC);
        FreteStrategy freteSEDEX = FreteFactory.criarFrete(TipoFrete.SEDEX);
        FreteStrategy freteExpressa = FreteFactory.criarFrete(TipoFrete.EXPRESSA);

        // Uso polimórfico: o método calcular é chamado sem saber qual classe concreta está por trás
        System.out.println("PAC: R$ " + fretePAC.calcular(peso));
        System.out.println("SEDEX: R$ " + freteSEDEX.calcular(peso));
        System.out.println("Expressa: R$ " + freteExpressa.calcular(peso));
    }
}
