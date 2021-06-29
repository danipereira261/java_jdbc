package br.com.loja_virtual;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexao {

        public static void main(String[] args) throws SQLException {

            Connection con = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/loja_virtual?useTimezone=true&serverTimezone=UTC","root","root")
                    ;

            System.out.println("Fechando conexão!");

            con.close();

        }
}
