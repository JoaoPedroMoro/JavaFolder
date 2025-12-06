package Reflection;

/**
 * Outra classe que será inspecionada pela classe de Reflection.
 * Isso demonstra que reflection funciona para QUALQUER objeto.
 */
public class Product {

    private String name;
    private double price;

    public Product() {}

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void applyDiscount(double value) {
        this.price -= value;
    }
}
