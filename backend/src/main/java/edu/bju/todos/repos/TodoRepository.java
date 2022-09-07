package edu.bju.todos.repos;

import edu.bju.todos.models.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {
    Page<Todo> findAllByTitleContains(String title, Pageable pageable);
}
