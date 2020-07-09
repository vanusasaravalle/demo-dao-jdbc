package model.implentacao;

import esception.DbException;
import model.dao.VendedorDao;
import model.entites.Departamento;
import model.entites.Vendedor;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class VendendorDaoJDBC implements VendedorDao {

    private Connection conn;

    public VendendorDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Vendedor obj) {

        PreparedStatement st = null;
        try {

            st = conn.prepareStatement(
                    "Insert into vendedor "
                    + "(Name, Email, Nascimento, salario, DepartamentoId) "
                    + "VALUES "
                    + "(?,?,?,?,?,?) ",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, (java.sql.Date) new Date(obj.getNascimento().getTime()));
            st.setDouble(4, obj.getSalario());
            st.setInt(5, obj.getDepartamento().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs =  st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            }else {
                throw new DbException("Erro");
            }


        } catch (SQLException e) {
            throw new DbException(e.getMessage() );
        }

    }

    @Override
    public void update(Vendedor obj) {

        PreparedStatement st = null;
        try {

            st = conn.prepareStatement(
                    "UPDATE vendedor "
                            + "SET Name = ?, Email = ?, Nascimento = ?, salario = ?, DepartamentoId = ?) "
                            + "WHERE Id = ? ");

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, (java.sql.Date) new Date(obj.getNascimento().getTime()));
            st.setDouble(4, obj.getSalario());
            st.setInt(5, obj.getDepartamento().getId());
            st.setInt(6, obj.getId());

            st.executeUpdate();


        } catch (SQLException e) {
            throw new DbException(e.getMessage() );
        }

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
                    "SELECT vendedor. *,departamento.Name as DepName "
                    + "FROM vendendor INNER JOIN departamento "
                    +"ON vendedor.ID = ? ");

            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()){

                Departamento dep = instantiateDepartamento(rs);
                Vendedor obj = instantiateVendedor(rs, dep, departamento);

                return obj;
            }
            return null;

        } catch (SQLException e) {
            throw  new DbException(e.getMessage());
        }
    }

    private Vendedor instantiateVendedor(ResultSet rs, Departamento dep, Departamento departamento) throws SQLException {

        Vendedor obj = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 4000.00, departamento);
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
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT vendedor. *,departamento.Name as DepName "
                            + "FROM vendendor INNER JOIN departamento "
                            +"ON vendedor.DepartamentoId = departamento.Id "
                            + "ORDER BY Name ");

            rs = st.executeQuery();

            List<Vendedor> list = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while (rs.next()){

                Departamento dep = map.get(rs.getInt("DepartamentoId"));

                if (dep == null){
                    dep = instantiateDepartamento(rs);
                    map.put(rs.getInt("Departamento"), dep);
                }

                Vendedor obj = instantiateVendedor(rs, dep, departamento);
                list.add(obj);

                return list;
            }
            return null;

        } catch (SQLException e) {
            throw  new DbException(e.getMessage());
        }
    }

    @Override
    public List<Vendedor> findByDepartamento(Departamento departamento) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT vendedor. *,departamento.Name as DepName "
                            + "FROM vendendor INNER JOIN departamento "
                            +"ON vendedor.DepartamentoId = departamento.Id "
                            + "WHERE DepartamentoId = ? "
                            + "ORDER BY Name ");

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

                Vendedor obj = instantiateVendedor(rs, dep, departamento);
                list.add(obj);

                return list;
            }
            return null;

        } catch (SQLException e) {
            throw  new DbException(e.getMessage());
        }
    }
}
