package cz.cvut.fel.dbs.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "se_ucastni")
public class SeUcastni {
    @EmbeddedId
    private SeUcastniId id;

    @MapsId("nazevTurnaje")
    @ManyToOne
    @JoinColumn(name = "nazev_turnaje", nullable = false)
    private Turnaj nazevTurnaje;

    @MapsId("nazevTymu")
    @ManyToOne
    @JoinColumn(name = "nazev_tymu", nullable = false)
    private Tym nazevTymu;

    public SeUcastniId getId() {
        return id;
    }

    public void setId(SeUcastniId id) {
        this.id = id;
    }

    public Turnaj getNazevTurnaje() {
        return nazevTurnaje;
    }

    public void setNazevTurnaje(Turnaj nazevTurnaje) {
        this.nazevTurnaje = nazevTurnaje;
    }

    public Tym getNazevTymu() {
        return nazevTymu;
    }

    public void setNazevTymu(Tym nazevTymu) {
        this.nazevTymu = nazevTymu;
    }

    @Override
    public String toString() {
        return "SeUcastni{" +
                "id=" + id.toString() +
                '}';
    }
}
