package br.com.loja_virtual.dao;

import br.com.loja_virtual.modelo.Categoria;
import br.com.loja_virtual.modelo.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private Connection connection;

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();

        String sql = "select id, nome from categoria";

        return getCategorias(categorias, sql);
    }

    public List<Categoria> listarComProdutos() throws SQLException {

        List<Categoria> categorias = new ArrayList<>();

        String sql = "select * from categoria c INNER JOIN produto p on c.id = p.categoria_id";

        return getCategorias(categorias, sql);
    }

    private List<Categoria> getCategorias(List<Categoria> categorias, String sql) throws SQLException {
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            Categoria ultima = null;

            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    if (ultima == null || !ultima.getNome().equals(rst.getString(2))) {
                        Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
                        ultima = categoria;
                        categorias.add(categoria);
                    }
                    Produto produto = new Produto(rst.getInt(3), rst.getString(4),rst.getString(5));
                    ultima.adicionar(produto);
                }
            }
        }
        return categorias;
    }
}