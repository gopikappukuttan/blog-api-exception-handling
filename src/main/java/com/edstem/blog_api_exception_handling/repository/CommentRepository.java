package com.edstem.blog_api_exception_handling.repository;

import com.edstem.blog_api_exception_handling.model.Comment;
import com.edstem.blog_api_exception_handling.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByPost(Post post);
}
