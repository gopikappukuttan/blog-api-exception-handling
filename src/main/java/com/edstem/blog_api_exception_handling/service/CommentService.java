package com.edstem.blog_api_exception_handling.service;

import com.edstem.blog_api_exception_handling.exceptions.ResourceNotFoundException;
import com.edstem.blog_api_exception_handling.model.Comment;
import com.edstem.blog_api_exception_handling.model.Post;
import com.edstem.blog_api_exception_handling.repository.CommentRepository;
import com.edstem.blog_api_exception_handling.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;

	public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
	}

	public Comment addComment(Long postId, Comment comment) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
		comment.setPost(post);
		return commentRepository.save(comment);
	}

	public List<Comment> getCommentsByPost(Long postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
		return commentRepository.findByPost(post);
	}
}
