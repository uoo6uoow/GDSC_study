package com.example.todo_api.member;

import com.example.todo_api.todo.Todo;
import com.example.todo_api.todo.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //유저 생성(회원가입)
    @Transactional
    public void createMember(String loginId, String pw) throws Exception {
        Member member = new Member(loginId, pw);

        if (loginId.equals(memberRepository.findByLoginId(loginId))) {
            throw new Exception("중복되는 아이디입니다.");
        }

        memberRepository.save(member);
    }

    //로그인
    @Transactional
    public Member login(String loginId, String pw) throws Exception {
        //아이디, 비밀번호 일치시 멤버 반환
        if (loginId.equals(memberRepository.findByLoginId(loginId).getLoginId())
                && pw.equals(memberRepository.findByLoginId(loginId).getPw())) {
            return memberRepository.findByLoginId(loginId);
        } else {//아닐 경우 예외 처리
            throw new Exception("아이디 혹은 비밀번호가 일치하지 않습니다.");
        }
    }
}
