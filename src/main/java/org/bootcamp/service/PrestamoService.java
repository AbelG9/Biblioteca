package org.bootcamp.service;

import org.bootcamp.model.Prestamo;

import java.util.List;

public interface PrestamoService {
    public void addPrestamo(int articuloID, int userID);
    public void updatePrestamo(Prestamo prestamo);
    public List<Prestamo> getPrestamosByUserId(int userID);
}
