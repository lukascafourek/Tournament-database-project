package cz.cvut.fel.dbs;

import cz.cvut.fel.dbs.dao.HracDAO;
import cz.cvut.fel.dbs.dao.StrategieDAO;
import cz.cvut.fel.dbs.dao.TurnajDAO;
import cz.cvut.fel.dbs.dao.TymDAO;
import cz.cvut.fel.dbs.tables.Hrac;
import cz.cvut.fel.dbs.tables.Strategie;
import cz.cvut.fel.dbs.tables.Turnaj;
import cz.cvut.fel.dbs.tables.Tym;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class Service {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ApplicationPU");
        EntityManager em = emf.createEntityManager();
        System.out.println();

        TurnajDAO turnajDAO = new TurnajDAO(em);
        Turnaj novyTurnaj = turnajDAO.insertTurnaj("ESL Majors Series Five",
                "Česká republika", "Praha", LocalDate.parse("2018-07-30"));

        TymDAO tymDAO = new TymDAO(em);
        List<Tym> tymy = tymDAO.getAll();
        for (Tym tym : tymy) {
            System.out.println(tym.getNazev());
            turnajDAO.insertTymDoTurnaje(novyTurnaj.getNazev(), tym);
            tymDAO.insertTurnajDoTymu(tym.getNazev(), novyTurnaj);
        }
        System.out.println();

        List<Turnaj> turnaje = turnajDAO.getAll();
        for (Turnaj turnaj : turnaje) {
            System.out.println(turnaj);
        }
        System.out.println();

        novyTurnaj.setZeme("Slovensko");
        novyTurnaj.setMesto("Bratislava");
        Turnaj updatedTurnaj = turnajDAO.updateTurnaj(novyTurnaj);
        System.out.println(updatedTurnaj);
        System.out.println();

        turnajDAO.deleteTurnaj(updatedTurnaj);
        List<Turnaj> konecneTurnaje = turnajDAO.getAll();
        for (Turnaj turnaj : konecneTurnaje) {
            System.out.println(turnaj);
        }
        System.out.println();

        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            HracDAO hracDAO = new HracDAO(em);
            Hrac hrac = hracDAO.getHrac(12345678);
            System.out.println(hrac);
            System.out.println();

            StrategieDAO strategieDAO = new StrategieDAO(em);
            Strategie strategie = strategieDAO.getStrategie(hrac.getId());
            System.out.println(strategie);
            System.out.println();

            Query query = em.createNativeQuery("SELECT aktualizovat_strategii(?, ?)");
            query.setParameter(1, hrac.getId());
            query.setParameter(2, "utocna puska, utok");
            boolean uspech = (boolean) query.getSingleResult();
            if (uspech) {
                et.commit();
                em.refresh(strategie);
                System.out.println(strategie);
                System.out.println("Strategie úspěšně aktualizována.");
            } else {
                et.rollback();
                System.out.println("Hráč s číslem smlouvy " + hrac.getId() + " nebyl nalezen.");
            }
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw e;
        }

        em.close();
        emf.close();
    }
}
