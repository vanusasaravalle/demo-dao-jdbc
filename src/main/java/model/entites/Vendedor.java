package model.entites;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Vendedor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String email;
    private Date nascimento;
    private Double salario;

    private Departamento departamento;

    public Vendedor(Object o, String greg, String s, Date date, double v, Departamento departamento) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nascimento = nascimento;
        this.salario = salario;
        this.departamento = this.departamento;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vendedor)) return false;
        Vendedor vendedor = (Vendedor) o;
        return getId().equals(vendedor.getId()) &&
                getName().equals(vendedor.getName()) &&
                getEmail().equals(vendedor.getEmail()) &&
                getNascimento().equals(vendedor.getNascimento()) &&
                getSalario().equals(vendedor.getSalario()) &&
                getDepartamento().equals(vendedor.getDepartamento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getNascimento(), getSalario(), getDepartamento());
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", nascimento=" + nascimento +
                ", salario=" + salario +
                ", departamento=" + departamento +
                '}';
    }
}
