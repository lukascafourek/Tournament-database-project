package cz.cvut.fel.dbs.tables;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "turnaj")
public class Turnaj {
    @Id
    @Column(name = "nazev", nullable = false, length = 50)
    private String nazev;

    @Column(name = "zeme", nullable = false, length = 185)
    private String zeme;

    @Column(name = "mesto", nullable = false, length = 58)
    private String mesto;

    @Column(name = "datum_konani", nullable = false)
    private LocalDate datumKonani;

    @ManyToMany
    @JoinTable(name = "Se_ucastni",
            joinColumns = @JoinColumn(name = "nazev_turnaje"),
            inverseJoinColumns = @JoinColumn(name = "nazev_tymu"))
    private Collection<Tym> tymy;

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getZeme() {
        return zeme;
    }

    public void setZeme(String zeme) {
        this.zeme = zeme;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public LocalDate getDatumKonani() {
        return datumKonani;
    }

    public void setDatumKonani(LocalDate datumKonani) {
        this.datumKonani = datumKonani;
    }

    public Collection<Tym> getTymy() {
        return tymy;
    }

    public void setTymy(Collection<Tym> tymy) {
        this.tymy = tymy;
    }

    @Override
    public String toString() {
        return "Turnaj{" +
                "nazev='" + nazev + '\'' +
                ", zeme='" + zeme + '\'' +
                ", mesto='" + mesto + '\'' +
                ", datumKonani=" + datumKonani +
                '}';
    }
}
