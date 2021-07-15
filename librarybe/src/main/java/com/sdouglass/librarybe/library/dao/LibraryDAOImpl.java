package com.sdouglass.librarybe.library.dao;

import com.sdouglass.librarybe.address.service.AddressService;
import com.sdouglass.librarybe.library.entity.Library;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class LibraryDAOImpl implements LibraryDAO {
    private EntityManager entityManager;
    private AddressService addressService;

    @Autowired
    public LibraryDAOImpl(EntityManager entityManager, AddressService addressService) {
        this.entityManager = entityManager;
        this.addressService = addressService;
    }

    @Override
    public Library getLibrary(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Library.class, id);
    }

    @Override
    public List<Library> getAllLibraries() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Library> libraryQuery = currentSession.createQuery("FROM Library", Library.class);
        List<Library> libraries = libraryQuery.getResultList();
        return libraries;
    }

    @Override
    public void saveLibrary(Library library) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(library);
    }

    @Override
    public void deleteLibrary(Integer id) {
        entityManager.unwrap(Session.class).createQuery("DELETE FROM Library l " +
                "WHERE l.libraryID = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
