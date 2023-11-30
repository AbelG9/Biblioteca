package org.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userID;
    private String nombre;
    private String apellido;
    private String email;
    private int estado;
    private List<Articulo> articuloList;

    public void showUserDetails() {
        System.out.println("==========================");
        System.out.println("     Datos de Usuario");
        System.out.println("==========================");
        System.out.println("UserID:" + userID);
        System.out.println("Nombre:" + nombre);
        System.out.println("Apellidos:" + apellido);
        System.out.println("Email:" + email);
        System.out.println("- - - - - - - - - - - - -");
        if(!this.articuloList.isEmpty()){
            System.out.println("Articulos rentados :");
            int contador = 1;
            for (Articulo articulo: this.articuloList){
                System.out.println("----------");
                System.out.println("Articulo " + contador + " :");
                System.out.println("----------");
                articulo.showDetails();
                contador++;
            }
        } else {
            System.out.println("No tiene articulos prestados");
        }
    }
}
