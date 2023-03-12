package mo.ed.course.two.amit.newsretrofitapp.retrofit;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import mo.ed.course.two.amit.newsretrofitapp.utils.Config;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HTTP {

    private static final String TAG = "HTTP";

    static String authorizationToken;
    static String language;
    static Retrofit retrofit;

    public static void initialize() {
        Gson gson=new GsonBuilder()
                .setLenient()
                .create();

        String bUrl = Config.base_url;
        retrofit = new Retrofit.Builder()
                .baseUrl(bUrl)
                .client(getHeader())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static <T> T create(Class<T> object) {
        //initialize(App.getConfig());
        if (retrofit == null) {
            initialize();
        }
        return retrofit.create(object);
    }

    public static <T> T create(Class<T> object, String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(getHeader())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(object);
    }

    private static OkHttpClient getHeader() {
        HttpLoggingInterceptor logging=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("InterceptorMessage :", message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(
                        chain -> {
                            Request request = chain.request();
                            Request.Builder requestBuilder = request.newBuilder();
                            requestBuilder.addHeader("Content-Type", Config.MULTIPART);
                            requestBuilder.addHeader("Accept", Config.APPLICATION_JSON);
                            request = requestBuilder.build();
                            return chain.proceed(request);
                        })
                .build();
        return okClient;
    }
}
