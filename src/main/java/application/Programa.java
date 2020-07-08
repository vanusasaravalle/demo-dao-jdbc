package application;

import model.dao.DaoFabrica;
import model.dao.VendedorDao;
import model.entites.Departamento;
import model.entites.Vendedor;

import java.util.Date;

public class Programa {

    public static void main(String[] args) {

        Departamento obj = new Departamento(1,"Books");

        Vendedor vendedor = new Vendedor(21, "Bob","bob@gmail.com", new Date(),3000.0, obj);

        VendedorDao vendedorDao = DaoFabrica.createVendendorDao();

        System.out.println(vendedor);
    }
}
