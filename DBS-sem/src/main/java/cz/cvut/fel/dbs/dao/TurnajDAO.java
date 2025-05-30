package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.tables.Tym;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import cz.cvut.fel.dbs.tables.Turnaj;
import java.time.LocalDate;
import java.util.List;

public class TurnajDAO {

    private final EntityManager em;

    public TurnajDAO(EntityManager em) {
        this.em = em;
    }

    public Turnaj insertTurnaj(String nazev, String zeme, String mesto, LocalDate datumKonani) {
        EntityTransaction et = em.getTransaction();
        try {
            Turnaj t = new Turnaj();
            t.setNazev(nazev);
            t.setZeme(zeme);
            t.setMesto(mesto);
            t.setDatumKonani(datumKonani);
            et.begin();
            em.persist(t);
            et.commit();
            return t;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Turnaj> getAll() {
        return em.createQuery("SELECT t FROM Turnaj AS t", Turnaj.class).getResultList();
    }

    public Turnaj getTurnaj(String nazev) {
        return em.find(Turnaj.class, nazev);
    }

    public Turnaj updateTurnaj(Turnaj t) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Turnaj updated = em.merge(t);
            et.commit();
            return updated;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public void deleteTurnaj(Turnaj t) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.remove(t);
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Turnaj> getTurnajByZeme(String zeme) {
        TypedQuery<Turnaj> q = em.createQuery("SELECT t FROM Turnaj AS t WHERE t.zeme = :zeme", Turnaj.class);
        q.setParameter("zeme", zeme);
        return q.getResultList();
    }

    public void insertTymDoTurnaje(String nazevTurnaje, Tym tym) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Turnaj t = em.find(Turnaj.class, nazevTurnaje);
            if (t != null) {
                t.getTymy().add(tym);
                em.merge(t);
            }
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public void deleteTymZTurnaje(String nazevTurnaje, Tym tym) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Turnaj t = em.find(Turnaj.class, nazevTurnaje);
            if (t != null) {
                t.getTymy().remove(tym);
                em.merge(t);
            }
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Tym> getTymyVTurnaji(String nazevTurnaje) {
        TypedQuery<Tym> q = em.createQuery(
                "SELECT t FROM Tym AS t JOIN t.turnaje AS u WHERE u.nazev = :nazevTurnaje", Tym.class);
        q.setParameter("nazevTurnaje", nazevTurnaje);
        return q.getResultList();
    }
}
