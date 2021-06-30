package br.com.loja_virtual;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();

        Statement stm = connection.createStatement();
        stm.execute("select id, nome, descricao from produto");

        ResultSet rst = stm.getResultSet();

        while (rst.next()) {
            Integer id = rst.getInt("id");
            System.out.println(id);
            String nome = rst.getString("nome");
            System.out.println(nome);
            String descricao = rst.getString("descricao");
            System.out.println(descricao);
        }
        connection.close();
    }
}
