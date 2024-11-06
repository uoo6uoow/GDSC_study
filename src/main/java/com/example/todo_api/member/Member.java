package com.example.todo_api.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "login_ID")
    private String loginId;

    @Column(name = "password")
    private String pw;

    public Member(String loginId, String pw) {
        this.loginId = loginId;
        this.pw = pw;
    }

    public void updateMember(String newId, String newPw) {
        this.loginId = newId;
        this.pw = newPw;
    }
}
