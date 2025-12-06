package DepInjection;

public class Main {
    public static void main(String[] args) {

        // Pede um UserController ao Injector
        UserController controller = Injector.provideUserController();

        // Usa normalmente
        controller.registerUser("João");
    }
}
