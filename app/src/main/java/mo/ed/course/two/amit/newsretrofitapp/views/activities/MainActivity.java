package mo.ed.course.two.amit.newsretrofitapp.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.List;

import mo.ed.course.two.amit.newsretrofitapp.BuildConfig;
import mo.ed.course.two.amit.newsretrofitapp.R;
import mo.ed.course.two.amit.newsretrofitapp.databinding.ActivityMainBinding;
import mo.ed.course.two.amit.newsretrofitapp.models.news.ArticlesDTO;
import mo.ed.course.two.amit.newsretrofitapp.viewmodels.ArticlesViewModel;
import mo.ed.course.two.amit.newsretrofitapp.views.adapters.NewsApiRecyclerAdapter;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int mYear;
    private int mMonth;
    private int mDay;
    private String mSelectedDate;

    ArticlesViewModel articlesViewModel;
    private NewsApiRecyclerAdapter articlesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        articlesViewModel = new ViewModelProvider(this).get(ArticlesViewModel.class);
        binding.imgDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        binding.tvSelectDate.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                        mSelectedDate = dayOfMonth + "-" + (month + 1) + "-" + year;
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!TextUtils.isEmpty(binding.etSearch.getText())) {
//                    String query = binding.etSearch.getText().toString();
//                    if (query != null && mSelectedDate != null) {
                        articlesViewModel.getArticles(null, null).observe(MainActivity.this, new Observer<List<ArticlesDTO>>() {
                            @Override
                            public void onChanged(List<ArticlesDTO> articlesList) {
                                if (articlesList != null) {
                                    if (articlesList.size() > 0) {
                                        populateRecyclerView(articlesList);
                                    }
                                }
                            }
                        });
                    }
//                }
//            }
        });
    }

    private void populateRecyclerView(List<ArticlesDTO> articlesList) {
        articlesAdapter = new NewsApiRecyclerAdapter(MainActivity.this, articlesList, false);
        binding.rvArticles.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        binding.rvArticles.setLayoutManager(mLayoutManager);
        binding.rvArticles.setItemAnimator(new DefaultItemAnimator());
        articlesAdapter.setHasStableIds(true);
        binding.rvArticles.setAdapter(articlesAdapter);

    }
}