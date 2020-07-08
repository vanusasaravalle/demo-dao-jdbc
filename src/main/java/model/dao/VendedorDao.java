package model.dao;

import model.Departamento;
import model.Vendedor;

import java.util.List;

public interface VendedorDao {

    void insert(Vendedor obj);
    void update(Vendedor obj);
    void deleteById(Integer id);
    Vendedor findByIdId(Integer id);
    List<Vendedor> findAll();
}
