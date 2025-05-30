package cz.cvut.fel.dbs.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import cz.cvut.fel.dbs.tables.Cena;
import cz.cvut.fel.dbs.tables.Turnaj;
import cz.cvut.fel.dbs.tables.Tym;
import java.time.LocalDate;
import java.util.List;

public class CenaDAO {

    private final EntityManager em;

    public CenaDAO(EntityManager em) {
        this.em = em;
    }

    public Cena insertCena(String nazev, LocalDate datumUdeleni, String typ, Tym tym, Turnaj turnaj) {
        EntityTransaction et = em.getTransaction();
        try {
            Cena c = new Cena();
            c.setNazev(nazev);
            c.setDatumUdeleni(datumUdeleni);
            c.setTyp(typ);
            c.setTym(tym);
            c.setNazevTurnaje(turnaj);
            c.setNazevTymu(tym.getNazev());
            et.begin();
            em.persist(c);
            et.commit();
            return c;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Cena> getAll() {
        return em.createQuery("SELECT c FROM Cena AS c", Cena.class).getResultList();
    }

    public Cena getCena(String nazevTymu) {
        return em.find(Cena.class, nazevTymu);
    }

    public Cena updateCena(Cena c) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Cena updated = em.merge(c);
            et.commit();
            return updated;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public void deleteCena(Cena c) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.remove(c);
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Cena> getCenaByTyp(String typ) {
        TypedQuery<Cena> q = em.createQuery("SELECT c FROM Cena AS c WHERE c.typ = :typ", Cena.class);
        q.setParameter("typ", typ);
        return q.getResultList();
    }
}
