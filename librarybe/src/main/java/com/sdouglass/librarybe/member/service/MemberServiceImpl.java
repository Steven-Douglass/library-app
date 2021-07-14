package com.sdouglass.librarybe.member.service;

import com.sdouglass.librarybe.LibraryMember.service.LibraryMemberService;
import com.sdouglass.librarybe.address.entity.Address;
import com.sdouglass.librarybe.address.service.AddressService;
import com.sdouglass.librarybe.checkouttransaction.service.CheckOutTransactionService;
import com.sdouglass.librarybe.member.dao.MemberDAO;
import com.sdouglass.librarybe.member.dao.MemberDAOImpl;
import com.sdouglass.librarybe.member.entity.Member;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;
    private final LibraryMemberService libraryMemberService;
    private final AddressService addressService;
    private final CheckOutTransactionService checkOutTransactionService;

    @Autowired
    public MemberServiceImpl(MemberDAO memberDAO,
                             LibraryMemberService libraryMemberService,
                             AddressService addressService,
                             CheckOutTransactionService checkOutTransactionService) {
        this.memberDAO = memberDAO;
        this.libraryMemberService = libraryMemberService;
        this.addressService = addressService;
        this.checkOutTransactionService = checkOutTransactionService;
    }

    @Override
    @Transactional
    public Member getMember(Integer id) {
        Member member = memberDAO.getMember(id);
        if (member == null) {
            throw new RuntimeException("Member ID not found - " + id);
        }
        return member;
    }

    @Override
    @Transactional
    public List<Member> getAllMembers() {
        return memberDAO.getAllMembers();
    }

    @Override
    @Transactional
    public void saveMember(Member member) {
        memberDAO.saveMember(member);
    }

    @Override
    @Transactional
    public String deleteMember(Integer id) {
        Member member = getMember(id);
        Address address = addressService.getAddress(member.getAddress().getAddressID());
        libraryMemberService.deleteLibraryMemberForMemberId(id);
        checkOutTransactionService.deleteAllCheckOutTransactionsForMember(id);
        memberDAO.deleteMember(member.getMemberID());
        addressService.deleteAddress(member.getAddress().getAddressID());
        return "Deleted Member with ID: " + id;
    }
}
