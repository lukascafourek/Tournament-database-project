package cz.cvut.fel.dbs.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import cz.cvut.fel.dbs.tables.Turnaj;
import cz.cvut.fel.dbs.tables.Tym;
import cz.cvut.fel.dbs.tables.Zapas;
import java.time.LocalDate;
import java.util.List;

public class ZapasDAO {

    private final EntityManager em;

    public ZapasDAO(EntityManager em) {
        this.em = em;
    }

    public Zapas insertZapas(String mapa, String tymA, String tymB, LocalDate datumKonani,
                             String viteznyTym, Integer delkaMin, Turnaj turnaj) {
        EntityTransaction et = em.getTransaction();
        try {
            Zapas z = new Zapas();
            z.setMapa(mapa);
            z.setTymA(tymA);
            z.setTymB(tymB);
            z.setDatumKonani(datumKonani);
            z.setViteznyTym(viteznyTym);
            z.setDelkaMin(delkaMin);
            z.setNazevTurnaje(turnaj);
            et.begin();
            em.persist(z);
            et.commit();
            return z;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }

    }

    public List<Zapas> getAll() {
        return em.createQuery("SELECT z FROM Zapas AS z", Zapas.class).getResultList();
    }

    public Zapas getZapas(Integer id) {
        return em.find(Zapas.class, id);
    }

    public Zapas updateZapas(Zapas z) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Zapas updated = em.merge(z);
            et.commit();
            return updated;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public void deleteZapas(Zapas z) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.remove(z);
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Zapas> getZapasByMapa(String mapa) {
        TypedQuery<Zapas> q = em.createQuery("SELECT z FROM Zapas AS z WHERE z.mapa = :mapa", Zapas.class);
        q.setParameter("mapa", mapa);
        return q.getResultList();
    }

    public void insertTymDoZapasu(Integer idZapasu, Tym tym) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Zapas zapas = em.find(Zapas.class, idZapasu);
            if (zapas != null) {
                zapas.getTymy().add(tym);
                em.merge(zapas);
            }
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public void deleteTymZeZapasu(Integer idZapasu, Tym tym) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Zapas zapas = em.find(Zapas.class, idZapasu);
            if (zapas != null) {
                zapas.getTymy().remove(tym);
                em.merge(zapas);
            }
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Tym> getTymyVZapasu(Long idZapasu) {
        TypedQuery<Tym> query = em.createQuery(
                "SELECT t FROM Tym AS t JOIN t.zapasy AS z WHERE z.id = :idZapasu", Tym.class);
        query.setParameter("idZapasu", idZapasu);
        return query.getResultList();
    }
}
