package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.tables.Hrac;
import cz.cvut.fel.dbs.tables.Strategie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class StrategieDAO {

    private final EntityManager em;

    public StrategieDAO(EntityManager em) {
        this.em = em;
    }

    public Strategie insertStrategie(Integer id, Hrac hrac, String strategie) {
        EntityTransaction et = em.getTransaction();
        try {
            Strategie s = new Strategie();
            s.setId(id);
            s.setHrac(hrac);
            s.setStrategie(strategie);
            et.begin();
            em.persist(s);
            et.commit();
            return s;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Strategie> getAll() {
        return em.createQuery("SELECT s FROM Strategie AS s", Strategie.class).getResultList();
    }

    public Strategie getStrategie(Integer id) {
        return em.find(Strategie.class, id);
    }

    public Strategie updateStrategie(Strategie s) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Strategie updated = em.merge(s);
            et.commit();
            return updated;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public void deleteStrategie(Strategie s) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.remove(s);
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Strategie> getStrategieByText(String text) {
        TypedQuery<Strategie> q = em.createQuery(
                "SELECT s FROM Strategie AS s WHERE s.strategie = :text", Strategie.class);
        q.setParameter("text", text);
        return q.getResultList();
    }
}
