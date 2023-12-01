package org.bootcamp.dao.impl;

import org.bootcamp.dao.UserDao;
import org.bootcamp.model.Articulo;
import org.bootcamp.model.Prestamo;
import org.bootcamp.model.User;
import org.bootcamp.service.ArticuloService;
import org.bootcamp.service.PrestamoService;
import org.bootcamp.service.impl.ArticuloServiceImpl;
import org.bootcamp.service.impl.PrestamoServiceImpl;
import org.bootcamp.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDaoImpl implements UserDao {
    private Connection connection;  //conexion bd
    private Connection dbConnection = new DbConnection().getConnection();
    PrestamoService prestamoService= new PrestamoServiceImpl(new PrestamoDaoImpl(dbConnection));
    ArticuloService articuloService = new ArticuloServiceImpl(new ArticuloDaoImpl(dbConnection));

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addUser(User user) {
        try{
            String sql = "INSERT INTO usuarios (nombre, apellidos, email, estado) VALUES (?, ?, ?, ?)";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setString(1, user.getNombre());
            psmt.setString(2, user.getApellido());
            psmt.setString(3, user.getEmail());
            psmt.setInt(4, 1);
            psmt.executeUpdate();
            System.out.println("Usuario ingresado correctamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> showAllUsers() {
        try{
            String sql = "select * from usuarios where estado = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, 1);
            ResultSet resultSet = psmt.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()){
                int id = resultSet.getInt("usuario_id");
                String nombre = resultSet.getString("nombre");
                String apellidos = resultSet.getString("apellidos");
                String email = resultSet.getString("email");

                List<Prestamo> prestamoList = prestamoService.getLoansByUserId(id);

                List<Articulo> articuloList = new ArrayList<>();

                for(Prestamo prestamo: prestamoList){
                    Articulo articulo = articuloService.returnArtById(prestamo.getArticulo());
                    articuloList.add(articulo);
                }

                User user = new User();
                user.setUserID(id);
                user.setNombre(nombre);
                user.setApellido(apellidos);
                user.setEmail(email);
                user.setArticuloList(articuloList);
                users.add(user);
            }
            return users;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void editUser(User user) {
        try{
            String sql2 = "update usuarios set nombre = ?, apellidos = ?, email = ? where usuario_id = ?";
            PreparedStatement psmt2 = connection.prepareStatement(sql2);
            psmt2.setString(1,user.getNombre());
            psmt2.setString(2,user.getApellido());
            psmt2.setString(3,user.getEmail());
            psmt2.setInt(4, user.getUserID());
            psmt2.executeUpdate();

            System.out.println("Usuario editado exitosamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteUser(int id) {
        System.out.println("Esta seguro de eliminar este usuario? (1: Si, 2: No)");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        if(option != 1) return;
        try{
            String sql2 = "update usuarios set estado = ? where usuario_id = ?";
            PreparedStatement psmt = connection.prepareStatement(sql2);
            psmt.setInt(1, 0);
            psmt.setInt(2, id);
            psmt.executeUpdate();

            System.out.println("Usuario eliminado exitosamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User findUserById(int userID) {
        try{
            String sql = "select * from usuarios where usuario_id = ? and estado = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, userID);
            psmt.setInt(2, 1);
            ResultSet resultSet = psmt.executeQuery();
            User user = new User();
            while (resultSet.next()){
                int id = resultSet.getInt("usuario_id");
                String nombre = resultSet.getString("nombre");
                String apellidos = resultSet.getString("apellidos");
                String email = resultSet.getString("email");

                List<Prestamo> prestamoList = prestamoService.getLoansByUserId(id);

                List<Articulo> articuloList = new ArrayList<>();

                for(Prestamo prestamo: prestamoList){
                    Articulo articulo = articuloService.returnArtById(prestamo.getArticulo());
                    articuloList.add(articulo);
                }

                user.setUserID(id);
                user.setNombre(nombre);
                user.setApellido(apellidos);
                user.setEmail(email);
                user.setArticuloList(articuloList);
            }
            return user;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
