package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.tables.Organizator;
import cz.cvut.fel.dbs.tables.OrganizatorId;
import cz.cvut.fel.dbs.tables.Turnaj;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class OrganizatorDAO {

    private final EntityManager em;

    public OrganizatorDAO(EntityManager em) {
        this.em = em;
    }

    public Organizator insertOrganizator(OrganizatorId id, Turnaj turnaj) {
        EntityTransaction et = em.getTransaction();
        try {
            Organizator o = new Organizator();
            o.setId(id);
            o.setNazevTurnaje(turnaj);
            et.begin();
            em.persist(o);
            et.commit();
            return o;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Organizator> getAll() {
        return em.createQuery("SELECT o FROM Organizator AS o", Organizator.class).getResultList();
    }

    public Organizator getOrganizator(OrganizatorId id) {
        return em.find(Organizator.class, id);
    }

    public Organizator updateOrganizator(Organizator o) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Organizator updated = em.merge(o);
            et.commit();
            return updated;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public void deleteOrganizator(Organizator o) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.remove(o);
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }
    }

    public List<Organizator> getOrganizatorByTurnaj(Turnaj turnaj) {
        TypedQuery<Organizator> q = em.createQuery(
                "SELECT o FROM Organizator As o WHERE o.nazevTurnaje = :turnaj", Organizator.class);
        q.setParameter("turnaj", turnaj);
        return q.getResultList();
    }
}
