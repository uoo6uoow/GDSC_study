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
    public Long createMember(String loginId, String pw) throws Exception {
        Member member = new Member(loginId, pw);

        if (loginId.equals(memberRepository.findByLoginId(loginId))) {
            throw new Exception("중복되는 아이디입니다.");
        }

        memberRepository.save(member);
        return member.getId();

    }

    //로그인
    @Transactional
    public Member login(String loginId, String pw) throws Exception {
        //아이디로 멤버 조회
        Member member = memberRepository.findByLoginId(loginId);

        //멤버가 존재하고 비밀번호가 일치하는지 확인
        if (member != null && pw.equals(member.getPw())) {
            return member;
        }else{
            throw new Exception("아이디 혹은 비밀번호가 일치하지 않습니다.");
        }
    }

    //계정 정보 수정
    @Transactional
    public void updateMember(Long memberId, String newPassword) throws Exception {
        Member member = memberRepository.findById(memberId);

        //이전 비밀 번호랑 동일시 변경 불가
        if (newPassword.equals(member.getPw())) {
            throw new Exception("사용하던 비밀번호로는 변경할 수 없습니다.");
        }
        member.updateMember(member.getLoginId(), newPassword);
    }

    //회원 삭제
    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId);
        memberRepository.deleteById(memberId);
    }
}
