package org.bootcamp.dao.impl;

import org.bootcamp.dao.PrestamoDao;
import org.bootcamp.model.Articulo;
import org.bootcamp.model.Prestamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDaoImpl implements PrestamoDao {
    private Connection connection;  //conexion bd

    public PrestamoDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void loanItem(int articuloID, int userID) {
        try{
            String sql = "insert into prestamos (usuario, articulo, estado) values (?,?,?)";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, userID);
            psmt.setInt(2, articuloID);
            psmt.setInt(3, 1);
            psmt.executeUpdate();

            String sql2 = "update articulos set isLoaned = ? where articulo_id = ?";
            PreparedStatement psmt2 = connection.prepareStatement(sql2);
            psmt2.setBoolean(1,true);
            psmt2.setInt(2, articuloID);
            psmt2.executeUpdate();

            System.out.println("Articulo prestado exitosamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void returnItem(int articuloID, int userID) {
        try{
            String sql = "update prestamos set estado = ? where usuario = ? and articulo = ? and estado = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, 0);
            psmt.setInt(2, userID);
            psmt.setInt(3, userID);
            psmt.setInt(4, 1);
            psmt.executeUpdate();

            String sql2 = "update articulos set isLoaned = ? where articulo_id = ?";
            PreparedStatement psmt2 = connection.prepareStatement(sql2);
            psmt2.setBoolean(1,false);
            psmt2.setInt(2, articuloID);
            psmt2.executeUpdate();

            System.out.println("Articulo devuelto exitosamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Prestamo> getLoansByUserId(int userID) {
        try{
            String sql = "select * from prestamos where usuario = ? and estado = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, userID);
            psmt.setInt(2, 1);
            ResultSet resultSet = psmt.executeQuery();
            List<Prestamo> prestamos = new ArrayList<>();
            while(resultSet.next()){
                int id = resultSet.getInt("prestamo_id");
                int usuario = resultSet.getInt("usuario");
                int articulo = resultSet.getInt("articulo");
                Prestamo prestamo = new Prestamo();
                prestamo.setPrestamoID(id);
                prestamo.setUsuario(usuario);
                prestamo.setArticulo(articulo);
                prestamos.add(prestamo);
            }
            return prestamos;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
