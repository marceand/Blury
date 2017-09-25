package marceme.com.blury.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import marceme.com.blury.R;
import marceme.com.blury.model.Score;


public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    private List<Score> scores;

    public ScoreAdapter() {
        scores = Collections.emptyList();
    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View scoreView = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_row_layout, parent, false);
        return new ScoreViewHolder(scoreView);
    }

    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position) {
        holder.scorePosition.setText(String.valueOf(position + 1));
        Picasso.with(holder.avatar.getContext()).load(scores.get(position).getUrl()).into(holder.avatar);
        holder.name.setText(scores.get(position).name());
        holder.score.setText(scores.get(position).getScore());
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public void addScore(List<Score> scores){
        this.scores = scores;
        notifyDataSetChanged();
    }

    class ScoreViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text_score_position)
        TextView scorePosition;
        @BindView(R.id.image_avatar_score)
        ImageView avatar;
        @BindView(R.id.text_name_score)
        TextView name;
        @BindView(R.id.text_score)
        TextView score;

        public ScoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
