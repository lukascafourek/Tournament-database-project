package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.tables.Trener;
import cz.cvut.fel.dbs.tables.Tym;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class TrenerDAO {

    private final EntityManager em;

    public TrenerDAO(EntityManager em) {
        this.em = em;
    }

    public Trener insertTrener(Integer id, String jmeno, String prijmeni, String prezdivkaVeHre, LocalDate datumNarozeni,
                               String zemePuvodu, LocalDate clenemOd, String typ, Tym tym, LocalDate zkusenostiOd) {
        EntityTransaction et = em.getTransaction();
        try {
            Trener t = new Trener();
            ProfesionalDAO pdao = new ProfesionalDAO(em);
            pdao.insertProfesional(t, id, jmeno, prijmeni, prezdivkaVeHre, datumNarozeni, zemePuvodu, clenemOd, typ, tym);
            t.setZkusenostiOd(zkusenostiOd);
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

    public List<Trener> getAll() {
        return em.createQuery("SELECT t FROM Trener AS t", Trener.class).getResultList();
    }

    public Trener getTrener(Integer id) {
        return em.find(Trener.class, id);
    }

    public Trener updateTrener(Trener t) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Trener updated = em.merge(t);
            et.commit();
            return updated;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public void deleteTrener(Trener t) {
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

    public List<Trener> getTrenerByZkusenostiOd(LocalDate zkusenostiOd) {
        TypedQuery<Trener> q = em.createQuery(
                "SELECT t FROM Trener AS t WHERE t.zkusenostiOd = :zkusenostiOd", Trener.class);
        q.setParameter("zkusenostiOd", zkusenostiOd);
        return q.getResultList();
    }
}
