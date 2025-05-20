package com.edstem.blog_api_exception_handling.controller;

import com.edstem.blog_api_exception_handling.model.Comment;
import com.edstem.blog_api_exception_handling.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {
	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping
	public ResponseEntity<Comment> addComment(@PathVariable Long postId,
											  @RequestBody @Valid Comment comment) {
		return new ResponseEntity<>(commentService.addComment(postId, comment), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId) {
		return ResponseEntity.ok(commentService.getCommentsByPost(postId));
	}
}
