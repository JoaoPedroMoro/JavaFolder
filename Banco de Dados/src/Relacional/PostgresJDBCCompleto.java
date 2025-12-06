package Relacional;
/***********************************************************************************************
 * EXEMPLO COMPLETO DE JDBC COM POSTGRESQL
 * ---------------------------------------------------------------------------------------------
 * Este exemplo cobre:
 *  - Como conectar ao PostgreSQL usando JDBC
 *  - Como preparar e executar queries
 *  - Como inserir dados (INSERT)
 *  - Como consultar dados (SELECT)
 *  - Como atualizar dados (UPDATE)
 *  - Como remover dados (DELETE)
 *  - Como executar JOINs usando JDBC
 *  
 ***********************************************************************************************/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class PostgresJDBCCompleto {

    public static void main(String[] args) {

        /*******************************************************************************************
         * 1) CONFIGURAÇÃO DA CONEXÃO
         * -----------------------------------------------------------------------------------------
         * Aqui nós definimos:
         *    - URL do banco
         *    - Usuário
         *    - Senha
         * 
         * A URL segue o padrão JDBC:
         *      jdbc:postgresql://HOST:PORTA/NOME_DO_BANCO
         * 
         * O PostgreSQL localmente costuma ter as seguintes informações:
         *    host = localhost
         *    porta = 5432 (padrão)
         *******************************************************************************************/
        String url = "jdbc:postgresql://localhost:5432/dev";
        String user = "postgres";
        String password = "admin";

        try {

            /****************************************************************************************
             * 2) ABRINDO A CONEXÃO E CRIANDO AS TABELAS CASO NÃO EXISTA,
             * --------------------------------------------------------------------------------------
             * DriverManager.getConnection() cria a conexão com o banco.
             ****************************************************************************************/
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado ao PostgreSQL com sucesso!\n");
            
            String sqlCreateTableUsuarios = """
            	    CREATE TABLE IF NOT EXISTS usuarios (
            	        id SERIAL PRIMARY KEY,
            	        nome VARCHAR(100) NOT NULL,
            	        email VARCHAR(100) NOT NULL UNIQUE
            	    );
            	""";
            
            String sqlCreateTablePedidos = """
            	    CREATE TABLE IF NOT EXISTS pedidos (
            	        id SERIAL PRIMARY KEY,
            	        descricao VARCHAR(255) NOT NULL,
            	        valor NUMERIC(10,2) NOT NULL,  
            	        usuario_id INTEGER NOT NULL,
            	        data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
            	        
            			CONSTRAINT fk_usuario
            				FOREIGN KEY (usuario_id)
				            REFERENCES usuarios(id)
				            ON DELETE CASCADE 
            	    );
            	""";

        	try (PreparedStatement stmt = conn.prepareStatement(sqlCreateTableUsuarios)) {
        	    stmt.execute();
        	    System.out.println("Tabela 'usuarios' verificada/criada com sucesso!");
        	}
        	
        	try (PreparedStatement stmt = conn.prepareStatement(sqlCreateTablePedidos)) {
        	    stmt.execute();
        	    System.out.println("Tabela 'pedidos' verificada/criada com sucesso!");
        	}


            /****************************************************************************************
             * 3) INSERINDO DADOS (INSERT)
             * --------------------------------------------------------------------------------------
             * Vamos inserir um usuário na tabela 'usuarios' e um pedido na tabela 'pedidos'
             * 
             * PreparedStatement:
             *   - É usado para evitar SQL Injection
             *   - Permite substituir parâmetros (?) por valores reais
             *   
             *  É feita uma verificação para ver se o e-mail não existe no banco de dados.
             ****************************************************************************************/
        	
        	// A implementação a seguir pular os PK mesmo se der falha resultando em PK que não estão em ordem, vamos tratar isso de outra maneira.
//        	String sqlInsert = """
//        		    INSERT INTO usuarios (nome, email)
//        		    VALUES (?, ?)
//        		    ON CONFLICT (email) DO NOTHING;
//        		""";
//
//
//            PreparedStatement insertStmt = conn.prepareStatement(sqlInsert);
//            insertStmt.setString(1, "João da Costa");
//            insertStmt.setString(2, "joaocosta@email.com");
//
//            int linhasInseridas = insertStmt.executeUpdate();
//            System.out.println("Linhas inseridas na tabela usuários: " + linhasInseridas);       
//            
        	
        	// 1. Verifica se existe
        	String sqlSelect = "SELECT id FROM usuarios WHERE email = ?";
        	try (PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {
        	    pstmt.setString(1, "joaocosta@email.com");

        	    ResultSet rs = pstmt.executeQuery();
        	    if (!rs.next()) {
        	        // 2. Não existe, insere
        	        String sqlInsert = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
        	        try (PreparedStatement insert = conn.prepareStatement(sqlInsert)) {
        	            insert.setString(1, "João da Costa");
        	            insert.setString(2, "joaocosta@email.com");
        	            insert.executeUpdate();
        	            System.out.println("Usuário criado!");
        	        }
        	    } else {
        	        System.out.println("Usuário já existe. ID: " + rs.getInt("id"));
        	    }
        	}
        	
        	try (PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {
        	    pstmt.setString(1, "lucfernandes@email.com");

        	    ResultSet rs = pstmt.executeQuery();
        	    if (!rs.next()) {
        	        String sqlInsert = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
        	        try (PreparedStatement insert = conn.prepareStatement(sqlInsert)) {
        	            insert.setString(1, "Lucas Fernandes");
        	            insert.setString(2, "lucfernandes@email.com");
        	            insert.executeUpdate();
        	            System.out.println("Usuário criado!");
        	        }
        	    } else {
        	        System.out.println("Usuário já existe. ID: " + rs.getInt("id"));
        	    }
        	}
        	
        	try (PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {
        	    pstmt.setString(1, "emailerrado@email.com");

        	    ResultSet rs = pstmt.executeQuery();
        	    if (!rs.next()) {
        	        String sqlInsert = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
        	        try (PreparedStatement insert = conn.prepareStatement(sqlInsert)) {
        	            insert.setString(1, "Usuario desconhecido");
        	            insert.setString(2, "emailerrado@email.com");
        	            insert.executeUpdate();
        	            System.out.println("Usuário criado!");
        	        }
        	    } else {
        	        System.out.println("Usuário já existe. ID: " + rs.getInt("id"));
        	    }
        	}

        	String sqlInsertPedido = """
        		    INSERT INTO pedidos (descricao, valor, usuario_id)
        		    VALUES (?, ?, ?);
        		""";
        	
            // O código a seguir insere valor por valor, vamos tentar implementar dinamicamente esses valores, sem ter que fazer 1 por 1.
//        	PreparedStatement insertStmt = conn.prepareStatement(sqlInsert);
//            insertStmt.setString(1, "2 Camisetas brancas, 1 calça jeans");
//            insertStmt.setDouble(2, 350.0);
//            insertStmt.setInt(3, 1);
//
//            int linhasInseridas = insertStmt.executeUpdate();
//            System.out.println("Linhas inseridas na tabela pedidos: " + linhasInseridas);
//
//            insertStmt.close();
        	
        	// 2) Lista de pedidos (cada pedido é um array com [descricao, valor, usuario_id])
        	List<Object[]> pedidos = List.of(
        	    new Object[]{"2 Camisetas brancas, 1 calça jeans", 350.00, 1},
        	    new Object[]{"3 Tênis", 1200.00, 1},
        	    new Object[]{"2 Bonés, 2 Calças e 1 Camiseta", 1075.99, 2},
        	    new Object[]{"1 Terno, 1 Sapato social e 1 Gravata", 2299.99, 2}
        	);

        	try (PreparedStatement stmt = conn.prepareStatement(sqlInsertPedido)) {

        	    // 4) Percorre a lista e insere dinamicamente
        	    for (Object[] p : pedidos) {

        	        stmt.setString(1, (String) p[0]);     // descricao
        	        stmt.setDouble(2, (Double) p[1]);      // valor
        	        stmt.setInt   (3, (Integer) p[2]);     // usuario_id

        	        int linhas = stmt.executeUpdate();
        	        System.out.println("Pedido inserido! Linhas afetadas = " + linhas);
        	    }

        	} catch (Exception e) {
        	    e.printStackTrace();
        	}


            /****************************************************************************************
             * 4) CONSULTANDO DADOS (SELECT)
             * --------------------------------------------------------------------------------------
             * Aqui vamos consultar todos os usuários cadastrados e imprimi-los na ordem de inserção.
             * Também vamos consultar todos os pedidos feitos e imprimi-los.
             * 
             * ResultSet:
             *   - É como um "cursor" que percorre os resultados da consulta.
             ****************************************************************************************/
            String selectSQL = "SELECT id, nome, email FROM usuarios ORDER BY id ASC";

            PreparedStatement selectStmt = conn.prepareStatement(selectSQL);
            ResultSet rs = selectStmt.executeQuery();

            System.out.println("\n=== LISTA DE USUÁRIOS ===");
            while (rs.next()) {
                // rs.getInt("id") → pega o valor da coluna "id"
                // rs.getString("nome") → pega o valor da coluna "nome"
                System.out.println(
                    "ID: " + rs.getInt("id") +
                    " | Nome: " + rs.getString("nome") +
                    " | Email: " + rs.getString("email")
                );
            }
            
            selectSQL = "SELECT * FROM pedidos ORDER BY id ASC";

            selectStmt = conn.prepareStatement(selectSQL);
            rs = selectStmt.executeQuery();

            System.out.println("\n=== LISTA DE PEDIDOS ===");
            while (rs.next()) {
                // rs.getInt("id") → pega o valor da coluna "id"
                // rs.getString("nome") → pega o valor da coluna "nome"
                System.out.println(
                    "ID: " + rs.getInt("id") +
                    " | Descrição: " + rs.getString("descricao") +
                    " | Valor: " + rs.getString("valor") + 
                    " | Usuario ID: " + rs.getString("usuario_id") + 
                    " | Data do pedido: " + rs.getString("data")
                );
            }

            rs.close();
            selectStmt.close();




            /****************************************************************************************
             * 5) ATUALIZANDO DADOS (UPDATE)
             * --------------------------------------------------------------------------------------
             * Vamos atualizar o nome do usuário.
             ****************************************************************************************/
            String updateSQL = "UPDATE usuarios SET nome = ? WHERE email = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSQL);

            updateStmt.setString(1, "Usuario invalido");    // novo nome
            updateStmt.setString(2, "emailerrado@email.com");     // critério

            int linhasAtualizadas = updateStmt.executeUpdate();
            System.out.println("\nLinhas atualizadas: " + linhasAtualizadas);

            updateStmt.close();




            /****************************************************************************************
             * 6) DELETANDO DADOS (DELETE)
             * --------------------------------------------------------------------------------------
             * Vamos remover o usuário com determinado email.
             ****************************************************************************************/
            String deleteSQL = "DELETE FROM usuarios WHERE email = ?";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteSQL);

            deleteStmt.setString(1, "emailerrado@email.com"); // esse não vai apagar nada

            int linhasApagadas = deleteStmt.executeUpdate();
            System.out.println("\nLinhas deletadas: " + linhasApagadas);

            deleteStmt.close();



            /****************************************************************************************
             * 7) EXEMPLO DE JOIN E SUM USANDO JDBC
             * --------------------------------------------------------------------------------------
             * Estamos mostrando todos os pedidos realizados e quem é o usuário responsável por ele e
             * também mostrando quanto cada usuário gastou em pedidos.
             ****************************************************************************************/
            String joinSQL =
                "SELECT u.nome, p.valor, p.data " +
                "FROM usuarios u " +
                "JOIN pedidos p ON p.usuario_id = u.id";

            PreparedStatement joinStmt = conn.prepareStatement(joinSQL);
            ResultSet rJoin = joinStmt.executeQuery();

            System.out.println("\n=== LISTA DE PEDIDOS + USUÁRIOS ===");
            while (rJoin.next()) {
                System.out.println(
                    "Nome: " + rJoin.getString("nome") +
                    " | Total: " + rJoin.getDouble("valor") +
                    " | Data: " + rJoin.getTimestamp("data")
                );
            }

            rJoin.close();
            joinStmt.close();
            
            String sumSQL =
            	    "SELECT u.nome, " +
            	    "       SUM(p.valor) AS total_gasto, " +
            	    "       COUNT(p.id) AS quantidade_pedidos, " + 
            	    "       MAX(p.data) AS ultima_compra " +
            	    "FROM usuarios u " +
            	    "LEFT JOIN pedidos p ON p.usuario_id = u.id " +
            	    "GROUP BY u.nome";

            	PreparedStatement sumStmt = conn.prepareStatement(sumSQL);
            	ResultSet rSum = sumStmt.executeQuery();

            	System.out.println("\n=== TOTAL GASTO POR USUÁRIO ===");

            	while (rSum.next()) {
            	    System.out.println(
            	        "Usuário: " + rSum.getString("nome") +
            	        " | Total gasto: R$ " + rSum.getDouble("total_gasto") +
            	        " | Pedidos: " + rSum.getInt("quantidade_pedidos") +
            	        " | Última compra: " + rSum.getTimestamp("ultima_compra")
            	    );
            	}

            	rSum.close();
            	sumStmt.close();



            /****************************************************************************************
             * 8) FECHANDO A CONEXÃO (IMPORTANTE)
             ****************************************************************************************/
            conn.close();
            System.out.println("\nConexão encerrada.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
