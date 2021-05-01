package dao;

import model.Organization;

import java.util.List;

public interface OrgDAO {
    List<Organization> findAll(Organization org);

    Organization findByID(Organization org);

    Organization findByName(Organization org);

    Organization save(Organization org);
    void delete(Organization org);
}
