package org.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Articulo {
    private int articuloID;
    private String nombreArticulo;
    private String autor;
    private String editorial;
    private String isbn;
    private boolean isLoaned;
    private int estado;

    public void showDetails(){
        System.out.println("==========================");
        System.out.println("   Articulo de Biblioteca");
        System.out.println("==========================");
        System.out.println("articuloID: " + getArticuloID());
        System.out.println("nombreArticulo: " + getNombreArticulo());
        System.out.println("autor: " + getAutor());
        System.out.println("editorial: " + getEditorial());
        System.out.println("isbn: " + getIsbn());
        System.out.println("isLoaned: " + isLoaned());
        System.out.println("- - - - - - - - - - - - -");
    };
}
