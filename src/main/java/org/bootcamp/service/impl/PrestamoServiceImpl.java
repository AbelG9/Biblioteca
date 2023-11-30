package org.bootcamp.service.impl;

import org.bootcamp.dao.PrestamoDao;
import org.bootcamp.model.Prestamo;
import org.bootcamp.model.User;
import org.bootcamp.service.PrestamoService;

import java.util.List;

public class PrestamoServiceImpl implements PrestamoService {
    PrestamoDao prestamoDao;

    public PrestamoServiceImpl(PrestamoDao prestamoDao) {
        this.prestamoDao = prestamoDao;
    }

    @Override
    public void addPrestamo(int articuloID, int userID) {
        prestamoDao.addPrestamo(articuloID, userID);
    }

    @Override
    public void updatePrestamo(Prestamo prestamo) {

    }

    @Override
    public List<Prestamo> getPrestamosByUserId(int userID) {
        return prestamoDao.getPrestamosByUserId(userID);
    }
}
