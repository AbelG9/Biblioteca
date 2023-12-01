package org.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prestamo {
    private int prestamoID;
    private int usuario;
    private int articulo;
    private Date fecha_prestamo;
    private Date fecha_devolucion;
    private int estado;

    public void showPrestamoDetails(){
        System.out.println("==========================");
        System.out.println("    Datos de Prestamos");
        System.out.println("==========================");
        System.out.println("Prestamo ID: " + prestamoID);
        System.out.println("UsuarioId: " + usuario);
        System.out.println("ArticuloId: " + articulo);
        String definicionEstado = estado == 0 ? "Devuelto" : "Prestado";
        System.out.println("Fecha de prestamo: " + fecha_prestamo);
        System.out.println("Fecha de devolucion: " + fecha_devolucion);
        System.out.println("Estado: " + definicionEstado);
        System.out.println("- - - - - - - - - - - - -");
    }
}
