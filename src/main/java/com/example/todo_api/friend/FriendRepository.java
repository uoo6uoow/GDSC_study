package com.example.todo_api.friend;

import com.example.todo_api.member.Member;
import com.example.todo_api.todo.Todo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendRepository {

    @PersistenceContext
    private EntityManager em;

    //친구 요청 생성
    public void save(Friend friend){em.persist(friend);}
    //조회
    // 단건 조회(한 개의 데이터 조회)
    public Friend findById(Long friendId){
        return em.find(Friend.class, friendId);
    }

    // 다건 조회(여러 데이터 조회)
    public List<Friend> findAll(Member member) {
        return em.createQuery("select f from Friend as f where f.member = :member or f.friend = :member", Friend.class)
                .setParameter("member", member)
                .getResultList();// 실행결과를 리스트로 받음
    }

    // 조건 조회(수락된 친구만 조회)
    public List<Friend> findAcceptedFriend(Member member) {
        return em.createQuery("select f from Friend as f where (f.member = :member or f.friend = :member) and f.status = '수락됨'", Friend.class)
                .setParameter("member", member)
                .getResultList();
    }

    //특정 친구의 todo list 조회
    public List<Todo> findTodoByFriend(Member friend) {
        return em.createQuery("select t from Todo as t where t.member = :friend", Todo.class)
                .setParameter("friend", friend)
                .getResultList();
    }

    //삭제

    public void deleteById(Long friendId) {
        Friend friend= findById(friendId);
        em.remove(friend);
    }
}
