package br.com.loja_virtual.dao;

import br.com.loja_virtual.modelo.Categoria;
import br.com.loja_virtual.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Produto produto) throws SQLException {
        String sql = "insert into produto (nome, descricao) values (?,?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());

            pstm.execute();

            try (ResultSet rst = pstm.getGeneratedKeys()) {
                while (rst.next()) {
                    produto.setId(rst.getInt(1));
                }
            }
        }
    }

    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<>();

        String sql = "select id, nome, descricao from produto";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.execute();

            try (ResultSet rst = psmt.getResultSet()) {
                while (rst.next()) {
                    Produto produto =
                            new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));

                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }

    public List<Produto> buscar(Categoria ct) throws SQLException {
        List<Produto> produtos = new ArrayList<>();

        String sql = "select id, nome, descricao from produto where categoria_id = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1,ct.getId());
            psmt.execute();

            try (ResultSet rst = psmt.getResultSet()) {
                while (rst.next()) {
                    Produto produto =
                            new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));

                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }
}