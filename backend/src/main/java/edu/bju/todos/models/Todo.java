package edu.bju.todos.models;

import edu.bju.todos.utils.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 255, nullable = false)
    private String title;

    public static Todo of(Long id) {
        if (id == null) return null;
        return BeanUtil.getBean(EntityManager.class).getReference(Todo.class, id);
    }
}
