package mo.ed.course.two.amit.newsretrofitapp.models.news;

import java.io.Serializable;

public class SourceDTO implements Serializable {
	private Object id;
	private String name;

	public void setId(Object id){
		this.id = id;
	}

	public Object getId(){
		return id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"SourceDTO{" + 
			"id = '" + id + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}