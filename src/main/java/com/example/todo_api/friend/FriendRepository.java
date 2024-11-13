package com.example.todo_api.friend;

import com.example.todo_api.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FriendRepository {

    @PersistenceContext
    private EntityManager em;

    //친구 생성
    public void save(Friend friend){em.persist(friend);}

    //전체 친구 조회
    public List<Friend> findByMember(Member member) {
        return em.createQuery("select f from Friend as f where f.member = :member or f.friend = :member", Friend.class)
                .setParameter("member", member)
                .getResultList();
    }

    //단일 친구 조회
    public Friend findById(Long friendId) {
        return em.find(Friend.class, friendId);
    }


    //삭제
    public void deleteById(Friend friend) {
        em.remove(friend);
    }
}
