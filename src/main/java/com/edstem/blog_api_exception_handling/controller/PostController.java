package com.edstem.blog_api_exception_handling.controller;

import com.edstem.blog_api_exception_handling.contract.PostDTO;
import com.edstem.blog_api_exception_handling.model.Post;
import com.edstem.blog_api_exception_handling.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping
	public ResponseEntity<Post> createPost(@Valid @RequestBody PostDTO postDTO) {
		return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Post>> getAllPosts() {
		return ResponseEntity.ok(postService.getAllPosts());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Post> getPostById(@PathVariable Long id) {
		return ResponseEntity.ok(postService.getPostById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable Long id, @Valid @RequestBody PostDTO postDTO) {
		return ResponseEntity.ok(postService.updatePost(id, postDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable Long id) {
		postService.deletePost(id);
		return ResponseEntity.ok("Post deleted successfully");
	}
}
