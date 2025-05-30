package cz.cvut.fel.dbs.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import cz.cvut.fel.dbs.tables.Turnaj;
import cz.cvut.fel.dbs.tables.Tym;
import cz.cvut.fel.dbs.tables.Zapas;
import java.time.LocalDate;
import java.util.List;

public class TymDAO {

    private final EntityManager em;

    public TymDAO(EntityManager em) {
        this.em = em;
    }

    public Tym insertTym(String nazev, String zemePuvodu, LocalDate datumVzniku, Float hodnoceni) {
        EntityTransaction et = em.getTransaction();
        try {
            Tym t = new Tym();
            t.setNazev(nazev);
            t.setZemePuvodu(zemePuvodu);
            t.setDatumVzniku(datumVzniku);
            t.setHodnoceni(hodnoceni);
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

    public List<Tym> getAll() {
        return em.createQuery("SELECT t FROM Tym AS t", Tym.class).getResultList();
    }

    public Tym getTym(String nazev) {
        return em.find(Tym.class, nazev);
    }

    public Tym updateTym(Tym t) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Tym updated = em.merge(t);
            et.commit();
            return updated;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public void deleteTym(Tym t) {
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

    public List<Tym> getTymByHodnoceniGTE(Float hodnoceni) {
        TypedQuery<Tym> q = em.createQuery("SELECT t FROM Tym AS t WHERE t.hodnoceni >= :hodnoceni", Tym.class);
        q.setParameter("hodnoceni", hodnoceni);
        return q.getResultList();
    }

    public void insertTurnajDoTymu(String nazevTymu, Turnaj turnaj) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Tym tym = em.find(Tym.class, nazevTymu);
            if (tym != null) {
                tym.getTurnaje().add(turnaj);
                em.merge(tym);
            }
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public void deleteTurnajZTymu(String nazevTymu, Turnaj turnaj) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Tym tym = em.find(Tym.class, nazevTymu);
            if (tym != null) {
                tym.getTurnaje().remove(turnaj);
                em.merge(tym);
            }
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Turnaj> getTurnajeTymu(String nazevTymu) {
        TypedQuery<Turnaj> query = em.createQuery(
                "SELECT t FROM Turnaj AS t JOIN t.tymy AS y WHERE y.nazev = :nazevTymu", Turnaj.class);
        query.setParameter("nazevTymu", nazevTymu);
        return query.getResultList();
    }

    public void insertZapasDoTymu(String nazevTymu, Zapas zapas) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Tym tym = em.find(Tym.class, nazevTymu);
            if (tym != null) {
                tym.getZapasy().add(zapas);
                em.merge(tym);
            }
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public void deleteZapasZTymu(String nazevTymu, Zapas zapas) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Tym tym = em.find(Tym.class, nazevTymu);
            if (tym != null) {
                tym.getZapasy().remove(zapas);
                em.merge(tym);
            }
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Zapas> getZapasyTymu(String nazevTymu) {
        TypedQuery<Zapas> query = em.createQuery(
                "SELECT z FROM Zapas AS z JOIN z.tymy AS y WHERE y.nazev = :nazevTymu", Zapas.class);
        query.setParameter("nazevTymu", nazevTymu);
        return query.getResultList();
    }
}
