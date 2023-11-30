package org.bootcamp.service;

import org.bootcamp.model.Prestamo;

import java.util.List;

public interface PrestamoService {
    public void addPrestamo(Prestamo prestamo);
    public void updatePrestamo(Prestamo prestamo);
    public List<Prestamo> getPrestamosByUserId(int userID);
}
