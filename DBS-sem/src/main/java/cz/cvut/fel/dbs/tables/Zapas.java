package cz.cvut.fel.dbs.tables;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "zapas")
public class Zapas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "mapa", nullable = false, length = 50)
    private String mapa;

    @Column(name = "tym_a", nullable = false, length = 50)
    private String tymA;

    @Column(name = "tym_b", nullable = false, length = 50)
    private String tymB;

    @Column(name = "datum_konani", nullable = false)
    private LocalDate datumKonani;

    @Column(name = "vitezny_tym", nullable = false, length = 50)
    private String viteznyTym;

    @Column(name = "delka_min", nullable = false)
    private Integer delkaMin;

    @ManyToOne
    @JoinColumn(name = "nazev_turnaje", nullable = false)
    private Turnaj nazevTurnaje;

    @ManyToMany(mappedBy = "zapasy")
    private Collection<Tym> tymy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    public String getTymA() {
        return tymA;
    }

    public void setTymA(String tymA) {
        this.tymA = tymA;
    }

    public String getTymB() {
        return tymB;
    }

    public void setTymB(String tymB) {
        this.tymB = tymB;
    }

    public LocalDate getDatumKonani() {
        return datumKonani;
    }

    public void setDatumKonani(LocalDate datumKonani) {
        this.datumKonani = datumKonani;
    }

    public String getViteznyTym() {
        return viteznyTym;
    }

    public void setViteznyTym(String viteznyTym) {
        this.viteznyTym = viteznyTym;
    }

    public Integer getDelkaMin() {
        return delkaMin;
    }

    public void setDelkaMin(Integer delkaMin) {
        this.delkaMin = delkaMin;
    }

    public Turnaj getNazevTurnaje() {
        return nazevTurnaje;
    }

    public void setNazevTurnaje(Turnaj nazevTurnaje) {
        this.nazevTurnaje = nazevTurnaje;
    }

    public Collection<Tym> getTymy() {
        return tymy;
    }

    public void setTymy(Collection<Tym> tymy) {
        this.tymy = tymy;
    }

    @Override
    public String toString() {
        return "Zapas{" +
                "id=" + id +
                ", mapa='" + mapa + '\'' +
                ", tymA='" + tymA + '\'' +
                ", tymB='" + tymB + '\'' +
                ", datumKonani=" + datumKonani +
                ", viteznyTym='" + viteznyTym + '\'' +
                ", delkaMin=" + delkaMin +
                ", nazevTurnaje=" + nazevTurnaje.getNazev() +
                '}';
    }
}
