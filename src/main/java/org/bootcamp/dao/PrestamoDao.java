package org.bootcamp.dao;

import org.bootcamp.model.Prestamo;

import java.time.Period;
import java.util.List;

public interface PrestamoDao {
    public void addPrestamo(int articuloID, int userID);
    public void updatePrestamo(Prestamo prestamo);
    public List<Prestamo> getPrestamosByUserId(int userID);
}
