package cz.cvut.fel.dbs.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serial;
import java.util.Objects;

@Embeddable
public class SouperiVId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 359842955602492987L;
    @Column(name = "id_zapasu", nullable = false)
    private Integer idZapasu;

    @Column(name = "nazev_tymu", nullable = false, length = 50)
    private String nazevTymu;

    public Integer getIdZapasu() {
        return idZapasu;
    }

    public void setIdZapasu(Integer idZapasu) {
        this.idZapasu = idZapasu;
    }

    public String getNazevTymu() {
        return nazevTymu;
    }

    public void setNazevTymu(String nazevTymu) {
        this.nazevTymu = nazevTymu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SouperiVId entity = (SouperiVId) o;
        return Objects.equals(this.nazevTymu, entity.nazevTymu) &&
                Objects.equals(this.idZapasu, entity.idZapasu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazevTymu, idZapasu);
    }

    @Override
    public String toString() {
        return "SouperiVId{" +
                "idZapasu=" + idZapasu +
                ", nazevTymu='" + nazevTymu + '\'' +
                '}';
    }
}
