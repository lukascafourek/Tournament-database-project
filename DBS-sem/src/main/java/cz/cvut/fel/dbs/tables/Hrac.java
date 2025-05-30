package cz.cvut.fel.dbs.tables;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "hrac")
@DiscriminatorValue("h")
public class Hrac extends Profesional {
    @Column(name = "hodnoceni", nullable = false)
    protected Float hodnoceni;

    @ManyToOne
    @JoinColumn(name = "trener", nullable = false)
    protected Trener trener;

    @ManyToMany
    @JoinTable(name = "Hraje",
            joinColumns = @JoinColumn(name = "hrac"),
            inverseJoinColumns = @JoinColumn(name = "id_kola"))
    protected Collection<Kolo> kola;

    @ManyToMany
    @JoinTable(name = "Je_spoluhracem",
            joinColumns = @JoinColumn(name = "hrac"),
            inverseJoinColumns = @JoinColumn(name = "spoluhrac"))
    protected Collection<Hrac> spoluhraci;

    public Float getHodnoceni() {
        return hodnoceni;
    }

    public void setHodnoceni(Float hodnoceni) {
        this.hodnoceni = hodnoceni;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public Collection<Kolo> getKola() {
        return kola;
    }

    public void setKola(Collection<Kolo> kola) {
        this.kola = kola;
    }

    public Collection<Hrac> getSpoluhraci() {
        return spoluhraci;
    }

    public void setSpoluhraci(Collection<Hrac> spoluhraci) {
        this.spoluhraci = spoluhraci;
    }

    @Override
    public String toString() {
        return "Hrac{" +
                "id=" + id +
                ", hodnoceni=" + hodnoceni +
                ", trener=" + trener.getId() +
                '}';
    }
}
