package Reflection;

public class Main {
    public static void main(String[] args) {

        Person p = new Person("João", 25);
        Product pr = new Product("Notebook", 5000.00);

        ObjectInspector.inspect(p);
        ObjectInspector.inspect(pr);
    }
}
