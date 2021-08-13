package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PostController {
	private PostService postService;

	@Autowired
	public void setPostService(PostService postService) {
		this.postService = postService;
	}


	@RequestMapping(path = "/blog", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<PostBoundary> createMessage(@RequestBody PostBoundary post) {
		return this.postService.createPost(post);
	}

	@RequestMapping(path = "/blog/byUser/{email}", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<PostBoundary> getAllByUser(@PathVariable("email") String email,
			@RequestParam(name = "sortBy", required = false, defaultValue = "productId") String sortBy,
			@RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder,
			@RequestParam(name = "filterType", required = false) String filterType,
			@RequestParam(name = "filterValue", required = false) String filterVal) {
		return this.postService.getAllByUser(email, sortBy,sortOrder,filterType,filterVal);
	}
	
	@RequestMapping(path = "/blog/byProduct/{productId}", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<PostBoundary> getAllByProduct(@PathVariable("productId") String productId,
			@RequestParam(name = "sortBy", required = false, defaultValue = "userEmail") String sortBy,
			@RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder,
			@RequestParam(name = "filterType", required = false) String filterType,
			@RequestParam(name = "filterValue", required = false) String filterVal) {
		return this.postService.getAllByProduct(productId, sortBy,sortOrder,filterType,filterVal);
	}
	@RequestMapping(path = "/blog", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<PostBoundary> getAllPosts(
			@RequestParam(name = "sortBy", required = false, defaultValue = "userEmail") String sortBy,
			@RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder,
			@RequestParam(name = "filterType", required = false) String filterType,
			@RequestParam(name = "filterValue", required = false) String filterVal) {
		return this.postService.getAll(sortBy,sortOrder,filterType,filterVal);
	}
	
	
	@RequestMapping(path = "/blog", method = RequestMethod.DELETE)
	public Mono<Void> deleteAllPost() {
		return this.postService.deleteAll();
	}

}
