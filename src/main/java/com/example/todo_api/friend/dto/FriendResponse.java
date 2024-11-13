package com.example.todo_api.friend.dto;

import com.example.todo_api.member.dto.MemberResponse;
import lombok.Getter;

@Getter
public class FriendResponse {
    private Long friendId;
    private MemberResponse member;
    private MemberResponse friend;
}
