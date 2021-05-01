package dao;

import model.Organization;

import java.util.List;

public interface OrgDAO {
    List<Organization> findAll();

    Organization findByID(Organization org);

    Organization findByID(int id);

    Organization save(Organization org);
    void delete(Organization org);
}
