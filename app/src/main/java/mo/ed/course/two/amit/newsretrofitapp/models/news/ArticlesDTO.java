package mo.ed.course.two.amit.newsretrofitapp.models.news;

import java.io.Serializable;

public class ArticlesDTO implements Serializable {
	private SourceDTO source;
	private String author;
	private String title;
	private String description;
	private String url;
	private String urlToImage;
	private String publishedAt;
	private String content;

	public void setSource(SourceDTO source){
		this.source = source;
	}

	public SourceDTO getSource(){
		return source;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setUrlToImage(String urlToImage){
		this.urlToImage = urlToImage;
	}

	public String getUrlToImage(){
		return urlToImage;
	}

	public void setPublishedAt(String publishedAt){
		this.publishedAt = publishedAt;
	}

	public String getPublishedAt(){
		return publishedAt;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	@Override
 	public String toString(){
		return 
			"ArticlesDTO{" + 
			"source = '" + source + '\'' + 
			",author = '" + author + '\'' + 
			",title = '" + title + '\'' + 
			",description = '" + description + '\'' + 
			",url = '" + url + '\'' + 
			",urlToImage = '" + urlToImage + '\'' + 
			",publishedAt = '" + publishedAt + '\'' + 
			",content = '" + content + '\'' + 
			"}";
		}
}