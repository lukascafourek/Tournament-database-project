package cz.cvut.fel.dbs.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import cz.cvut.fel.dbs.tables.Hrac;
import cz.cvut.fel.dbs.tables.Kolo;
import cz.cvut.fel.dbs.tables.Zapas;
import java.util.List;

public class KoloDAO {

    private final EntityManager em;

    public KoloDAO(EntityManager em) {
        this.em = em;
    }

    public Kolo insertKolo(Integer cislo, Zapas zapas, String viteznyTym, String nejlepsiHrac, Integer delkaMin) {
        EntityTransaction et = em.getTransaction();
        try {
            Kolo k = new Kolo();
            k.setCislo(cislo);
            k.setIdZapasu(zapas);
            k.setViteznyTym(viteznyTym);
            k.setNejlepsiHrac(nejlepsiHrac);
            k.setDelkaMin(delkaMin);
            et.begin();
            em.persist(k);
            et.commit();
            return k;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Kolo> getAll() {
        return em.createQuery("SELECT k FROM Kolo AS k", Kolo.class).getResultList();
    }

    public Kolo getKolo(Integer id) {
        return em.find(Kolo.class, id);
    }

    public Kolo updateKolo(Kolo k) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Kolo updated = em.merge(k);
            et.commit();
            return updated;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public void deleteKolo(Kolo k) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.remove(k);
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Kolo> getKoloByViteznyTym(String viteznyTym) {
        TypedQuery<Kolo> q = em.createQuery("SELECT k FROM Kolo AS k WHERE k.viteznyTym = :viteznyTym", Kolo.class);
        q.setParameter("viteznyTym", viteznyTym);
        return q.getResultList();
    }

    public void insertHraceDoKola(Integer idKola, Hrac hrac) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Kolo kolo = em.find(Kolo.class, idKola);
            if (kolo != null) {
                kolo.getHraci().add(hrac);
                em.merge(kolo);
            }
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public void deleteHraceZKola(Integer idKola, Hrac hrac) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Kolo kolo = em.find(Kolo.class, idKola);
            if (kolo != null) {
                kolo.getHraci().remove(hrac);
                em.merge(kolo);
            }
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Hrac> getHraciVKole(Integer idKola) {
        TypedQuery<Hrac> query = em.createQuery(
                "SELECT h FROM Hrac AS h JOIN h.kola AS k WHERE k.id = :idKola", Hrac.class);
        query.setParameter("idKola", idKola);
        return query.getResultList();
    }
}
