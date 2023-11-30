package org.bootcamp.service.impl;

import org.bootcamp.dao.PrestamoDao;
import org.bootcamp.model.Prestamo;
import org.bootcamp.service.PrestamoService;

import java.util.List;

public class PrestamoServiceImpl implements PrestamoService {
    PrestamoDao prestamoDao;

    public PrestamoServiceImpl(PrestamoDao prestamoDao) {
        this.prestamoDao = prestamoDao;
    }

    @Override
    public void loanItem(int articuloID, int userID) {
        prestamoDao.loanItem(articuloID, userID);
    }

    @Override
    public void returnItem(int articuloID, int userID) {
        prestamoDao.returnItem(articuloID, userID);
    }

    @Override
    public List<Prestamo> getLoansByUserId(int userID) {
        return prestamoDao.getLoansByUserId(userID);
    }
}
