package application;

import model.dao.DaoFabrica;
import model.dao.VendedorDao;

import model.entites.Departamento;
import model.entites.Vendedor;

import java.util.List;

public class Programa {

    public static void main(String[] args) {

       VendedorDao vendedorDao = DaoFabrica.createVendendorDao();

        System.out.println("=====Teste 1: vendendor findById =====");
        Vendedor vendedor = vendedorDao.findByIdId(3);

        System.out.println(vendedor);

        System.out.println("=====Teste 2: vendendor findByDeapartamento=====");
        Departamento departamento = new Departamento(2, null);
        List<Vendedor> list = vendedorDao.findByDepartamento(departamento);

        for (Vendedor obj : list){
            System.out.println(obj);
        }
    }
}
