package org.bootcamp;

import org.bootcamp.dao.impl.ArticuloDaoImpl;
import org.bootcamp.dao.impl.UserDaoImpl;
import org.bootcamp.model.Articulo;
import org.bootcamp.model.User;
import org.bootcamp.service.ArticuloService;
import org.bootcamp.service.UserService;
import org.bootcamp.service.impl.ArticuloServiceImpl;
import org.bootcamp.service.impl.UserServiceImpl;
import org.bootcamp.utils.DbConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();

        ArticuloService articuloService = new ArticuloServiceImpl(new ArticuloDaoImpl(connection));
        UserService userService = new UserServiceImpl(new UserDaoImpl(connection));

        Scanner sc = new Scanner(System.in);

        //mostrando opciones del menu
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("Bienvenido al Sistema de Biblioteca ");
            System.out.println("--------------------------------------------");
            System.out.println("Ingrese 1 para ver los articulos disponibles");
            System.out.println("Ingrese 2 para ver los usuarios disponibles");
            System.out.println("Ingrese 3 para ver la informacion de un articulo");
            System.out.println("Ingrese 4 para ver la informacion de un usuario");
            System.out.println("--------------------------------------------");

            int option = sc.nextInt();
            switch (option) {
                case 1:
                    List<Articulo> articuloList = articuloService.showAllArticulos();
                    for(Articulo articulo: articuloList){
                        articulo.showDetails();
                    }
                    break;
                case 2:
                    List<User> users = userService.showAllUsers();
                    for (User user: users){
                        user.showUserDetails();
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el id del articulo");
                    int idArticulo = sc.nextInt();
                    Articulo articulo = articuloService.returnArtById(idArticulo);
                    if (articulo != null) {
                        articulo.showDetails();
                    } else {
                        System.out.println("No existe el articulo solicitado");
                    }
                    break;
                case 4:
                    System.out.println("Ingrese el id del usuario");
                    int idUser = sc.nextInt();
                    User user = userService.returnUserById(idUser);
                    if (user != null) {
                        user.showUserDetails();
                    } else {
                        System.out.println("No existe el usuario solicitado");
                    }
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta");
                    break;
            }
        }
    }
}