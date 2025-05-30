package cz.cvut.fel.dbs.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "hraje")
public class Hraje {
    @EmbeddedId
    private HrajeId id;

    @MapsId("hrac")
    @ManyToOne
    @JoinColumn(name = "hrac", nullable = false)
    private Hrac hrac;

    @MapsId("idKola")
    @ManyToOne
    @JoinColumn(name = "id_kola", nullable = false)
    private Kolo idKola;

    public HrajeId getId() {
        return id;
    }

    public void setId(HrajeId id) {
        this.id = id;
    }

    public Hrac getHrac() {
        return hrac;
    }

    public void setHrac(Hrac hrac) {
        this.hrac = hrac;
    }

    public Kolo getIdKola() {
        return idKola;
    }

    public void setIdKola(Kolo idKola) {
        this.idKola = idKola;
    }

    @Override
    public String toString() {
        return "Hraje{" +
                "id=" + id.toString() +
                '}';
    }
}
