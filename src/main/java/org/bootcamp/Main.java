package org.bootcamp;

import org.bootcamp.dao.impl.ArticuloDaoImpl;
import org.bootcamp.dao.impl.PrestamoDaoImpl;
import org.bootcamp.dao.impl.UserDaoImpl;
import org.bootcamp.model.Articulo;
import org.bootcamp.model.User;
import org.bootcamp.service.ArticuloService;
import org.bootcamp.service.PrestamoService;
import org.bootcamp.service.UserService;
import org.bootcamp.service.impl.ArticuloServiceImpl;
import org.bootcamp.service.impl.PrestamoServiceImpl;
import org.bootcamp.service.impl.UserServiceImpl;
import org.bootcamp.utils.DbConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();

        ArticuloService articuloService = new ArticuloServiceImpl(new ArticuloDaoImpl(connection));
        UserService userService = new UserServiceImpl(new UserDaoImpl(connection));
        PrestamoService prestamoService = new PrestamoServiceImpl(new PrestamoDaoImpl(connection));

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
            System.out.println("Ingrese 5 para agregar un nuevo articulo");
            System.out.println("Ingrese 6 para agregar un nuevo usuario");
            System.out.println("Ingrese 7 para prestar un articulo");
            System.out.println("Ingrese 8 para devolver un articulo");
            System.out.println("--------------------------------------------");

            int option = sc.nextInt();
            int idUsuario;
            int idArticulo;
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
                    idArticulo = sc.nextInt();
                    Articulo articulo = articuloService.returnArtById(idArticulo);
                    if (articulo.getArticuloID() != 0) {
                        articulo.showDetails();
                    } else {
                        System.out.println("No existe el articulo solicitado");
                    }
                    break;
                case 4:
                    System.out.println("Ingrese el id del usuario");
                    idUsuario = sc.nextInt();
                    User user = userService.findUserById(idUsuario);
                    if (user.getUserID() != 0) {
                        user.showUserDetails();
                    } else {
                        System.out.println("No existe el usuario solicitado");
                    }
                    break;
                case 5:
                    Articulo articuloSave = new Articulo();
                    System.out.println("Ingrese el nombre del articulo");
                    String nombreArticulo = sc.next();
                    System.out.println("Ingrese el nombre del autor");
                    String autor = sc.next();
                    System.out.println("Ingrese la editorial");
                    String editorial = sc.next();
                    System.out.println("Ingrese el isbn del articulo");
                    String isbn = sc.next();

                    articuloSave.setNombreArticulo(nombreArticulo);
                    articuloSave.setAutor(autor);
                    articuloSave.setEditorial(editorial);
                    articuloSave.setIsbn(isbn);
                    articuloSave.setLoaned(false);

                    articuloService.addArticulo(articuloSave);
                    break;
                case 6:
                    User userSave = new User();
                    System.out.println("Ingrese el nombre del usuario");
                    String nombre = sc.next();
                    System.out.println("Ingrese los apellidos del usuario");
                    String apellidos = sc.next();
                    System.out.println("Ingrese el email del usuario");
                    String email = sc.next();

                    userSave.setNombre(nombre);
                    userSave.setApellido(apellidos);
                    userSave.setEmail(email);

                    userService.addUser(userSave);
                    break;
                case 7:
                    System.out.println("Ingrese el id del usuario");
                    idUsuario = sc.nextInt();

                    User userFind = userService.findUserById(idUsuario);
                    if (userFind.getUserID() == 0){
                        System.out.println("Ingrese un usuario valido");
                        return;
                    }

                    System.out.println("Ingrese el id del articulo");
                    idArticulo = sc.nextInt();

                    Articulo articuloFind = articuloService.returnArtById(idArticulo);
                    if (articuloFind.getArticuloID() == 0){
                        System.out.println("Ingrese un articulo valido");
                        return;
                    }
                    if (articuloFind.isLoaned()){
                        System.out.println("El articulo ya esta prestado a otro usuario");
                        return;
                    }
                    prestamoService.loanItem(idArticulo, idUsuario);
                    break;
                case 8:
                    System.out.println("Ingrese el id del usuario");
                    idUsuario = sc.nextInt();

                    User userFound = userService.findUserById(idUsuario);
                    if (userFound.getUserID() == 0){
                        System.out.println("Ingrese un usuario valido");
                        return;
                    }
                    if(userFound.getArticuloList().isEmpty()){
                        System.out.println("El usuario no tiene articulos prestados");
                        return;
                    }

                    System.out.println("Ingrese el id del articulo");
                    idArticulo = sc.nextInt();

                    Articulo articuloFound = articuloService.returnArtById(idArticulo);
                    if (articuloFound.getArticuloID() == 0){
                        System.out.println("Ingrese un articulo valido");
                        return;
                    }
                    boolean loaned = articuloFound.isLoaned();
                    if (!loaned){
                        System.out.println("El articulo no esta prestado");
                        return;
                    }
                    prestamoService.returnItem(idArticulo, idUsuario);
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta");
                    break;
            }
        }
    }
}