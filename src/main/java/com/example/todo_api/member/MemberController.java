package com.example.todo_api.member;

import com.example.todo_api.member.dto.MemberCreateRequest;
import com.example.todo_api.member.dto.MemberLoginRequest;
import com.example.todo_api.member.dto.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberCreateRequest request) throws Exception {
        Long memberId = memberService.createMember(request.getLogInId(), request.getPassword());

        return ResponseEntity.created(URI.create("/member" + memberId)).build();
    }

    @PatchMapping
    public ResponseEntity<Void> login(@RequestBody MemberLoginRequest request) throws Exception {
        Member member = memberService.login(request.getLogInId(), request.getPassword());

        return ResponseEntity.created(URI.create("/member" + member.getId())).build();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<Void> updateMember(@PathVariable Long memberId, @RequestBody MemberUpdateRequest request) throws Exception {
        memberService.updateMember(memberId, request.getPassword());
        return ResponseEntity.ok().build();
    }


}
