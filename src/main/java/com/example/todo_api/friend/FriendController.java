package com.example.todo_api.friend;

import com.example.todo_api.friend.dto.FriendCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friend")
public class FriendController {

    private final FriendService friendService;

    //친구요청 생성
    @PostMapping
    public ResponseEntity<Void> createFriendRequest(@RequestBody FriendCreateRequest request) throws Exception {
        Long friendId = friendService.friendRequest(request.getMember().getId(), request.getFriend().getId());

        return ResponseEntity.created(URI.create("friend" + friendId)).build();
    }

    //친구 조회
    @GetMapping("/list")
    public ResponseEntity<List<Friend>> getFriendList(@RequestBody Long memberId) throws Exception {
        List<Friend> friendList = friendService.getAllFriend(memberId);
        return ResponseEntity.ok().body(friendList);
    }

    //친구 삭제
    @DeleteMapping("/{friendId}")
    public ResponseEntity<Void> deleteFriend(@PathVariable Long friendId) throws Exception {
        friendService.deleteFriend(friendId);
        return ResponseEntity.noContent().build();
    }



}
