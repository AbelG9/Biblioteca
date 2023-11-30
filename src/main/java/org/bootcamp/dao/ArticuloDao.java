package org.bootcamp.dao;

import org.bootcamp.model.Articulo;

import java.util.List;

public interface ArticuloDao {
    public void addArticulo(Articulo articulo);
    public List<Articulo> showAllArticulos();
    public void updateArticulo(Articulo articulo);
    public void deleteArticulo(int id);
    public Articulo returnArtById(int articuloID);
}
