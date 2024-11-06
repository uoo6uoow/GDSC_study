package com.example.todo_api.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    //생성
    public void save(Member member) {
        em.persist(member);
    }

    //조회
    public Member findById(Long memberId) {
        return em.find(Member.class, memberId);
    }

    public Member findByLoginId(String loginId) {
        return em.find(Member.class, loginId);
    }

    //삭제
    public void deleteMember(Long memberId) {
        Member member = findById(memberId);
        em.remove(member);
    }
}
