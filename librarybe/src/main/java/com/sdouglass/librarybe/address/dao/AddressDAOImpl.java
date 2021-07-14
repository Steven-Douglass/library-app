package com.sdouglass.librarybe.address.dao;

import com.sdouglass.librarybe.address.entity.Address;
import com.sdouglass.librarybe.library.entity.Library;
import com.sdouglass.librarybe.member.entity.Member;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AddressDAOImpl implements AddressDAO {

    private final EntityManager entityManager;

    @Autowired
    public AddressDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Address getAddress(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Address.class, id);
    }

    @Override
    public List<Address> getAllAddresses() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Address> addressQuery = currentSession.createQuery("FROM Address", Address.class);
        List<Address> addresses = addressQuery.getResultList();
        return addresses;
    }

    @Override
    public void saveAddress(Address address) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(address);
    }

    @Override
    public void deleteAddress(Integer id) {
        // Get list of libraries who use this address
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Library> libraryQuery = currentSession.createQuery("FROM Library l WHERE l.address.addressID = :id");
        libraryQuery.setParameter("id", id);
        List<Library> libraryList = libraryQuery.getResultList();
        List<Integer> libraryIDs = libraryList.stream().map(Library::getLibraryID).collect(Collectors.toList());

        // Remove the address reference of any library with this address
        if (!libraryIDs.isEmpty()) {
            Query<Library> libraryAddressQuery = currentSession.createQuery("UPDATE Library set address.addressID = null " +
                    "WHERE id IN :libraryIDs");
            libraryAddressQuery.setParameter("libraryIDs", libraryIDs);
            libraryAddressQuery.executeUpdate();
        }

        // Get list of members who use this address
        Query<Member> memberQuery = currentSession.createQuery("FROM Member m WHERE m.address.addressID = :id");
        memberQuery.setParameter("id", id);
        List<Member> memberList = memberQuery.getResultList();
        List<Integer> memberIDs = memberList.stream().map(Member::getMemberID).collect(Collectors.toList());

        // Remove the address reference of any member with this address
        if (!memberIDs.isEmpty()) {
            Query<Library> memberAddressQuery = currentSession.createQuery("UPDATE Member set address.addressID = null " +
                    "WHERE id IN :memberIDs");
            memberAddressQuery.setParameter("memberIDs", memberIDs);
            memberAddressQuery.executeUpdate();
        }

        // Delete the address
        Query addressQuery = currentSession.createQuery("DELETE FROM Address a WHERE a.addressID = :id");
        addressQuery.setParameter("id", id);
        addressQuery.executeUpdate();
    }
}
