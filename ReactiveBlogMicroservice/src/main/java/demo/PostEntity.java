package demo;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="POSTS")
public class PostEntity {
	@Id
	private String id;
	private String userEmail;
	private String productId;
	private Date postingTimestamp;
	private String language;
	private Map<String, Object> postContent;
	
	
	
	public PostEntity() {
		super();
	}

	public PostEntity(String id, String userEmail, String productId, Date postingTimestamp, String language,
			Map<String, Object> postContent) {
		super();
		this.id = id;
		this.userEmail = userEmail;
		this.productId = productId;
		this.postingTimestamp = postingTimestamp;
		this.language = language;
		this.postContent = postContent;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String user) {
		this.userEmail = user;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
		return "PostEntity [id=" + id + ", userEmail=" + userEmail + ", productId=" + productId + ", postingTimestamp="
				+ postingTimestamp + ", language=" + language + ", postContent=" + postContent + "]";
	}
	

}
