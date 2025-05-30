package cz.cvut.fel.dbs.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "souperi_v")
public class SouperiV {
    @EmbeddedId
    private SouperiVId id;

    @MapsId("idZapasu")
    @ManyToOne
    @JoinColumn(name = "id_zapasu", nullable = false)
    private Zapas idZapasu;

    @MapsId("nazevTymu")
    @ManyToOne
    @JoinColumn(name = "nazev_tymu", nullable = false)
    private Tym nazevTymu;

    public SouperiVId getId() {
        return id;
    }

    public void setId(SouperiVId id) {
        this.id = id;
    }

    public Zapas getIdZapasu() {
        return idZapasu;
    }

    public void setIdZapasu(Zapas idZapasu) {
        this.idZapasu = idZapasu;
    }

    public Tym getNazevTymu() {
        return nazevTymu;
    }

    public void setNazevTymu(Tym nazevTymu) {
        this.nazevTymu = nazevTymu;
    }

    @Override
    public String toString() {
        return "SouperiV{" +
                "id=" + id.toString() +
                '}';
    }
}
