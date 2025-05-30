package cz.cvut.fel.dbs.tables;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "kolo")
public class Kolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "cislo", nullable = false)
    private Integer cislo;

    @ManyToOne
    @JoinColumn(name = "id_zapasu", nullable = false)
    private Zapas idZapasu;

    @Column(name = "vitezny_tym", nullable = false, length = 50)
    private String viteznyTym;

    @Column(name = "nejlepsi_hrac", nullable = false, length = 50)
    private String nejlepsiHrac;

    @Column(name = "delka_min", nullable = false)
    private Integer delkaMin;

    @ManyToMany(mappedBy = "kola")
    private Collection<Hrac> hraci;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCislo() {
        return cislo;
    }

    public void setCislo(Integer cislo) {
        this.cislo = cislo;
    }

    public Zapas getIdZapasu() {
        return idZapasu;
    }

    public void setIdZapasu(Zapas idZapasu) {
        this.idZapasu = idZapasu;
    }

    public String getViteznyTym() {
        return viteznyTym;
    }

    public void setViteznyTym(String viteznyTym) {
        this.viteznyTym = viteznyTym;
    }

    public String getNejlepsiHrac() {
        return nejlepsiHrac;
    }

    public void setNejlepsiHrac(String nejlepsiHrac) {
        this.nejlepsiHrac = nejlepsiHrac;
    }

    public Integer getDelkaMin() {
        return delkaMin;
    }

    public void setDelkaMin(Integer delkaMin) {
        this.delkaMin = delkaMin;
    }

    public Collection<Hrac> getHraci() {
        return hraci;
    }

    public void setHraci(Collection<Hrac> hraci) {
        this.hraci = hraci;
    }

    @Override
    public String toString() {
        return "Kolo{" +
                "id=" + id +
                ", cislo=" + cislo +
                ", idZapasu=" + idZapasu.getId() +
                ", viteznyTym='" + viteznyTym + '\'' +
                ", nejlepsiHrac='" + nejlepsiHrac + '\'' +
                ", delkaMin=" + delkaMin +
                '}';
    }
}
