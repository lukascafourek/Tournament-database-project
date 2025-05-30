package cz.cvut.fel.dbs.tables;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "profesional")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "typ", discriminatorType = DiscriminatorType.STRING)
public abstract class Profesional {
    @Id
    @Column(name = "cislo_smlouvy", nullable = false)
    protected Integer id;

    @Column(name = "jmeno", nullable = false, length = 50)
    protected String jmeno;

    @Column(name = "prijmeni", nullable = false, length = 50)
    protected String prijmeni;

    @Column(name = "prezdivka_ve_hre", nullable = false, length = 50)
    protected String prezdivkaVeHre;

    @Column(name = "datum_narozeni", nullable = false)
    protected LocalDate datumNarozeni;

    @Column(name = "zeme_puvodu", nullable = false, length = 50)
    protected String zemePuvodu;

    @Column(name = "clenem_od", nullable = false)
    protected LocalDate clenemOd;

    @Column(name = "typ", nullable = false)
    protected String typ;

    @ManyToOne
    @JoinColumn(name = "nazev_tymu", nullable = false)
    protected Tym nazevTymu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getPrezdivkaVeHre() {
        return prezdivkaVeHre;
    }

    public void setPrezdivkaVeHre(String prezdivkaVeHre) {
        this.prezdivkaVeHre = prezdivkaVeHre;
    }

    public LocalDate getDatumNarozeni() {
        return datumNarozeni;
    }

    public void setDatumNarozeni(LocalDate datumNarozeni) {
        this.datumNarozeni = datumNarozeni;
    }

    public String getZemePuvodu() {
        return zemePuvodu;
    }

    public void setZemePuvodu(String zemePuvodu) {
        this.zemePuvodu = zemePuvodu;
    }

    public LocalDate getClenemOd() {
        return clenemOd;
    }

    public void setClenemOd(LocalDate clenemOd) {
        this.clenemOd = clenemOd;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Tym getNazevTymu() {
        return nazevTymu;
    }

    public void setNazevTymu(Tym nazevTymu) {
        this.nazevTymu = nazevTymu;
    }

    @Override
    public String toString() {
        return "Profesional{" +
                "id=" + id +
                ", jmeno='" + jmeno + '\'' +
                ", prijmeni='" + prijmeni + '\'' +
                ", prezdivkaVeHre='" + prezdivkaVeHre + '\'' +
                ", datumNarozeni=" + datumNarozeni +
                ", zemePuvodu='" + zemePuvodu + '\'' +
                ", clenemOd=" + clenemOd +
                ", typ=" + typ +
                ", nazevTymu=" + nazevTymu.getNazev() +
                '}';
    }
}
