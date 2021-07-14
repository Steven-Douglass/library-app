package com.sdouglass.librarybe.entity;

import com.sdouglass.librarybe.member.entity.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void testEquals() {
        Member member1 = new Member();
        member1.setMemberID(1);
        member1.setFirstName("Steven");
        member1.setLastName("Douglass");

        Member member2 = new Member();
        member2.setMemberID(1);
        member2.setFirstName("Steven");
        member2.setLastName("Douglass");

        Member member3 = new Member();
        member3.setMemberID(6);
        member3.setFirstName("Latoya");
        member3.setLastName("Bridges");

        assertEquals(member1, member1);
        assertEquals(member2, member2);
        assertEquals(member1, member2);
        assertEquals(member2, member1);
        assertNotEquals(member1, member3);
        assertNotEquals(member3, member1);
        assertNotEquals(member2, member3);
        assertNotEquals(member3, member2);
    }
}