package com.example.todo_api.friend;

import com.example.todo_api.member.Member;
import com.example.todo_api.member.MemberRepository;
import com.example.todo_api.todo.TodoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FriendRepositoryTest {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Test
    @Transactional
    public void createFriend() {
        //given
        Member member1 = new Member();
        Member member2 = new Member();
        memberRepository.save(member1);
        memberRepository.save(member2);

        Friend friend = new Friend(member1, member2);
        //when
        friendRepository.save(friend);
        //then
        Assertions.assertThat(friend.getId()).isNotNull();
    }

    @Test
    @Transactional
    public void deleteFriend() {
        //given
        Member member1 = new Member();
        Member member2 = new Member();
        memberRepository.save(member1);
        memberRepository.save(member2);

        Friend friend = new Friend(member1, member2);
        friendRepository.save(friend);
        todoRepository.flushAndClear();

        List<Friend> friendList = friendRepository.findByMember(member1);
        Friend findFriend = friendList.get(0);
        //when
        friendRepository.deleteById(findFriend);
        //then
        Assertions.assertThat(friendList).hasSize(1);
        Assertions.assertThat(friendRepository.findByMember(member1)).hasSize(0);
    }
}
