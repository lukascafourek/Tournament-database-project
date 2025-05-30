package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.tables.Specializace;
import cz.cvut.fel.dbs.tables.Trener;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class SpecializaceDAO {

    private final EntityManager em;

    public SpecializaceDAO(EntityManager em) {
        this.em = em;
    }

    public Specializace insertSpecializace(Integer id, Trener trener, String specializace) {
        EntityTransaction et = em.getTransaction();
        try {
            Specializace s = new Specializace();
            s.setId(id);
            s.setTrener(trener);
            s.setSpecializace(specializace);
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

    public List<Specializace> getAll() {
        return em.createQuery("SELECT s FROM Specializace AS s", Specializace.class).getResultList();
    }

    public Specializace getSpecializace(Integer id) {
        return em.find(Specializace.class, id);
    }

    public Specializace udpateSpecializace(Specializace s) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Specializace updated = em.merge(s);
            et.commit();
            return updated;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public void deleteSpecializace(Specializace s) {
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

    public List<Specializace> getSpecializaceByText(String text) {
        TypedQuery<Specializace> q = em.createQuery(
                "SELECT s FROM Specializace As s WHERE s.specializace = :text", Specializace.class);
        q.setParameter("text", text);
        return q.getResultList();
    }
}
