package Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Esta classe demonstra o uso de REFLECTION em Java.
 *
 * Ela recebe QUALQUER objeto e exibe:
 *  - Nome da classe
 *  - Atributos (fields)
 *  - Métodos (methods)
 *  - Modificadores (public, private...)
 *
 * O uso típico é:
 *     ObjectInspector.inspect(new Person("Ana", 30));
 *     ObjectInspector.inspect(new Product("TV", 3500.0));
 *
 * Reflection é útil para frameworks, validações automáticas
 * e mapeamento entre objetos e bancos de dados (ORM).
 */
public class ObjectInspector {

    /**
     * Inspeciona um objeto usando reflection e imprime detalhes.
     * @param obj qualquer instância de qualquer classe
     */
    public static void inspect(Object obj) {

        // Descobre dinamicamente o tipo do objeto
        Class<?> clazz = obj.getClass();

        System.out.println("\n===== INSPECTING OBJECT =====");
        System.out.println("Classe: " + clazz.getName());

        // ---------------------------------------------------------------
        // LISTANDO ATRIBUTOS
        // ---------------------------------------------------------------
        System.out.println("\n--- Atributos (Fields) ---");
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // permite acessar private

            String modifiers = Modifier.toString(field.getModifiers());
            String type = field.getType().getSimpleName();
            String name = field.getName();

            System.out.println(
                modifiers + " " + type + " " + name
            );
        }

        // ---------------------------------------------------------------
        // LISTANDO MÉTODOS
        // ---------------------------------------------------------------
        System.out.println("\n--- Métodos (Methods) ---");
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {

            String modifiers = Modifier.toString(method.getModifiers());
            String returnType = method.getReturnType().getSimpleName();
            String name = method.getName();

            System.out.print(modifiers + " " + returnType + " " + name + "(");

            // Lista parâmetros de cada método
            Class<?>[] params = method.getParameterTypes();
            for (int i = 0; i < params.length; i++) {
                System.out.print(params[i].getSimpleName());
                if (i < params.length - 1) System.out.print(", ");
            }

            System.out.println(")");
        }

        System.out.println("\n===== END =====\n");
    }
}
