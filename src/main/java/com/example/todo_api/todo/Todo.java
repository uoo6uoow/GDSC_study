package com.example.todo_api.todo;

import com.example.todo_api.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) //엔터티에서 생성자 생성
@Getter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //값이 자동으로 1씩 증가, identity -> 사용자 지정
    @Column(name = "todo_id")
    private Long id;

    @Column(name = "todo_content", columnDefinition = "varchar(200)")
    private String content;

    @Column(name = "todo_is_chckded", columnDefinition = "tinyint(1)")
    private boolean isChecked = false;

    //외래키 등록
    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY) //투두는 다대일 관계에서 다수의 입장이므로 ManyToOne 사용
    private Member member;

    public Todo(String content, Member member) { //id는 @GeneratedValue로 이미 생성
        this.content = content;
        this.member = member;
    }

    public void updateContent(String newContent) {
        this.content = newContent;
    }
    public void check() {
        if (!this.isChecked) {
            this.isChecked = true;
        }else this.isChecked = false;
    }
}
