package cz.cvut.fel.dbs.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serial;
import java.util.Objects;

@Embeddable
public class OrganizatorId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = -7078144755670020972L;
    @Column(name = "nazev_turnaje", nullable = false, length = 50)
    private String nazevTurnaje;

    @Column(name = "jmeno", nullable = false, length = 50)
    private String jmeno;

    @Column(name = "prijmeni", nullable = false, length = 50)
    private String prijmeni;

    public String getNazevTurnaje() {
        return nazevTurnaje;
    }

    public void setNazevTurnaje(String nazevTurnaje) {
        this.nazevTurnaje = nazevTurnaje;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizatorId entity = (OrganizatorId) o;
        return Objects.equals(this.nazevTurnaje, entity.nazevTurnaje) &&
                Objects.equals(this.prijmeni, entity.prijmeni) &&
                Objects.equals(this.jmeno, entity.jmeno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazevTurnaje, prijmeni, jmeno);
    }

    @Override
    public String toString() {
        return "OrganizatorId{" +
                "nazevTurnaje='" + nazevTurnaje + '\'' +
                ", jmeno='" + jmeno + '\'' +
                ", prijmeni='" + prijmeni + '\'' +
                '}';
    }
}
