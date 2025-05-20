package com.edstem.blog_api_exception_handling.service;

import com.edstem.blog_api_exception_handling.contract.PostDTO;
import com.edstem.blog_api_exception_handling.exceptions.ResourceNotFoundException;
import com.edstem.blog_api_exception_handling.model.Post;
import com.edstem.blog_api_exception_handling.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public Post createPost(PostDTO postDTO){
		Post post=new Post();
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		return postRepository.save(post);
	}

	public List<Post>getAllPosts(){
		return postRepository.findAll();
	}

	public Post getPostById(Long id){
		return postRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Post not found with ID: "+ id));
	}

	public Post updatePost(Long id, PostDTO postDTO){
		Post post=getPostById(id);
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		return postRepository.save(post);
	}

	public void deletePost(Long id){
		Post post=getPostById(id);
		postRepository.delete(post);
	}

}