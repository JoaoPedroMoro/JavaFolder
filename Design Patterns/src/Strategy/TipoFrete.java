package Strategy;

/**
 * Enum TipoFrete - usado para identificar claramente os tipos de frete.
 *
 * Usar enum evita "strings mágicas" e permite suporte por compilador (auto-complete,
 * segurança de tipo).
 */
public enum TipoFrete {
    PAC,
    SEDEX,
    EXPRESSA
}
