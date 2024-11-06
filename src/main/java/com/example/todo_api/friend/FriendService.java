package com.example.todo_api.friend;

import com.example.todo_api.member.Member;
import com.example.todo_api.member.MemberRepository;
import com.example.todo_api.todo.Todo;
import com.example.todo_api.todo.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;

    //친구 요청
    @Transactional
    public void request(Long memberId, Long friendId) throws Exception {
        Member member = memberRepository.findById(memberId);
        Member friend = memberRepository.findById(friendId);

        if (friend == null) {
            throw new Exception("존재하지 않는 유저입니다.");
        }

        Friend newFriend = new Friend(member, friend);
        friendRepository.save(newFriend);
    }

    //친구 수락
    @Transactional
    public void acceptFriend(Friend friend, boolean accept) throws Exception {
        if ((friend.isFriend() == true) && friend.isFriend() == accept) {
            throw new Exception("이미 친구인 회원입니다.");
        } else if (((friend.isFriend()) == false) && (friend.isFriend()) != accept) { // 친구 수락
            friend.updateFriend(accept);
        } else if ((friend.isFriend() == false) && (friend.isFriend() == accept)) {//친구 대기 중에서 accept이 false일 때(요청 거절)
            friendRepository.deleteById(friend);
        }
    }

    //친구 조회
    @Transactional(readOnly = true)
    public List<Friend> getAllFriend(Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId);

        if (member == null) {
            throw new Exception("존재하지 않는 유저입니다");
        }

        return friendRepository.findByMember(member);
    }

    //친구 삭제
    @Transactional
    public void deleteFriend(Long friendId) throws Exception {
        Friend friend = friendRepository.findById(friendId);

        if (friend == null) {
            throw new Exception("친구가 아니거나 존재하지 않는 유저입니다.");
        }

        friendRepository.deleteById(friend);
    }

    //친구의 할 일 조회
    @Transactional(readOnly = true)
    public List<Todo> getTodoByFriend(Long friendId) throws Exception {
        Friend friendShip = friendRepository.findById(friendId);
        Member friend = memberRepository.findById(friendShip.getFriend().getId());

        if (friend == null) {
            throw new Exception("친구가 아니거나 존재하지 않는 유저입니다.");
        }

        return todoRepository.findAllByMember(friend);

    }
}
