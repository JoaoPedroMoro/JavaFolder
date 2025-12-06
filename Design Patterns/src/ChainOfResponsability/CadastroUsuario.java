package ChainOfResponsability;

/**
 * Classe simples contendo os dados do usuário.
 * Usada como entrada para a cadeia de validadores.
 */
public class CadastroUsuario {

    private String nome;
    private String email;
    private String senha;

    public CadastroUsuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
