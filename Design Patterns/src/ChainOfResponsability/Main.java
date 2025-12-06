package ChainOfResponsability;

/**
 * O Chain of Responsibility (CoR) é um padrão de projeto comportamental que 
 * permite passar uma solicitação por uma cadeia de manipuladores (handlers),
 * onde cada manipulador decide:
 *   - processar a requisição,
 *   - ou passá-la adiante,
 *   - ou rejeitá-la.
 *
 * Problema que resolve:
 * - Evita if/else encadeados para fazer várias validações sequenciais.
 * - Separa cada regra de validação em uma classe própria.
 * - Facilita adicionar, remover ou reorganizar validações.
 *
 * Funcionamento:
 * - Cada validador tem um "próximo" na cadeia.
 * - Cada validador executa sua validação.
 * - Se passar, chama o próximo.
 * - Se falhar, lança exceção ou retorna erro.
 * 
 * Demonstração prática do Chain of Responsibility aplicado à validação de cadastro.
 */
public class Main {

    public static void main(String[] args) {

        // Criação do usuário (pode alterar para testar erros)
        CadastroUsuario usuario = new CadastroUsuario(
                "João",
                "joao@gmail.com",
                "senhaSegura123"
        );

        // Criando os validadores individuais
        Validador nomeValidador = new NomeObrigatorioValidador();
        Validador emailValidador = new EmailValidoValidador();
        Validador senhaValidador = new SenhaForteValidador();

        // Montando a cadeia → nome → email → senha
        nomeValidador.setProximo(emailValidador);
        emailValidador.setProximo(senhaValidador);

        // Executando a cadeia começando pelo primeiro validador
        nomeValidador.validar(usuario);

        System.out.println("Usuário validado com sucesso!");
    }
}
