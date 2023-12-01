package org.bootcamp.dao;

import org.bootcamp.model.Prestamo;

import java.util.List;

public interface PrestamoDao {
    public void loanItem(int articuloID, int userID);
    public void returnItem(int articuloID, int userID);
    public List<Prestamo> getLoansByUserId(int userID);
    public List<Prestamo> getLoansByItemId(int articuloID);
}
