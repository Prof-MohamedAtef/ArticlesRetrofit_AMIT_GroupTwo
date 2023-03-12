package mo.ed.course.two.amit.newsretrofitapp.models.news;

import java.util.List;
import java.io.Serializable;

public class NewsApiResponseDTO implements Serializable {
	private String status;
	private int totalResults;
	private List<ArticlesDTO> articles;

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}

	public void setArticles(List<ArticlesDTO> articles){
		this.articles = articles;
	}

	public List<ArticlesDTO> getArticles(){
		return articles;
	}

	@Override
 	public String toString(){
		return 
			"NewsApiResponseDTO{" + 
			"status = '" + status + '\'' + 
			",totalResults = '" + totalResults + '\'' + 
			",articles = '" + articles + '\'' + 
			"}";
		}
}