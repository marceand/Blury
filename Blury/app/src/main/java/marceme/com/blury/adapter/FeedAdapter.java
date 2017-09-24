package marceme.com.blury.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import marceme.com.blury.R;
import marceme.com.blury.model.Feed;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/23/2017.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private List<Feed> feeds;

    public FeedAdapter() {
        this.feeds = Collections.emptyList();
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View feedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_row_layout, parent, false);
        return new FeedViewHolder(feedView);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        Picasso.with(holder.avatarFeed.getContext()).load(feeds.get(position).getUrl()).into(holder.avatarFeed);
        holder.messageFeed.setText(feeds.get(position).messageFormatted());
        holder.dateFeed.setText(feeds.get(position).date());
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    public void addFeeds(List<Feed> feeds){
        this.feeds = feeds;
        notifyDataSetChanged();
    }

    class FeedViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image_avatar_feed)
        ImageView avatarFeed;
        @BindView(R.id.text_message_feed)
        TextView messageFeed;
        @BindView(R.id.text_date_feed)
        TextView dateFeed;

        public FeedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
