package com.example.todo_api.member.dto;

import lombok.Getter;

@Getter
public class MemberCreateRequest {
    private Long memberId;
    private String logInId;
    private String password;
}
