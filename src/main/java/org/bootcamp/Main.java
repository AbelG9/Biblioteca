package org.bootcamp;

import org.bootcamp.dao.impl.ArticuloDaoImpl;
import org.bootcamp.dao.impl.PrestamoDaoImpl;
import org.bootcamp.dao.impl.UserDaoImpl;
import org.bootcamp.model.Articulo;
import org.bootcamp.model.Prestamo;
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
        int optionSystem = 0;
        //mostrando opciones del menu
        do {
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
            System.out.println("Ingrese 9 para modificar un articulo");
            System.out.println("Ingrese 10 para modificar un usuario");
            System.out.println("Ingrese 11 para eliminar un articulo");
            System.out.println("Ingrese 12 para eliminar un usuario");
            System.out.println("Ingrese 13 para ver el historial de prestamos por usuario");
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
                        break;
                    }

                    System.out.println("Ingrese el id del articulo");
                    idArticulo = sc.nextInt();

                    Articulo articuloFind = articuloService.returnArtById(idArticulo);
                    if (articuloFind.getArticuloID() == 0){
                        System.out.println("Ingrese un articulo valido");
                        break;
                    }
                    if (articuloFind.isLoaned()){
                        System.out.println("El articulo ya esta prestado a otro usuario");
                        break;
                    }
                    prestamoService.loanItem(idArticulo, idUsuario);
                    break;
                case 8:
                    System.out.println("Ingrese el id del usuario");
                    idUsuario = sc.nextInt();

                    User userFound = userService.findUserById(idUsuario);
                    if (userFound.getUserID() == 0){
                        System.out.println("Ingrese un usuario valido");
                        break;
                    }
                    if(userFound.getArticuloList().isEmpty()){
                        System.out.println("El usuario no tiene articulos prestados");
                        break;
                    }

                    System.out.println("Ingrese el id del articulo");
                    idArticulo = sc.nextInt();

                    Articulo articuloFound = articuloService.returnArtById(idArticulo);
                    if (articuloFound.getArticuloID() == 0){
                        System.out.println("Ingrese un articulo valido");
                        break;
                    }
                    boolean loaned = articuloFound.isLoaned();
                    if (!loaned){
                        System.out.println("El articulo no esta prestado");
                        break;
                    }
                    prestamoService.returnItem(idArticulo, idUsuario);
                    break;
                case 9:
                    System.out.println("Ingrese el id del articulo");
                    idArticulo = sc.nextInt();

                    Articulo articuloEdit = articuloService.returnArtById(idArticulo);
                    if (articuloEdit.getArticuloID() == 0){
                        System.out.println("Ingrese un articulo valido");
                        break;
                    }
                    articuloEdit.showDetails();
                    System.out.println("--------------------------------------------");
                    System.out.println("Ingrese los nuevos datos del articulo");
                    System.out.println("--------------------------------------------");
                    System.out.println("Ingrese el nombre del articulo");
                    String newNombre = sc.next();
                    System.out.println("Ingrese el nombre del autor");
                    String newAutor = sc.next();
                    System.out.println("Ingrese la editorial");
                    String newEditorial = sc.next();
                    System.out.println("Ingrese el isbn del articulo");
                    String newIsbn = sc.next();

                    articuloEdit.setNombreArticulo(newNombre);
                    articuloEdit.setAutor(newAutor);
                    articuloEdit.setEditorial(newEditorial);
                    articuloEdit.setIsbn(newIsbn);
                    articuloEdit.setLoaned(false);

                    articuloService.updateArticulo(articuloEdit);
                    break;
                case 10:
                    System.out.println("Ingrese el id del usuario");
                    idUsuario = sc.nextInt();

                    User userEdit = userService.findUserById(idUsuario);
                    if (userEdit.getUserID() == 0){
                        System.out.println("Ingrese un usuario valido");
                        break;
                    }
                    userEdit.showUserDetails();
                    System.out.println("--------------------------------------------");
                    System.out.println("Ingrese los nuevos datos del usuario");
                    System.out.println("--------------------------------------------");
                    System.out.println("Ingrese el nombre del usuario");
                    String newUserNombre = sc.next();
                    System.out.println("Ingrese los apellidos del usuario");
                    String newApellidos = sc.next();
                    System.out.println("Ingrese el email del usuario");
                    String newEmail = sc.next();

                    userEdit.setNombre(newUserNombre);
                    userEdit.setApellido(newApellidos);
                    userEdit.setEmail(newEmail);

                    userService.editUser(userEdit);
                    break;
                case 11:
                    System.out.println("Ingrese el id del articulo");
                    idArticulo = sc.nextInt();
                    Articulo articuloDelete = articuloService.returnArtById(idArticulo);
                    if (articuloDelete.getArticuloID() == 0){
                        System.out.println("Ingrese un articulo valido");
                        break;
                    }
                    List<Prestamo> prestamoRevision = prestamoService.getLoansByItemId(articuloDelete.getArticuloID());
                    if (!prestamoRevision.isEmpty()){
                        System.out.println("El articulo esta prestado, primero efectue la devolucion por favor");
                        break;
                    }
                    articuloService.deleteArticulo(articuloDelete.getArticuloID());
                    break;
                case 12:
                    System.out.println("Ingrese el id del usuario");
                    idUsuario = sc.nextInt();

                    User userDelete = userService.findUserById(idUsuario);
                    if (userDelete.getUserID() == 0){
                        System.out.println("Ingrese un usuario valido");
                        break;
                    }
                    List<Prestamo> prestamoRevisionUser = prestamoService.getLoansByUserId(userDelete.getUserID());
                    if (!prestamoRevisionUser.isEmpty()){
                        System.out.println("El usuario tiene articulos prestados, primero debe efectuar la devolucion");
                        break;
                    }
                    userService.deleteUser(userDelete.getUserID());
                    break;
                case 13:
                    System.out.println("Ingrese el id del usuario");
                    idUsuario = sc.nextInt();

                    User userHistory = userService.findUserById(idUsuario);
                    if (userHistory.getUserID() == 0){
                        System.out.println("Ingrese un usuario valido");
                        break;
                    }
                    List<Prestamo> allPrestamos = prestamoService.getAllLoansByUserId(idUsuario);
                    for (Prestamo prestamo : allPrestamos) {
                        prestamo.showPrestamoDetails();
                    }
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta");
                    break;
            }
            System.out.println("--------------------------------------------");
            System.out.println("Desea realizar otra operacion? 1: Si, 0: No");
            optionSystem = sc.nextInt();
        } while (optionSystem == 1);
        System.out.println("Gracias por utilizar este sistema, vuelva pronto!");
    }
}