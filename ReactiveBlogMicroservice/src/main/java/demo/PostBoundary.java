package demo;

import java.util.Date;
import java.util.Map;

public class PostBoundary {

	private User user;
	private Product product;
	private Date postingTimestamp;
	private String language;
	private Map<String, Object> postContent;


	public PostBoundary() {
		super();
		
	}

	public PostBoundary(User user, Product product, Date postingTimestamp, String language,
			Map<String, Object> postContent) {
		super();

		this.user = user;
		this.product = product;
		this.postingTimestamp = postingTimestamp;
		this.language = language;
		this.postContent = postContent;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getPostingTimestamp() {
		return postingTimestamp;
	}

	public void setPostingTimestamp(Date postingTimestamp) {
		this.postingTimestamp = postingTimestamp;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Map<String, Object> getPostContent() {
		return postContent;
	}

	public void setPostContent(Map<String, Object> postContent) {
		this.postContent = postContent;
	}

	@Override
	public String toString() {
		return "PostBoundary ["+ "user=" + user + ", product=" + product + ", postingTimestamp="
				+ postingTimestamp + ", language=" + language + ", postContent=" + postContent + "]";
	}



}
