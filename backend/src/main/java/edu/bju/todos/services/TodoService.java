package edu.bju.todos.services;

import edu.bju.todos.models.Todo;
import edu.bju.todos.repos.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public Page<Todo> findAllByTitleLike(String title, Pageable pageable) {
        return todoRepository.findAllByTitleContains(title, pageable);
    }
    public Page<Todo> findAll(Pageable pageable) {
        return todoRepository.findAll(pageable);
    }
}
