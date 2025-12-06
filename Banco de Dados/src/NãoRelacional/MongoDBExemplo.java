package NãoRelacional;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;

public class MongoDBExemplo {

    public static void main(String[] args) {

        /****************************************************************************************
         * 1) CONEXÃO COM O MONGODB
         * --------------------------------------------------------------------------------------
         * O MongoDB roda localmente na porta padrão 27017.
         * O método MongoClients.create() cria um cliente de conexão.
         ****************************************************************************************/
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        System.out.println("Conectado ao MongoDB com sucesso!");

        /****************************************************************************************
         * 2) CRIAR / ACESSAR UM DATABASE
         * --------------------------------------------------------------------------------------
         * O MongoDB NÃO exige CREATE DATABASE.
         * Se o banco não existir, ele só é criado após o primeiro insert.
         ****************************************************************************************/
        MongoDatabase database = mongoClient.getDatabase("loja"); // nome do banco
        System.out.println("Database selecionado: loja");

        /****************************************************************************************
         * 3) CRIAR / ACESSAR UMA COLLECTION (tabela)
         * --------------------------------------------------------------------------------------
         * Collections = tabelas de documentos.
         * Igual o database:
         *     - não precisa "criar" explicitamente
         *     - é criado automaticamente ao inserir algo
         ****************************************************************************************/
        MongoCollection<Document> usuarios = database.getCollection("usuarios");
        System.out.println("Collection 'usuarios' selecionada!");

        /****************************************************************************************
         * 4) CRIANDO UM DOCUMENTO (equivalente ao INSERT)
         * --------------------------------------------------------------------------------------
         * Document é equivalente a um registro JSON.
         ****************************************************************************************/
        Document usuario1 = new Document()
                .append("nome", "João da Costa")
                .append("email", "joaocosta@email.com")
                .append("idade", 30);

        /****************************************************************************************
         * 5) INSERINDO O DOCUMENTO
         ****************************************************************************************/
        InsertOneResult resultado = usuarios.insertOne(usuario1);

        System.out.println("Documento inserido com _id: " + resultado.getInsertedId());

        /****************************************************************************************
         * 6) INSERINDO VÁRIOS DOCUMENTOS DE UMA VEZ (INSERT MANY)
         ****************************************************************************************/
        Document usuario2 = new Document()
                .append("nome", "Lucas Fernandes")
                .append("email", "lucas@email.com")
                .append("idade", 27);

        Document usuario3 = new Document()
                .append("nome", "Maria Oliveira")
                .append("email", "maria@email.com")
                .append("idade", 22);

        usuarios.insertMany(java.util.Arrays.asList(usuario2, usuario3));
        System.out.println("Vários documentos inseridos!");

        /****************************************************************************************
         * 7) BUSCANDO DOCUMENTOS (equivalente ao SELECT * FROM usuarios)
         ****************************************************************************************/
        System.out.println("\n=== LISTA DE USUÁRIOS ===");

        FindIterable<Document> lista = usuarios.find();

        for (Document doc : lista) {
            System.out.println(doc.toJson());
        }

        /****************************************************************************************
         * 8) FECHAR CONEXÃO
         ****************************************************************************************/
        mongoClient.close();
        System.out.println("\nConexão com MongoDB encerrada!");
    }
}
