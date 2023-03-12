package mo.ed.course.two.amit.newsretrofitapp.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;
import mo.ed.course.two.amit.newsretrofitapp.R;
import mo.ed.course.two.amit.newsretrofitapp.models.news.ArticlesDTO;

public class NewsApiRecyclerAdapter extends RecyclerView.Adapter<NewsApiRecyclerAdapter.ViewHOlder> {


    private final String LOG_TAG = NewsApiRecyclerAdapter.class.getSimpleName();
    Context mContext;
    List<ArticlesDTO> feedItemList;
    boolean TwoPane;
    public static ArticlesDTO articlesEntity;

    private String NULL_KEY = "null";

    public NewsApiRecyclerAdapter(Context mContext, List<ArticlesDTO> feedItemList, boolean twoPane) {
        this.mContext = mContext;
        this.feedItemList = feedItemList;
        TwoPane = twoPane;
    }

    @NonNull
    @Override
    public ViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_item, null);
        RecyclerView.ViewHolder viewHolder = new ViewHOlder(view);
        return (ViewHOlder) viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHOlder holder, int position) {
        if (feedItemList != null && feedItemList.size() > 0) {
            final ArticlesDTO feedItem = feedItemList.get(position);
            articlesEntity = feedItem;
            if (mContext != null) {
                if (feedItem.getAuthor() != null && feedItem.getTitle() != null) {
                    if (feedItem.getAuthor().equals(NULL_KEY)) {
                        holder.Author.setText(mContext.getResources().getString(R.string.author_not_identified));
                    } else {
                        holder.Author.setText(feedItem.getAuthor());
                    }
                    if (feedItem.getTitle().equals(NULL_KEY)) {
                        holder.Title.setText(mContext.getResources().getString(R.string.author_not_identified));
                    } else {
                        holder.Title.setText(feedItem.getTitle());
                    }
                    if (feedItem.getDescription() != null) {
                        if (feedItem.getDescription().equals(NULL_KEY)) {
                            holder.Description.setText(mContext.getResources().getString(R.string.author_not_identified));
                        } else {
                            holder.Description.setText(feedItem.getDescription());
                        }
                        if (feedItem.getPublishedAt() != null && feedItem.getUrl() != null && feedItem.getUrlToImage() != null) {
                            if (feedItem.getPublishedAt().equals(NULL_KEY)) {
                                holder.Date.setText(mContext.getResources().getString(R.string.author_not_identified));
                            } else {
                                holder.Date.setText(feedItem.getPublishedAt());
                            }
                            if (feedItem.getUrlToImage() != null && !feedItem.getUrlToImage().isEmpty() && feedItem.getUrlToImage() != "" && feedItem.getUrlToImage() != " ") {
                                Picasso.with(mContext).load(feedItem.getUrlToImage())
                                        .error(R.drawable.breaking_news)
                                        .into(holder.Image);
                            } else {
                                Picasso.with(mContext).load(R.drawable.breaking_news)
                                        .into(holder.Image);
                            }
                        } else {
                            holder.Date.setText("");
                        }
                    } else {
                        holder.Description.setText("");

                    }
                } else {
                    holder.Author.setText("");
                    holder.Title.setText("");
                }
            }
//            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (Config.ActivityNum != 0 && feedItemList != null) {
//                        ((ArticlesMasterListFragment.OnSelectedArticleListener) mContext).onArticleSelected(feedItemList.get(Config.PosNewsFragment), TwoPane, Config.PosNewsFragment);
////                        Config.position=position;
//                    }
//                    if (Config.ActivityNum == 0) {
//                        if (articlesEntity.getARTICLE_URL() != null) {
//                            String url = articlesEntity.getARTICLE_URL();
//                            Intent intent = new Intent(mContext, WebViewerActivity.class);
//                            intent.putExtra(URL_KEY, url);
//                            mContext.startActivity(intent);
//                        }
//                    } else {
//                        /*
//                        Do nothing
//                         */
//                        Log.e(LOG_TAG, NOTHING_TODO);
//                    }
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }
    class ViewHOlder extends RecyclerView.ViewHolder {
        protected LinearLayout linearLayout;
        protected TextView Title;
        protected TextView Author;
        protected TextView Date;
        protected TextView Description;
        protected ImageView Image;
        public ViewHOlder(View converview) {
            super(converview);
            this.Title = converview.findViewById(R.id.title);
            this.Author = converview.findViewById(R.id.author);
            this.Date = converview.findViewById(R.id.date_publish);
            this.Description = converview.findViewById(R.id.description);
            this.Image = converview.findViewById(R.id.image);
            this.linearLayout = converview.findViewById(R.id.linearLayout);
        }
    }
}