package cz.cvut.fel.dbs.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "specializace")
public class Specializace {
    @Id
    @Column(name = "trener", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "trener", nullable = false)
    private Trener trener;

    @Lob
    @Column(name = "specializace", nullable = false)
    private String specializace;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public String getSpecializace() {
        return specializace;
    }

    public void setSpecializace(String specializace) {
        this.specializace = specializace;
    }

    @Override
    public String toString() {
        return "Specializace{" +
                "trener=" + trener.getId() +
                ", specializace='" + specializace + '\'' +
                '}';
    }
}
