package com.example.todo_api.friend;

import com.example.todo_api.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "friend_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member friend;

    private String status; //친구 상태 (요청, 수락)

    public Friend(Member member, Member friend) {
        this.member = member;
        this.friend = friend;
    }



}
