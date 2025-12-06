package Reflection;

/**
 * Classe simples de exemplo apenas para demonstrar como
 * o mecanismo de reflection consegue inspeccionar atributos
 * e métodos em tempo de execução.
 */
public class Person {

    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void sayHello() {
        System.out.println("Olá! Meu nome é " + name);
    }

    private void secretMethod() {
        System.out.println("Este é um método privado!");
    }
}
