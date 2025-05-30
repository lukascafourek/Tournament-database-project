package cz.cvut.fel.dbs.tables;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trener")
@DiscriminatorValue("t")
public class Trener extends Profesional {
    @Column(name = "zkusenosti_od", nullable = false)
    protected LocalDate zkusenostiOd;

    public LocalDate getZkusenostiOd() {
        return zkusenostiOd;
    }

    public void setZkusenostiOd(LocalDate zkusenostiOd) {
        this.zkusenostiOd = zkusenostiOd;
    }

    @Override
    public String toString() {
        return "Trener{" +
                "id=" + id +
                ", zkusenostiOd=" + zkusenostiOd +
                '}';
    }
}
