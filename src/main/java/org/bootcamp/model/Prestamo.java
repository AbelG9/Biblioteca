package org.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prestamo {
    private int prestamoID;
    private int usuario;
    private int articulo;
    private int estado;
}
