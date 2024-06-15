package com.acheron.flowers.store.repository;

import com.acheron.flowers.store.entity.Comment;
import com.acheron.flowers.store.entity.ShopComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopCommentRepository extends JpaRepository<ShopComment,Long> {
}
