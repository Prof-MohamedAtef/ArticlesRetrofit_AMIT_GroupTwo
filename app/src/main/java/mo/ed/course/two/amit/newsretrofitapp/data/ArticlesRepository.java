package mo.ed.course.two.amit.newsretrofitapp.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import mo.ed.course.two.amit.newsretrofitapp.BuildConfig;
import mo.ed.course.two.amit.newsretrofitapp.models.news.ArticlesDTO;
import mo.ed.course.two.amit.newsretrofitapp.models.news.NewsApiResponseDTO;
import mo.ed.course.two.amit.newsretrofitapp.retrofit.ApiService;
import mo.ed.course.two.amit.newsretrofitapp.retrofit.HTTP;
import mo.ed.course.two.amit.newsretrofitapp.utils.Config;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesRepository {
    private final MutableLiveData<List<ArticlesDTO>> newsListMutableLiveData;

    public ArticlesRepository(Application application) {
        this.newsListMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<ArticlesDTO>> getArticles(String query, String date) {
//        Call<NewsApiResponseDTO> call = HTTP.create(ApiService.class).getArticles(query, date, Config.PUBLISHED_AT, BuildConfig.NEWS_API_KEY);
        Call<NewsApiResponseDTO> call = HTTP.create(ApiService.class).getArticles("https://newsapi.org/v2/everything?q=tesla&from=2023-02-12&sortBy=publishedAt&apiKey="+BuildConfig.NEWS_API_KEY);
        call.enqueue(new Callback<NewsApiResponseDTO>() {
            @Override
            public void onResponse(Call<NewsApiResponseDTO> call, Response<NewsApiResponseDTO> response) {
                NewsApiResponseDTO newsApiResponse = response.body();
                if (newsApiResponse != null){
                    List<ArticlesDTO> articlesList = newsApiResponse.getArticles();
                    if (articlesList == null ) {
                        newsListMutableLiveData.postValue(null);
                    }else {
                        if (articlesList.size() > 0 ){
                            newsListMutableLiveData.setValue(articlesList);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsApiResponseDTO> call, Throwable t) {
                Log.e("ConnectionFailure", t.toString());
            }
        });

        return newsListMutableLiveData;
    }
}
