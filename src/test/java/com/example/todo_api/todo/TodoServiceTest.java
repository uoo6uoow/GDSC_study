package com.example.todo_api.todo;


import com.example.todo_api.member.Member;
import com.example.todo_api.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//@SpringBootTest << 서비스 계층 테스트는 단위테스트이므로 통합테스트에 쓰이는 어노테이션은 사용하지 않는다.
@ExtendWith(MockitoExtension.class) // 단위 테스트를 위한 의존성 제거 << 허위 객체(mock)를 통해 단위 테스트 시행
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks // mock 주입 어노테이션
    private TodoService todoService;

    @Test
    public void createTodoTest() throws Exception {
        //bdd (behavior driven development)
        //given (테스트를 실행할 때 필요한 기본 환경 셋팅)
        BDDMockito.given(memberRepository.findById(1L)).willReturn(new Member()); //해당 아이디를 가진 함수가 호출되면 그 아이디를 가진 멤버를 생성
        // ㄴ> 가짜객체의 동작을 지정

        //when (테스트하려는 동작을 실행)
        todoService.createTodo("contents", 1L);

        //then (동작이 실행됐을 , 기대하는 결과를 확인)
        verify(todoRepository,times(1)).save(any(Todo.class));//특정 메서드의 호출 여부, 횟수 확인

    }

    @Test
    public void nullMemberTest() throws Exception {
        //given
        BDDMockito.given(memberRepository.findById(anyLong())).willReturn(null);

        //when & then
        //멤버가 존재하지 않아야 됨
        Assertions.assertThatThrownBy(() -> {
            todoService.createTodo("content", 123414356L);
        }).hasMessageContaining("존재하지 않는 멤버입니다.")
                .isInstanceOf(Exception.class);// 해당 블럭 안이 실행 됐을 때 에러가 나와야됨


    }
}
