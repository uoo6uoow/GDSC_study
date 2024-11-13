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

    @Column(name = "isFriend", columnDefinition = "tinyint(1)")
    private boolean isFriend = false;


    public Friend(Member member, Member friend) {

        this.member = member;
        this.friend = friend;
    }
    public boolean isFriend() {
        return isFriend;
    }

    public void updateFriend(boolean accept) {
        isFriend = accept;
    }

}
