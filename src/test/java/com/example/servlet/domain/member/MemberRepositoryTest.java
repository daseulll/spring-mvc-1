package com.example.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member("hello", 22);

        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(member.getId());
        assertThat(savedMember).isEqualTo(findMember);
    }

    @Test
    void findAll() {
        Member member1 = new Member("member1", 22);
        Member member2 = new Member("member2", 22);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> allMember = memberRepository.findAll();
        assertThat(allMember.size()).isEqualTo(2);
        assertThat(allMember).contains(member1, member2);
    }
}