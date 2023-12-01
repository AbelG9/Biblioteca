package org.bootcamp.dao.impl;

import org.bootcamp.dao.ArticuloDao;
import org.bootcamp.model.Articulo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticuloDaoImpl implements ArticuloDao {
    private Connection connection;  //conexion bd

    public ArticuloDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addArticulo(Articulo articulo) {
        try{
            String sql = "insert into articulos (nombreArticulo, autor, editorial, isbn, isLoaned, estado) values(?,?,?,?,?,?)";
            PreparedStatement psmt = connection.prepareStatement(sql);
            String nombre = articulo.getNombreArticulo();
            String autor = articulo.getAutor();
            String editorial = articulo.getEditorial();
            String isbn = articulo.getIsbn();
            Boolean isLoaned = articulo.isLoaned();
            int estado = 1;

            psmt.setString(1, nombre);
            psmt.setString(2, autor);
            psmt.setString(3, editorial);
            psmt.setString(4, isbn);
            psmt.setBoolean(5, isLoaned);
            psmt.setInt(6, estado);
            psmt.executeUpdate();
            System.out.println("Articulo ingresado correctamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
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
        try{
            String sql2 = "update articulos set nombreArticulo = ?, autor = ?, editorial = ?, isbn = ? where articulo_id = ?";
            PreparedStatement psmt2 = connection.prepareStatement(sql2);
            psmt2.setString(1,articulo.getNombreArticulo());
            psmt2.setString(2,articulo.getAutor());
            psmt2.setString(3,articulo.getEditorial());
            psmt2.setString(4,articulo.getIsbn());
            psmt2.setInt(5, articulo.getArticuloID());
            psmt2.executeUpdate();

            System.out.println("Articulo editado exitosamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteArticulo(int id) {
        System.out.println("Esta seguro de eliminar este articulo? (1: Si, 2: No)");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        if(option != 1) return;
        try{
            String sql2 = "update articulos set estado = ? where articulo_id = ?";
            PreparedStatement psmt2 = connection.prepareStatement(sql2);
            psmt2.setInt(1, 0);
            psmt2.setInt(2, id);
            psmt2.executeUpdate();

            System.out.println("Articulo eliminado exitosamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Articulo returnArtById(int articuloID) {
        try{
            String sql = "select * from articulos where articulo_id = ? and estado = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, articuloID);
            psmt.setInt(2, 1);
            ResultSet resultSet = psmt.executeQuery();
            Articulo articulo = new Articulo();
            while(resultSet.next()){
                int id = resultSet.getInt("articulo_id");
                String nombre = resultSet.getString("nombreArticulo");
                String autor = resultSet.getString("autor");
                String editorial = resultSet.getString("editorial");
                String isbn = resultSet.getString("isbn");
                boolean isLoaned = resultSet.getBoolean("isLoaned");

                articulo.setArticuloID(id);
                articulo.setNombreArticulo(nombre);
                articulo.setAutor(autor);
                articulo.setEditorial(editorial);
                articulo.setIsbn(isbn);
                articulo.setLoaned(isLoaned);
            }
            return articulo;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
