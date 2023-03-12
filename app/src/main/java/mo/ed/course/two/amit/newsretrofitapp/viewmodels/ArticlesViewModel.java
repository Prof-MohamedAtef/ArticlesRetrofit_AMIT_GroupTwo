package mo.ed.course.two.amit.newsretrofitapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import mo.ed.course.two.amit.newsretrofitapp.data.ArticlesRepository;
import mo.ed.course.two.amit.newsretrofitapp.models.news.ArticlesDTO;

public class ArticlesViewModel extends AndroidViewModel {
//    private final MediatorLiveData<List<ArticlesDTO>> articlesListMediatorLiveData;
    private final ArticlesRepository articlesRepository;
    
    public ArticlesViewModel(@NonNull Application application) {
        super(application);
        articlesRepository = new ArticlesRepository(application);
//        articlesListMediatorLiveData = new MediatorLiveData<>();
    }
    
    public MutableLiveData<List<ArticlesDTO>> getArticles(String query, String date){
        return articlesRepository.getArticles(query, date);
    }
}
