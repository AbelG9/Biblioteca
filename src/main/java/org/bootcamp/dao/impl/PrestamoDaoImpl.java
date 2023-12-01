package org.bootcamp.dao.impl;

import org.bootcamp.dao.PrestamoDao;
import org.bootcamp.model.Articulo;
import org.bootcamp.model.Prestamo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
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
            String sql = "insert into prestamos (usuario, articulo, fecha_prestamo, estado) values (?,?,?,?)";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, userID);
            psmt.setInt(2, articuloID);
            psmt.setDate(3, Date.valueOf(LocalDate.now()));
            psmt.setInt(4, 1);
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
            String sql = "update prestamos set estado = ?, fecha_devolucion = ? where usuario = ? and articulo = ? and estado = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, 0);
            psmt.setDate(2, Date.valueOf(LocalDate.now()));
            psmt.setInt(3, userID);
            psmt.setInt(4, articuloID);
            psmt.setInt(5, 1);
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
                Date fechaPrestamo = resultSet.getDate("fecha_prestamo");
                Date fechaDevolucion = resultSet.getDate("fecha_devolucion");
                Prestamo prestamo = new Prestamo();
                prestamo.setPrestamoID(id);
                prestamo.setUsuario(usuario);
                prestamo.setArticulo(articulo);
                prestamo.setFecha_prestamo(fechaPrestamo);
                prestamo.setFecha_devolucion(fechaDevolucion);
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

    @Override
    public List<Prestamo> getLoansByItemId(int articuloID) {
        try{
            String sql = "select * from prestamos where articulo = ? and estado = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, articuloID);
            psmt.setInt(2, 1);
            ResultSet resultSet = psmt.executeQuery();
            List<Prestamo> prestamos = new ArrayList<>();
            while(resultSet.next()){
                int id = resultSet.getInt("prestamo_id");
                int usuario = resultSet.getInt("usuario");
                int articulo = resultSet.getInt("articulo");
                Date fechaPrestamo = resultSet.getDate("fecha_prestamo");
                Date fechaDevolucion = resultSet.getDate("fecha_devolucion");
                Prestamo prestamo = new Prestamo();
                prestamo.setPrestamoID(id);
                prestamo.setUsuario(usuario);
                prestamo.setArticulo(articulo);
                prestamo.setFecha_prestamo(fechaPrestamo);
                prestamo.setFecha_devolucion(fechaDevolucion);
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

    @Override
    public List<Prestamo> getAllLoansByUserId(int userID) {
        try{
            String sql = "select * from prestamos where usuario = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, userID);
            ResultSet resultSet = psmt.executeQuery();
            List<Prestamo> prestamos = new ArrayList<>();
            while(resultSet.next()){
                int id = resultSet.getInt("prestamo_id");
                int usuario = resultSet.getInt("usuario");
                int articulo = resultSet.getInt("articulo");
                Date fechaPrestamo = resultSet.getDate("fecha_prestamo");
                Date fechaDevolucion = resultSet.getDate("fecha_devolucion");
                int estado = resultSet.getInt("estado");
                Prestamo prestamo = new Prestamo();
                prestamo.setPrestamoID(id);
                prestamo.setUsuario(usuario);
                prestamo.setArticulo(articulo);
                prestamo.setFecha_prestamo(fechaPrestamo);
                prestamo.setFecha_devolucion(fechaDevolucion);
                prestamo.setEstado(estado);
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
