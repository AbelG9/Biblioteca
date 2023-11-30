package org.bootcamp;

import org.bootcamp.dao.impl.ArticuloDaoImpl;
import org.bootcamp.model.Articulo;
import org.bootcamp.service.ArticuloService;
import org.bootcamp.service.impl.ArticuloServiceImpl;
import org.bootcamp.utils.DbConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();

        ArticuloService articuloService = new ArticuloServiceImpl(new ArticuloDaoImpl(connection));

        Scanner sc = new Scanner(System.in);

        //mostrando opciones del menu
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("Bienvenido al Sistema de Biblioteca ");
            System.out.println("--------------------------------------------");
            System.out.println("Ingrese 1 para ver los articulos disponibles");

            int option = sc.nextInt();
            switch (option) {
                case 1:
                    List<Articulo> articuloList = articuloService.showAllArticulos();
                    for(Articulo articulo: articuloList){
                        articulo.showDetails();
                    }
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta");
                    break;
            }
        }
    }
}