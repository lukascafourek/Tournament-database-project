package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.tables.OrganizatorId;

public class OrganizatorIdDAO {
    public OrganizatorId insertOrganizatorId(String nazevTurnaje, String jmeno, String prijmeni) {
        OrganizatorId o = new OrganizatorId();
        o.setNazevTurnaje(nazevTurnaje);
        o.setJmeno(jmeno);
        o.setPrijmeni(prijmeni);
        return o;
    }
}
