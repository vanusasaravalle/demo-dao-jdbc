package model.dao;

import model.Departamento;

import java.util.List;

public interface DepartamentoDao {

    void insert(Departamento obj);
    void update(Departamento obj);
    void deleteById(Integer id);
    Departamento findByIdId(Integer id);
    List<Departamento> findAll();
}
