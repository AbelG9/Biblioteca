package org.bootcamp.dao;

import org.bootcamp.model.Articulo;

import java.util.List;

public interface ArticuloDao {
    public void addArticulo(Articulo articulo);
    public List<Articulo> showAllArticulos();
    public void updateArticulo(Articulo articulo);
    public void deleteArticulo(int id);
    public void loanArt(int articuloID, int userID);
    public void returnArt(int articuloID, int userID);
    public Articulo returnArtById(int articuloID);
}
