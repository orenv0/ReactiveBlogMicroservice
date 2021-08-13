package demo;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostServiceDb implements PostService {
	private PostDao postDao;
	private PostEntityConverter postEntityConverter;

	@Autowired
	public PostServiceDb(PostDao postDao, PostEntityConverter postEntityConverter) {
		super();
		this.postDao = postDao;
		this.postEntityConverter = postEntityConverter;
	}

	@Override
	public Mono<PostBoundary> createPost(PostBoundary post) {
		return Mono.just(post).map(boundary -> {
			boundary.setPostingTimestamp(new Date());
			return boundary;
		}).map(boundary -> this.postEntityConverter.toEntity(boundary)).flatMap(entity -> this.postDao.save(entity))
				.map(this.postEntityConverter::fromEntity);
	}

	@Override
	public Flux<PostBoundary> getAllByUser(String email, String sortBy, String sortOrder, String filterType,
			String filterVal) {
		if(filterType==null)
			return this.postDao.findAllByUserEmail(email,Sort.by(Direction.valueOf(sortOrder), sortBy)).map(this.postEntityConverter::fromEntity);
		switch (filterType.toLowerCase()) {
		case "bylanguage":
			return this.postDao.findAllByUserEmailAndLanguage(email, filterVal, Sort.by(Direction.valueOf(sortOrder), sortBy))
					.map(this.postEntityConverter::fromEntity);
		case "bycreation":
			int numOfDays=0;
			 
			switch(TimeEnum.valueOf(filterVal)) {
			case lastDay:		
				numOfDays = -1;
				break;
			case lastWeek:
				numOfDays = -7;
				break;
			case lastMonth:
				numOfDays = -30;
				break;
			}
			Date date = getSpecificDate(numOfDays);
			return this.postDao.findAllByUserEmailAndPostingTimestampGreaterThan(email,date,Sort.by(Direction.valueOf(sortOrder), sortBy)).map(this.postEntityConverter::fromEntity);

		case "byproduct":
			return this.postDao.findAllByUserEmailAndProductId(email,filterVal,Sort.by(Direction.valueOf(sortOrder), sortBy)).map(this.postEntityConverter::fromEntity);
			
		}
		throw new RuntimeException();
	}


	@Override
	public Flux<PostBoundary> getAllByProduct(String productId, String sortBy, String sortOrder, String filterType,
			String filterVal) {
		if(filterType==null)
			return this.postDao.findAllByProductId(productId,Sort.by(Direction.valueOf(sortOrder), sortBy)).map(this.postEntityConverter::fromEntity);
		
		switch (filterType.toLowerCase()) {
		case "bylanguage":
			return this.postDao.findAllByProductIdAndLanguage(productId, filterVal, Sort.by(Direction.valueOf(sortOrder), sortBy))
					.map(this.postEntityConverter::fromEntity);
		case "bycreation":
			int numOfDays=0;
					 
			switch(TimeEnum.valueOf(filterVal)) {
			case lastDay:		
				numOfDays = -1;
				break;
			case lastWeek:
				numOfDays = -7;
				break;
			case lastMonth:
				numOfDays = -30;
				break;
			}
			Date date = getSpecificDate(numOfDays);
			return this.postDao.findAllByProductIdAndPostingTimestampGreaterThan(productId,date,Sort.by(Direction.valueOf(sortOrder), sortBy)).map(this.postEntityConverter::fromEntity);
		
		}
		throw new RuntimeException();
	}

	@Override
	public Flux<PostBoundary> getAll(String sortBy, String sortOrder, String filterType, String filterVal) {
		// TODO Auto-generated method stub
		switch (filterType.toLowerCase()) {
		case "bycount":
			return this.postDao.findAll(Sort.by(Direction.DESC,"postingTimestamp")).map(this.postEntityConverter::fromEntity).limitRequest(Integer.parseInt(filterVal));	
		case "bycreation":
			int numOfDays=0;
			
			switch(TimeEnum.valueOf(filterVal)) {
			case lastDay:		
				numOfDays = -1;
				break;
			case lastWeek:
				numOfDays = -7;
				break;
			case lastMonth:
				numOfDays = -30;
				break;
			}
            Date date = getSpecificDate(numOfDays);
			return this.postDao.findAllByPostingTimestampGreaterThan(date,Sort.by(Direction.valueOf(sortOrder), sortBy)).map(this.postEntityConverter::fromEntity);

		}
		throw new RuntimeException();
	}

	@Override
	public Mono<Void> deleteAll() {
		return postDao.deleteAll();
	}
	
	public Date getSpecificDate(int time) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, time);
		Date date = cal.getTime();
		return date;
	}

}
