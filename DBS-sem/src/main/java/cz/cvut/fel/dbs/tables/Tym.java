package cz.cvut.fel.dbs.tables;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "tym")
public class Tym {
    @Id
    @Column(name = "nazev", nullable = false, length = 50)
    private String nazev;

    @Column(name = "zeme_puvodu", nullable = false, length = 185)
    private String zemePuvodu;

    @Column(name = "datum_vzniku", nullable = false)
    private LocalDate datumVzniku;

    @Column(name = "hodnoceni", nullable = false)
    private Float hodnoceni;

    @ManyToMany(mappedBy = "tymy")
    private Collection<Turnaj> turnaje;

    @ManyToMany
    @JoinTable(name = "Souperi_v",
            joinColumns = @JoinColumn(name = "nazev_tymu"),
            inverseJoinColumns = @JoinColumn(name = "id_zapasu"))
    private Collection<Zapas> zapasy;

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getZemePuvodu() {
        return zemePuvodu;
    }

    public void setZemePuvodu(String zemePuvodu) {
        this.zemePuvodu = zemePuvodu;
    }

    public LocalDate getDatumVzniku() {
        return datumVzniku;
    }

    public void setDatumVzniku(LocalDate datumVzniku) {
        this.datumVzniku = datumVzniku;
    }

    public Float getHodnoceni() {
        return hodnoceni;
    }

    public void setHodnoceni(Float hodnoceni) {
        this.hodnoceni = hodnoceni;
    }

    public Collection<Turnaj> getTurnaje() {
        return turnaje;
    }

    public void setTurnaje(Collection<Turnaj> turnaje) {
        this.turnaje = turnaje;
    }

    public Collection<Zapas> getZapasy() {
        return zapasy;
    }

    public void setZapasy(Collection<Zapas> zapasy) {
        this.zapasy = zapasy;
    }

    @Override
    public String toString() {
        return "Tym{" +
                "nazev='" + nazev + '\'' +
                ", zemePuvodu='" + zemePuvodu + '\'' +
                ", datumVzniku=" + datumVzniku +
                ", hodnoceni=" + hodnoceni +
                '}';
    }
}
