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
        return em.createQuery("SELECT m from Member m WHERE m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getSingleResult();
    }

    //삭제
    public void deleteById(Long memberId) {
        Member member = findById(memberId);
        em.remove(member);
    }
}
