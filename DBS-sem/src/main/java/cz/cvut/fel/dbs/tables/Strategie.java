package cz.cvut.fel.dbs.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "strategie")
public class Strategie {
    @Id
    @Column(name = "hrac", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "hrac", nullable = false)
    private Hrac hrac;

    @Lob
    @Column(name = "strategie", nullable = false)
    private String strategie;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Hrac getHrac() {
        return hrac;
    }

    public void setHrac(Hrac hrac) {
        this.hrac = hrac;
    }

    public String getStrategie() {
        return strategie;
    }

    public void setStrategie(String strategie) {
        this.strategie = strategie;
    }

    @Override
    public String toString() {
        return "Strategie{" +
                "hrac=" + hrac.getId() +
                ", strategie='" + strategie + '\'' +
                '}';
    }
}
