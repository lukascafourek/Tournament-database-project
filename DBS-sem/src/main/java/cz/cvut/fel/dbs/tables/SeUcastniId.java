package cz.cvut.fel.dbs.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serial;
import java.util.Objects;

@Embeddable
public class SeUcastniId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = -5831369687891180648L;
    @Column(name = "nazev_turnaje", nullable = false, length = 50)
    private String nazevTurnaje;

    @Column(name = "nazev_tymu", nullable = false, length = 50)
    private String nazevTymu;

    public String getNazevTurnaje() {
        return nazevTurnaje;
    }

    public void setNazevTurnaje(String nazevTurnaje) {
        this.nazevTurnaje = nazevTurnaje;
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
        SeUcastniId entity = (SeUcastniId) o;
        return Objects.equals(this.nazevTurnaje, entity.nazevTurnaje) &&
                Objects.equals(this.nazevTymu, entity.nazevTymu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazevTurnaje, nazevTymu);
    }

    @Override
    public String toString() {
        return "SeUcastniId{" +
                "nazevTurnaje='" + nazevTurnaje + '\'' +
                ", nazevTymu='" + nazevTymu + '\'' +
                '}';
    }
}
