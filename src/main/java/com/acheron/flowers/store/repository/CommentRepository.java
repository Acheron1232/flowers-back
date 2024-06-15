package com.acheron.flowers.store.repository;

import com.acheron.flowers.store.entity.Category;
import com.acheron.flowers.store.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
