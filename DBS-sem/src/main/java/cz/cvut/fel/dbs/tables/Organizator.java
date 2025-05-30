package cz.cvut.fel.dbs.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "organizator")
public class Organizator {
    @EmbeddedId
    private OrganizatorId id;

    @MapsId("nazevTurnaje")
    @ManyToOne
    @JoinColumn(name = "nazev_turnaje", nullable = false)
    private Turnaj nazevTurnaje;

    public OrganizatorId getId() {
        return id;
    }

    public void setId(OrganizatorId id) {
        this.id = id;
    }

    public Turnaj getNazevTurnaje() {
        return nazevTurnaje;
    }

    public void setNazevTurnaje(Turnaj nazevTurnaje) {
        this.nazevTurnaje = nazevTurnaje;
    }

    @Override
    public String toString() {
        return "Organizator{" +
                "id=" + id.toString() +
                '}';
    }
}
