package org.bootcamp.dao;

import org.bootcamp.model.Prestamo;

import java.time.Period;
import java.util.List;

public interface PrestamoDao {
    public void addPrestamo(Prestamo prestamo);
    public void updatePrestamo(Prestamo prestamo);
    public List<Prestamo> getPrestamosByUserId(int userID);
}
