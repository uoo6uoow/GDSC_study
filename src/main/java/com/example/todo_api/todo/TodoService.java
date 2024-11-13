package com.example.todo_api.todo;

import com.example.todo_api.member.Member;
import com.example.todo_api.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    //할 일 생성
    @Transactional
    public Long createTodo(String content, Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId);

        if (member ==null) { //존재하지 않는 멤버에 대한 예외 처리, 에러 처리 순서도 신경쓰기
            throw new Exception("존재하지 않는 멤버입니다.");
        }

        Todo todo = new Todo(content, member);
        todoRepository.save(todo);

        return todo.getId();
    }

    //할 일 조회(특정 멤버의 모든 할 일 조회)
    @Transactional(readOnly = true) //조회기능 구현에서는 readOnly를 통해 수정이 안되게
    public List<Todo> getTodoList(Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId);

        if (member ==null) { //존재하지 않는 멤버에 대한 예외 처, 에러 처리 순서도 신경쓰기
            throw new Exception("존재하지 않는 멤버입니다.");
        }

       return todoRepository.findAllByMember(member);
    }

    //할 일 수정
    @Transactional
    public void updateTodo(Long todoId, Long memberId, String updateContent) throws Exception {
        Todo todo = todoRepository.findById(todoId);
        Member member = memberRepository.findById(memberId);

        //엄밀한 검증
        if (todo == null) {
            throw new Exception("존재하지 않는 할 일 입니다.");
        }

        if (member == null) {
            throw new Exception("존재하지 않는 멤버입니다.");
        }

        if (todo.getMember() != member) {
            throw new Exception("다른 유저의 할 일을 수정할 수 없습니다.");
        }

        todo.updateContent(updateContent);
    }

    //할 일 삭제
    @Transactional
    public void deleteTodo(Long todoId) {
        //Todo todo = todoRepository.findById(todoId);
        todoRepository.deleteById(todoId);
    }
}
