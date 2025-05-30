package cz.cvut.fel.dbs.tables;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cena")
public class Cena {
    @Id
    @Column(name = "nazev_tymu", nullable = false, length = 50)
    private String nazevTymu;

    @MapsId
    @OneToOne
    @JoinColumn(name = "nazev_tymu", nullable = false)
    private Tym tym;

    @Column(name = "nazev", nullable = false, length = 50)
    private String nazev;

    @Column(name = "datum_udeleni", nullable = false)
    private LocalDate datumUdeleni;

    @Column(name = "typ", nullable = false, length = 50)
    private String typ;

    @ManyToOne
    @JoinColumn(name = "nazev_turnaje", nullable = false)
    private Turnaj nazevTurnaje;

    public String getNazevTymu() {
        return nazevTymu;
    }

    public void setNazevTymu(String nazevTymu) {
        this.nazevTymu = nazevTymu;
    }

    public Tym getTym() {
        return tym;
    }

    public void setTym(Tym tym) {
        this.tym = tym;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public LocalDate getDatumUdeleni() {
        return datumUdeleni;
    }

    public void setDatumUdeleni(LocalDate datumUdeleni) {
        this.datumUdeleni = datumUdeleni;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Turnaj getNazevTurnaje() {
        return nazevTurnaje;
    }

    public void setNazevTurnaje(Turnaj nazevTurnaje) {
        this.nazevTurnaje = nazevTurnaje;
    }

    @Override
    public String toString() {
        return "Cena{" +
                "nazevTymu='" + nazevTymu + '\'' +
                ", nazev='" + nazev + '\'' +
                ", datumUdeleni=" + datumUdeleni +
                ", typ='" + typ + '\'' +
                ", nazevTurnaje=" + nazevTurnaje.getNazev() +
                '}';
    }
}
