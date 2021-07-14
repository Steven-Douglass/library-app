package com.sdouglass.librarybe.member.controller;

import com.sdouglass.librarybe.member.entity.Member;
import com.sdouglass.librarybe.member.service.MemberService;
import com.sdouglass.librarybe.member.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{id}")
    public Member getMember(@PathVariable Integer id) {
        return memberService.getMember(id);
    }

    @GetMapping("")
    public List<Member> getMembers() {
        return memberService.getAllMembers();
    }

    @PostMapping("")
    public Member addMember(@RequestBody Member member) {
        memberService.saveMember(member);
        return member;
    }

    @PutMapping("")
    public Member updateMember(@RequestBody Member member) {
        memberService.saveMember(member);
        return member;
    }

    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable Integer id) {
        return memberService.deleteMember(id);
    }
}
