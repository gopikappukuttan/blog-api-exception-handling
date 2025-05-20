package com.edstem.blog_api_exception_handling.repository;

import com.edstem.blog_api_exception_handling.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
