package demo;

import java.util.Date;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;


import reactor.core.publisher.Flux;

public interface PostDao extends ReactiveSortingRepository<PostEntity, String> {

	public Flux<PostEntity> findAllByProductId(@Param("productId") String productId, Sort sort);

	public Flux<PostEntity> findAllByProductIdAndLanguage(@Param("productId") String productId,
			@Param("language") String language, Sort sort);

	public Flux<PostEntity> findAllByProductIdAndPostingTimestampGreaterThan(@Param("productId") String productId,
			@Param("postingTimestamp") Date postingTimestamp, Sort sort);

	public Flux<PostEntity> findAllByPostingTimestampGreaterThan(@Param("postingTimestamp") Date postingTimestamp,
			Sort sort);

	public Flux<PostEntity> findAllByUserEmailAndLanguage(@Param("email") String email,
			@Param("filterVal") String filterVal, Sort by);

	public Flux<PostEntity> findAllByUserEmailAndPostingTimestampGreaterThan(@Param("email") String email,
			@Param("specificDate") Date specificDate, Sort by);

	public Flux<PostEntity> findAllByUserEmail(@Param("email") String email, Sort by);

	public Flux<PostEntity> findAllByUserEmailAndProductId(@Param("email") String email,
			@Param("productId") String productId, Sort by);

}
