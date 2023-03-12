package mo.ed.course.two.amit.newsretrofitapp.retrofit;

import mo.ed.course.two.amit.newsretrofitapp.models.news.NewsApiResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {

    @GET("q={q}&from={from}&sortBy={sortBy}&apiKey={apiKey}")
    Call<NewsApiResponseDTO> getArticles(@Path("q") String query, @Path("from") String from, @Path("sortBy") String sortBy,
                                         @Path("apiKey") String apiKey);

    @GET
    Call<NewsApiResponseDTO> getArticles(@Url String url);
}
