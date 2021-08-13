package demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostService {
	public Mono<PostBoundary> createPost (PostBoundary post);
	public Flux<PostBoundary> getAllByUser(String email, String sortBy,String sortOrder,String filterType,String filterVal);
	public Flux<PostBoundary> getAllByProduct(String productId, String sortBy,String sortOrder,String filterType,String filterVal);
	public Mono<Void> deleteAll();
	public Flux<PostBoundary> getAll(String sortBy, String sortOrder, String filterType,
			String filterVal);
	
}
