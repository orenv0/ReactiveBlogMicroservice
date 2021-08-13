package demo;

import org.springframework.stereotype.Component;


@Component
public class PostEntityConverter {
	
	public PostBoundary fromEntity(PostEntity entity) {
		PostBoundary boundary = new PostBoundary();
		boundary.setUser(new User(entity.getUserEmail()));
		boundary.setProduct(new Product(entity.getProductId()));
		boundary.setLanguage(entity.getLanguage());
		boundary.setPostingTimestamp(entity.getPostingTimestamp());
		boundary.setPostContent(entity.getPostContent());
		
		return boundary;
		
		
	}
	
	public PostEntity toEntity(PostBoundary boundary) {
		PostEntity entity = new PostEntity();
		entity.setUserEmail(boundary.getUser().getEmail());
		entity.setProductId(boundary.getProduct().getId());
		entity.setLanguage(boundary.getLanguage());
		entity.setPostingTimestamp(boundary.getPostingTimestamp());
		entity.setPostContent(boundary.getPostContent());
		
		return entity;
	}

}
