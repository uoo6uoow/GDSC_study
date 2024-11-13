package com.example.todo_api.friend.dto;

import com.example.todo_api.member.Member;
import lombok.Getter;

@Getter
public class FriendCreateRequest {
    private Long friendId;
    private Member member;
    private Member friend;
}
