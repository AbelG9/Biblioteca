package org.bootcamp.service;

import org.bootcamp.model.Prestamo;

import java.util.List;

public interface PrestamoService {
    public void loanItem(int articuloID, int userID);
    public void returnItem(int articuloID, int userID);
    public List<Prestamo> getLoansByUserId(int userID);
    public List<Prestamo> getLoansByItemId(int articuloID);
    public List<Prestamo> getAllLoansByUserId(int userID);
}
