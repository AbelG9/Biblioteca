package org.bootcamp.service.impl;

import org.bootcamp.dao.ArticuloDao;
import org.bootcamp.model.Articulo;
import org.bootcamp.service.ArticuloService;

import java.util.List;

public class ArticuloServiceImpl implements ArticuloService {
    ArticuloDao articuloDao;

    public ArticuloServiceImpl(ArticuloDao articuloDao) {
        this.articuloDao = articuloDao;
    }

    @Override
    public void addArticulo(Articulo articulo) {
        articuloDao.addArticulo(articulo);
    }

    @Override
    public List<Articulo> showAllArticulos() {
        return articuloDao.showAllArticulos();
    }

    @Override
    public void updateArticulo(Articulo articulo) {

    }

    @Override
    public void deleteArticulo(int id) {

    }

    @Override
    public void loanArt(int articuloID, int userID) {

    }

    @Override
    public void returnArt(int articuloID, int userID) {

    }

    @Override
    public Articulo returnArtById(int articuloID) {
        return articuloDao.returnArtById(articuloID);
    }
}
