package cz.cvut.fel.dbs.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serial;
import java.util.Objects;

@Embeddable
public class JeSpoluhracemId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 6227642024210631909L;
    @Column(name = "hrac", nullable = false)
    private Integer hrac;

    @Column(name = "spoluhrac", nullable = false)
    private Integer spoluhrac;

    public Integer getHrac() {
        return hrac;
    }

    public void setHrac(Integer hrac) {
        this.hrac = hrac;
    }

    public Integer getSpoluhrac() {
        return spoluhrac;
    }

    public void setSpoluhrac(Integer spoluhrac) {
        this.spoluhrac = spoluhrac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JeSpoluhracemId entity = (JeSpoluhracemId) o;
        return Objects.equals(this.spoluhrac, entity.spoluhrac) &&
                Objects.equals(this.hrac, entity.hrac);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spoluhrac, hrac);
    }

    @Override
    public String toString() {
        return "JeSpoluhracemId{" +
                "hrac=" + hrac +
                ", spoluhrac=" + spoluhrac +
                '}';
    }
}
