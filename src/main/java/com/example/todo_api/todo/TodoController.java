package com.example.todo_api.todo;

import com.example.todo_api.todo.dto.TodoCreateRequest;
import com.example.todo_api.todo.dto.TodoResponse;
import com.example.todo_api.todo.dto.TodoUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<Void> createTodo(@RequestBody TodoCreateRequest request) throws Exception { //인자로 http 요청이 들어와야됨
        Long todoId = todoService.createTodo(request.getContent(), request.getMemberId());

        return ResponseEntity.created(URI.create("/todo/" + todoId)).build(); //201 상태코드 응답 생성
    }


    @GetMapping("/list")
    public ResponseEntity<List<Todo>> getTodoList(@RequestBody Long memberId) throws Exception {
        List<Todo> todos = todoService.getTodoList(memberId);
        return ResponseEntity.ok().body(todos);
    }

    // DELETE
    @DeleteMapping("/{todoId}") //변수에 값이 들어오게 지정 가능
    public ResponseEntity<Void> deleteTodo(@PathVariable("todoId") Long todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.noContent().build(); //204 no content
    }

    //PATCH
    @PatchMapping("/{todoId}")
    public ResponseEntity<Void> updateTodo(@PathVariable("todoId") Long todoId, @RequestBody TodoUpdateRequest request) throws Exception {
        todoService.updateTodo(todoId, request.getMemberId(), request.getContent());

        return ResponseEntity.ok().build();
    }
}
