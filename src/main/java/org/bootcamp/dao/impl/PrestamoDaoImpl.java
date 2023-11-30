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
    public void addPrestamo(Prestamo prestamo) {

    }

    @Override
    public void updatePrestamo(Prestamo prestamo) {

    }

    @Override
    public List<Prestamo> getPrestamosByUserId(int userID) {
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
