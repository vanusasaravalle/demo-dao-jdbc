package model.implentacao;

import esception.DbException;
import model.dao.VendedorDao;
import model.entites.Departamento;
import model.entites.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendendorDaoJDBC implements VendedorDao {

    private Connection conn;

    public VendendorDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Vendedor obj) {

    }

    @Override
    public void update(Vendedor obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Vendedor findByIdId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT vendedor. *,departamento.Name as DepName"
                    + "FROM vendendor INNER JOIN departamento"
                    +"ON vendedor.ID = ?");

            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()){

                Departamento dep = instantiateDepartamento(rs);
                Vendedor obj = instantiateVendedor(rs, dep);

                return obj;
            }
            return null;

        } catch (SQLException e) {
            throw  new DbException(e.getMessage());
        }
    }

    private Vendedor instantiateVendedor(ResultSet rs, Departamento dep) throws SQLException {

        Vendedor obj = new Vendedor();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Nome"));
        obj.setEmail(rs.getString("Email"));
        obj.setNascimento(rs.getDate("Nascimento"));
        obj.setSalario(rs.getDouble("Salario"));
        obj.setDepartamento(dep);

        return obj;
    }

    private Departamento instantiateDepartamento(ResultSet rs) throws SQLException {
        Departamento dep = new Departamento(2, null);
        dep.setId(rs.getInt("DapartamentoId"));
        dep.setName("DapName");
        return dep;
    }

    @Override
    public List<Vendedor> findAll() {
        return null;
    }

    @Override
    public List<Vendedor> findByDepartamento(Departamento departamento) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT vendedor. *,departamento.Name as DepName"
                            + "FROM vendendor INNER JOIN departamento"
                            +"ON vendedor.DepartamentoId = departamento.Id"
                            + "WHERE DepartamentoId = ?"
                            + "ORDER BY Name");

            st.setInt(1, departamento.getId());
            rs = st.executeQuery();

            List<Vendedor> list = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while (rs.next()){

                Departamento dep = map.get(rs.getInt("DepartamentoId"));

                if (dep == null){
                    dep = instantiateDepartamento(rs);
                    map.put(rs.getInt("Departamento"), dep);
                }

                Vendedor obj = instantiateVendedor(rs, dep);
                list.add(obj);

                return list;
            }
            return null;

        } catch (SQLException e) {
            throw  new DbException(e.getMessage());
        }
    }
}
