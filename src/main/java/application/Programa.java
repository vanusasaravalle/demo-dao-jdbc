package application;

import model.dao.DaoFabrica;
import model.dao.VendedorDao;

import model.entites.Departamento;
import model.entites.Vendedor;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

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

        System.out.println("/n=====Teste 3: vendendor findByAll=====");
        list = vendedorDao.findAll();

        for (Vendedor obj : list){
            System.out.println(obj);
        }

        System.out.println("/n=====Teste 4: vendendor insert====");

        Vendedor newVendedor = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 4000.00, departamento);
        vendedorDao.insert(newVendedor);
        System.out.println("Insert! New id = " + newVendedor.getId());

        System.out.println("/n=====Teste 5: vendendor update=====");
        vendedor = vendedorDao.findByIdId(1);
        vendedor.setName("Marta waine");
        vendedorDao.update(vendedor);
        System.out.println("update completado");


     System.out.println("/n=====Teste 6: vendendor delete=====");
     System.out.println("Digite o Id para remover");
     int id = sc.nextInt();
     vendedorDao.deleteById(id);
     System.out.println("Deletado com sucesso!!!");


    }
}
