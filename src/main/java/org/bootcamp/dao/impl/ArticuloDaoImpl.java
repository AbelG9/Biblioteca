package org.bootcamp.dao.impl;

import org.bootcamp.dao.ArticuloDao;
import org.bootcamp.model.Articulo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArticuloDaoImpl implements ArticuloDao {
    private Connection connection;  //conexion bd

    public ArticuloDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addArticulo(Articulo articulo) {

    }

    @Override
    public List<Articulo> showAllArticulos() {
        try{
            String sql = "select * from articulos where estado = 1";
            PreparedStatement psmt = connection.prepareStatement(sql);
            ResultSet resultSet = psmt.executeQuery();
            List<Articulo> articulos = new ArrayList<>();
            while(resultSet.next()){
                int id = resultSet.getInt("articulo_id");
                String nombre = resultSet.getString("nombreArticulo");
                String autor = resultSet.getString("autor");
                String editorial = resultSet.getString("editorial");
                String isbn = resultSet.getString("isbn");
                boolean isLoaned = resultSet.getBoolean("isLoaned");

                Articulo articulo = new Articulo();
                articulo.setArticuloID(id);
                articulo.setNombreArticulo(nombre);
                articulo.setAutor(autor);
                articulo.setEditorial(editorial);
                articulo.setIsbn(isbn);
                articulo.setLoaned(isLoaned);
                articulos.add(articulo);
            }
            return articulos;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateArticulo(Articulo articulo) {

    }

    @Override
    public void deleteArticulo(int id) {

    }

    @Override
    public void loanArt(int articuloID, int userID) {

    }

    @Override
    public void returnArt(int articuloID, int userID) {

    }
}
