package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.tables.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class HracDAO {

    private final EntityManager em;

    public HracDAO(EntityManager em) {
        this.em = em;
    }

    public Hrac insertHrac(Integer id, String jmeno, String prijmeni, String prezdivkaVeHre, LocalDate datumNarozeni,
                           String zemePuvodu, LocalDate clenemOd, String typ, Tym tym, Float hodnoceni, Trener trener) {
        EntityTransaction et = em.getTransaction();
        try {
            Hrac h = new Hrac();
            ProfesionalDAO pdao = new ProfesionalDAO(em);
            pdao.insertProfesional(h, id, jmeno, prijmeni, prezdivkaVeHre, datumNarozeni, zemePuvodu, clenemOd, typ, tym);
            h.setHodnoceni(hodnoceni);
            h.setTrener(trener);
            et.begin();
            em.persist(h);
            et.commit();
            return h;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Hrac> getAll() {
        return em.createQuery("SELECT h FROM Hrac AS h", Hrac.class).getResultList();
    }

    public Hrac getHrac(Integer id) {
        return em.find(Hrac.class, id);
    }

    public Hrac updateHrac(Hrac h) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Hrac updated = em.merge(h);
            et.commit();
            return updated;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public void deleteHrac(Hrac h) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.remove(h);
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Hrac> getHracByHodnoceni(Float hodnoceni) {
        TypedQuery<Hrac> q = em.createQuery("SELECT h FROM Hrac As h WHERE h.hodnoceni >= :hodnoceni", Hrac.class);
        q.setParameter("hodnoceni", hodnoceni);
        return q.getResultList();
    }

    public void insertKoloHraci(Integer idHrace, Kolo kolo) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Hrac hrac = em.find(Hrac.class, idHrace);
            if (hrac != null) {
                hrac.getKola().add(kolo);
                em.merge(hrac);
            }
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public void deleteKoloHraci(Integer idHrace, Kolo kolo) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Hrac hrac = em.find(Hrac.class, idHrace);
            if (hrac != null) {
                hrac.getKola().remove(kolo);
                em.merge(hrac);
            }
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Kolo> getKolaHrace(Integer idHrace) {
        TypedQuery<Kolo> query = em.createQuery(
                "SELECT k FROM Kolo AS k JOIN k.hraci AS h WHERE h.id = :idHrace", Kolo.class);
        query.setParameter("idHrace", idHrace);
        return query.getResultList();
    }

    public void insertSpoluhrace(Integer idHrace, Hrac spoluhrac) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Hrac hrac = em.find(Hrac.class, idHrace);
            if (hrac != null) {
                hrac.getSpoluhraci().add(spoluhrac);
                em.merge(hrac);
            }
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public void deleteSpoluhrace(Integer idHrace, Hrac spoluhrac) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Hrac hrac = em.find(Hrac.class, idHrace);
            if (hrac != null) {
                hrac.getSpoluhraci().remove(spoluhrac);
                em.merge(hrac);
            }
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Hrac> getSpoluhraci(Integer idHrace) {
        TypedQuery<Hrac> query = em.createQuery(
                "SELECT s FROM Hrac AS h JOIN h.spoluhraci AS s WHERE h.id = :idHrace", Hrac.class);
        query.setParameter("idHrace", idHrace);
        return query.getResultList();
    }
}
