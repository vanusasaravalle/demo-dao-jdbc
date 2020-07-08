package application;

import model.Departamento;
import model.Vendedor;

import java.util.Date;

public class Programa {

    public static void main(String[] args) {

        Departamento obj = new Departamento(1,"Books");

        Vendedor vendedor = new Vendedor(21, "Bob","bob@gmail.com", new Date(),3000.0, obj);

        System.out.println(vendedor);
    }
}
