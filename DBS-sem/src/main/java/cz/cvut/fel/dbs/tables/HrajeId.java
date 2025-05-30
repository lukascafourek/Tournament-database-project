package cz.cvut.fel.dbs.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serial;
import java.util.Objects;

@Embeddable
public class HrajeId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = -5582558879128477753L;
    @Column(name = "hrac", nullable = false)
    private Integer hrac;

    @Column(name = "id_kola", nullable = false)
    private Integer idKola;

    public Integer getHrac() {
        return hrac;
    }

    public void setHrac(Integer hrac) {
        this.hrac = hrac;
    }

    public Integer getIdKola() {
        return idKola;
    }

    public void setIdKola(Integer idKola) {
        this.idKola = idKola;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HrajeId entity = (HrajeId) o;
        return Objects.equals(this.idKola, entity.idKola) &&
                Objects.equals(this.hrac, entity.hrac);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKola, hrac);
    }

    @Override
    public String toString() {
        return "HrajeId{" +
                "hrac=" + hrac +
                ", idKola=" + idKola +
                '}';
    }
}
