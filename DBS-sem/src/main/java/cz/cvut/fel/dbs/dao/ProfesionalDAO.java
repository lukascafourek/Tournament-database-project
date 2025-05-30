package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.tables.Profesional;
import cz.cvut.fel.dbs.tables.Tym;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class ProfesionalDAO {

    private final EntityManager em;

    public ProfesionalDAO(EntityManager em) {
        this.em = em;
    }

    public void insertProfesional(Profesional p, Integer id, String jmeno, String prijmeni, String prezdivkaVeHre,
                                         LocalDate datumNarozeni, String zemePuvodu, LocalDate clenemOd, String typ, Tym tym) {
        p.setId(id);
        p.setJmeno(jmeno);
        p.setPrijmeni(prijmeni);
        p.setPrezdivkaVeHre(prezdivkaVeHre);
        p.setDatumNarozeni(datumNarozeni);
        p.setZemePuvodu(zemePuvodu);
        p.setClenemOd(clenemOd);
        p.setTyp(typ);
        p.setNazevTymu(tym);
    }

    public List<Profesional> getAll() {
        return em.createQuery("SELECT p FROM Profesional AS p", Profesional.class).getResultList();
    }

    public Profesional getProfesional(Integer id) {
        return em.find(Profesional.class, id);
    }

    public List<Profesional> getProfesionalByZemePuvodu(String zemePuvodu) {
        TypedQuery<Profesional> q = em.createQuery(
                "SELECT p FROM Profesional AS p WHERE p.zemePuvodu = :zemePuvodu", Profesional.class);
        q.setParameter("zemePuvodu", zemePuvodu);
        return q.getResultList();
    }
}
