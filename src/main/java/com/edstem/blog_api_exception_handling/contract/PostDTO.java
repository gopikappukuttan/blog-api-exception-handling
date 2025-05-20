package com.edstem.blog_api_exception_handling.contract;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDTO {
	private Long id;
	@NotBlank(message = "title is required")
	private String title;
	@NotBlank(message = "content is required")
	private String content;
}
