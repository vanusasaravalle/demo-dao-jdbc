package model.dao;

import model.implentacao.VendendorDaoJDBC;

public class DaoFabrica {

    public static VendedorDao createVendendorDao(){
        return new VendendorDaoJDBC();
    }
}
