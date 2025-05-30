package cz.cvut.fel.dbs.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "je_spoluhracem")
public class JeSpoluhracem {
    @EmbeddedId
    private JeSpoluhracemId id;

    @MapsId("hrac")
    @ManyToOne
    @JoinColumn(name = "hrac", nullable = false)
    private Hrac hrac;

    @MapsId("spoluhrac")
    @ManyToOne
    @JoinColumn(name = "spoluhrac", nullable = false)
    private Hrac spoluhrac;

    public JeSpoluhracemId getId() {
        return id;
    }

    public void setId(JeSpoluhracemId id) {
        this.id = id;
    }

    public Hrac getHrac() {
        return hrac;
    }

    public void setHrac(Hrac hrac) {
        this.hrac = hrac;
    }

    public Hrac getSpoluhrac() {
        return spoluhrac;
    }

    public void setSpoluhrac(Hrac spoluhrac) {
        this.spoluhrac = spoluhrac;
    }

    @Override
    public String toString() {
        return "JeSpoluhracem{" +
                "id=" + id +
                ", hrac=" + hrac.getId() +
                ", spoluhrac=" + spoluhrac.getId() +
                '}';
    }
}
