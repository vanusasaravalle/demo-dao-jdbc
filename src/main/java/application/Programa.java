
import model.dao.DaoFabrica;
import model.dao.VendedorDao;
import model.entites.Departamento;
import model.entites.Vendedor;

public class Programa {

    public static void main(String[] args) {

       VendedorDao vendedorDao = DaoFabrica.createVendendorDao();

        System.out.println("=====Teste 1: vendendor findById =====");
        Vendedor vendedor = vendedorDao.findByIdId(3);

        System.out.println(vendedor);
    }
}
