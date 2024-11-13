package com.example.todo_api.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    public void createMemberTest() {
        //given
        Member member = new Member("loginId", "pw");

        //when
        memberRepository.save(member);

        //then
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    public void createMemberTest_fail_whenDuplicatedLoginId() {
        //given

        //when

        //then


    }
}
