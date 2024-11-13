package com.example.todo_api.member.dto;

import lombok.Getter;

@Getter
public class MemberLoginRequest {
    String logInId;
    String password;
}
