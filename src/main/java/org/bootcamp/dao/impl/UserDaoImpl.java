package org.bootcamp.dao.impl;

import org.bootcamp.dao.PrestamoDao;
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

public class UserDaoImpl implements UserDao {
    private Connection connection;  //conexion bd
    private Connection dbConnection = new DbConnection().getConnection();
    PrestamoService prestamoService= new PrestamoServiceImpl(new PrestamoDaoImpl(dbConnection));
    ArticuloService articuloService = new ArticuloServiceImpl(new ArticuloDaoImpl(dbConnection));

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addUser(User almacenUser) {

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

                List<Prestamo> prestamoList = prestamoService.getPrestamosByUserId(id);

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
    public void editUser(int id) {

    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public User returnUserById(int userID) {
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

                List<Prestamo> prestamoList = prestamoService.getPrestamosByUserId(id);

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
